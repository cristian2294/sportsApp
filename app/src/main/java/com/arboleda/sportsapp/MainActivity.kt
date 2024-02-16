package com.arboleda.sportsapp

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
import com.arboleda.sportsapp.presentation.screens.HomeScreen
import com.arboleda.sportsapp.presentation.screens.LeaguesScreen
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import com.arboleda.sportsapp.presentation.viewmodels.countries.LeaguesViewModel
import com.arboleda.sportsapp.ui.theme.SportsAppTheme
import com.arboleda.sportsapp.util.Constants
import com.arboleda.sportsapp.util.Constants.Companion.COUNTRY_CODE
import com.arboleda.sportsapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

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

                    /*** Define the start destination depending if the
                     country code has saved in the datastore or not ***/
                    val startDestination = selectStartScreen(
                        localCountryCode ?: "",
                    )

                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                    ) {
                        composable(
                            route = Routes.HomeScreen.route,
                        ) {
                            HomeScreen()
                        }

                        composable(
                            route = Routes.SelectCountryScreen.route,
                        ) {
                            CountriesScreen(countriesViewModel, navController)
                        }

                        composable(
                            route = Routes.SelectLeagueScreen.route,
                            arguments = listOf(
                                navArgument(COUNTRY_CODE) {
                                    type = NavType.StringType
                                },
                            ),
                        ) { input ->

                            // Set value for the country code depending the kind of navigation
                            val countryCode = if (localCountryCode.isNullOrEmpty()) {
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

    private fun selectStartScreen(countryCode: String): String {
        // TODO: I need modify this function when save the league in a datastore
        return if (countryCode.isEmpty()) {
            Constants.SELECT_COUNTRY_SCREEN
        } else {
            Constants.SELECT_LEAGUE_SCREEN
        }
    }
}
