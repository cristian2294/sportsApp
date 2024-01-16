package com.arboleda.sportsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arboleda.sportsapp.presentation.screens.CountriesScreen
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import com.arboleda.sportsapp.presentation.viewmodels.countries.LeaguesViewModel
import com.arboleda.sportsapp.ui.theme.SportsAppTheme
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
                    CountriesScreen(countriesViewModel)
                }
            }
        }
    }
}
