package com.arboleda.sportsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arboleda.sportsapp.presentation.coreui.NavigationWrapper
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import com.arboleda.sportsapp.presentation.viewmodels.leagues.LeaguesViewModel
import com.arboleda.sportsapp.ui.theme.SportsAppTheme
import com.arboleda.sportsapp.util.Util.selectStartScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val countriesViewModel: CountriesViewModel by viewModels()
        val leaguesViewModel: LeaguesViewModel by viewModels()
        setContent {
            SportsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // get the country code saved in the datastore
                    countriesViewModel.getCountryCode()
                    val localCountryCode = countriesViewModel.countryCode.collectAsState()

                    // get the league id saved in the datastore
                    leaguesViewModel.getLeagueId()
                    val localLeagueId = leaguesViewModel.leagueId.collectAsState()

                    /*** Define the start destination depending if the
                     country code has saved in the datastore or not ***/
                    val startDestination =
                        selectStartScreen(
                            countryCode = localCountryCode.value,
                            leagueId = localLeagueId.value,
                        )
                    NavigationWrapper(
                        startDestination = startDestination,
                        localCountryCode = localCountryCode.value,
                        localLeagueId = localLeagueId.value,
                    )
                }
            }
        }
    }
}
