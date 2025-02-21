package com.arboleda.sportsapp.presentation.coreui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.arboleda.sportsapp.presentation.coreui.Screen.Fixtures
import com.arboleda.sportsapp.presentation.coreui.Screen.SelectCountry
import com.arboleda.sportsapp.presentation.coreui.Screen.SelectLeague
import com.arboleda.sportsapp.presentation.screens.CountriesScreen
import com.arboleda.sportsapp.presentation.screens.FixturesScreen
import com.arboleda.sportsapp.presentation.screens.LeaguesScreen
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import com.arboleda.sportsapp.util.Constants.Companion.ZERO
import com.arboleda.sportsapp.util.Util.getCurrentSeason

@Composable
fun NavigationWrapper(
    countriesViewModel: CountriesViewModel = hiltViewModel(),
    startDestination: Screen,
    localCountryCode: String?,
    localLeagueId: Int?,
) {
    countriesViewModel.getAllCountries()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<SelectCountry> {
            CountriesScreen { countryCode ->
                navController.navigate(SelectLeague(countryCode))
            }
        }

        composable<SelectLeague> { backStackEntry ->
            val league: SelectLeague = backStackEntry.toRoute()
            val countryCode =
                if (localCountryCode.isNullOrEmpty()) {
                    league.countryCode
                } else {
                    localCountryCode
                }
            LeaguesScreen(
                countryCode = countryCode,
                navigateToHome = { timeZone, leagueId, season ->
                    navController.navigate(Fixtures(timeZone, leagueId, season))
                },
            )
        }

        composable<Fixtures> { backStackEntry ->
            val fixtures: Fixtures = backStackEntry.toRoute()
            // Set value for the country code depending the kind of navigation
            val leagueId =
                if (localLeagueId == ZERO) {
                    fixtures.leagueId
                } else {
                    localLeagueId
                }
            val currentSeason = getCurrentSeason()

            FixturesScreen(
                timeZone = fixtures.timeZone,
                leagueId = leagueId ?: 0,
                season = currentSeason,
            )
        }
    }
}
