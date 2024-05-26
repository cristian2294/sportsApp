package com.arboleda.sportsapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arboleda.sportsapp.presentation.screens.CountriesScreen
import com.arboleda.sportsapp.presentation.screens.FixturesScreen
import com.arboleda.sportsapp.presentation.screens.LeaguesScreen
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import com.arboleda.sportsapp.presentation.viewmodels.leagues.LeaguesViewModel
import com.arboleda.sportsapp.ui.theme.SportsAppTheme
import com.arboleda.sportsapp.util.Constants
import com.arboleda.sportsapp.util.Constants.Companion.COUNTRY_CODE
import com.arboleda.sportsapp.util.Constants.Companion.DEFAULT_TIME_ZONE
import com.arboleda.sportsapp.util.Constants.Companion.LEAGUE_ID
import com.arboleda.sportsapp.util.Constants.Companion.SEASON
import com.arboleda.sportsapp.util.Constants.Companion.TIME_ZONE
import com.arboleda.sportsapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val countriesViewModel: CountriesViewModel by viewModels()
        val leaguesViewModel: LeaguesViewModel by viewModels()
        countriesViewModel.getAllCountries()
        setContent {
            SportsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    // get the country code saved in the datastore
                    countriesViewModel.getCountryCode()
                    val localCountryCode = countriesViewModel.countryCode.value

                    // get the league id saved in the datastore
                    leaguesViewModel.getLeagueId()
                    val localLeagueId = leaguesViewModel.leagueId.value

                    /*** Define the start destination depending if the
                     country code has saved in the datastore or not ***/
                    val startDestination =
                        selectStartScreen(
                            countryCode = localCountryCode ?: "",
                            leagueId = localLeagueId ?: 0,
                        )

                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                    ) {
                        composable(
                            route = Routes.HomeScreen.route,
                            arguments =
                                listOf(
                                    navArgument(TIME_ZONE) {
                                        type = NavType.StringType
                                    },
                                    navArgument(LEAGUE_ID) {
                                        type = NavType.IntType
                                    },
                                    navArgument(SEASON) {
                                        type = NavType.IntType
                                    },
                                ),
                        ) { input ->

                            val timeZone =
                                input.arguments?.getString(TIME_ZONE) ?: DEFAULT_TIME_ZONE

                            // Set value for the country code depending the kind of navigation
                            val leagueId =
                                if (localLeagueId == 0) {
                                    input.arguments?.getInt(LEAGUE_ID)
                                } else {
                                    localLeagueId
                                }

                            val currentSeason = getCurrentSeason()
                            FixturesScreen(
                                timeZone = timeZone,
                                leagueId = leagueId ?: 0,
                                season = currentSeason,
                            )
                        }

                        composable(
                            route = Routes.SelectCountryScreen.route,
                        ) {
                            CountriesScreen(countriesViewModel, navController)
                        }

                        composable(
                            route = Routes.SelectLeagueScreen.route,
                            arguments =
                                listOf(
                                    navArgument(COUNTRY_CODE) {
                                        type = NavType.StringType
                                    },
                                ),
                        ) { input ->

                            // Set value for the country code depending the kind of navigation
                            val countryCode =
                                if (localCountryCode.isNullOrEmpty()) {
                                    input.arguments?.getString(COUNTRY_CODE)!!
                                } else {
                                    localCountryCode
                                }

                            LeaguesScreen(
                                leaguesViewModel = leaguesViewModel,
                                countryCode = countryCode,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCurrentSeason(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now().year
        } else {
            Calendar.getInstance().get(Calendar.YEAR)
        }
    }

    private fun selectStartScreen(
        countryCode: String,
        leagueId: Int,
    ): String {
        return when {
            countryCode.isEmpty() -> Constants.SELECT_COUNTRY_SCREEN
            leagueId == 0 -> Constants.SELECT_LEAGUE_SCREEN
            else -> Constants.HOME_SCREEN
        }
    }
}
