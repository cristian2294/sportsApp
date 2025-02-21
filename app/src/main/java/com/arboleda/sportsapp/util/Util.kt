package com.arboleda.sportsapp.util

import android.os.Build
import com.arboleda.sportsapp.presentation.coreui.Screen
import com.arboleda.sportsapp.presentation.coreui.Screen.Fixtures
import com.arboleda.sportsapp.presentation.coreui.Screen.SelectCountry
import com.arboleda.sportsapp.presentation.coreui.Screen.SelectLeague
import java.time.LocalDate
import java.util.Calendar

object Util {
    fun selectStartScreen(
        countryCode: String,
        leagueId: Int,
    ): Screen =
        when {
            countryCode.isEmpty() -> SelectCountry
            leagueId == 0 -> SelectLeague(countryCode)
            else -> Fixtures(leagueId = leagueId, season = getCurrentSeason())
        }

    fun getCurrentSeason(): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now().year
        } else {
            Calendar.getInstance().get(Calendar.YEAR)
        }
}
