package com.arboleda.sportsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arboleda.sportsapp.R
import com.arboleda.sportsapp.domain.models.leagues.League
import com.arboleda.sportsapp.domain.models.leagues.Response
import com.arboleda.sportsapp.presentation.states.LeagueState
import com.arboleda.sportsapp.presentation.viewmodels.leagues.LeaguesViewModel
import com.arboleda.sportsapp.util.Constants.Companion.DEFAULT_TIME_ZONE
import com.arboleda.sportsapp.util.Util.getCurrentSeason

@Composable
fun LeaguesScreen(
    leaguesViewModel: LeaguesViewModel = hiltViewModel(),
    countryCode: String,
    navigateToHome: (timeZone: String, leagueId: Int, season: Int) -> Unit,
) {
    LaunchedEffect(true) {
        leaguesViewModel.getAllLeagues(countryCode)
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.purple2_50))
                .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
    ) {
        InitLeaguesScreen(
            modifier = Modifier,
            leaguesViewModel = leaguesViewModel,
            navigateToHome = navigateToHome,
        )
    }
}

@Composable
fun InitLeaguesScreen(
    modifier: Modifier,
    leaguesViewModel: LeaguesViewModel,
    navigateToHome: (timeZone: String, leagueId: Int, season: Int) -> Unit,
) {
    val leaguesState = leaguesViewModel.leagueState.collectAsState()
    val showDialog: Boolean by leaguesViewModel.showDialog.collectAsState()

    when (leaguesState.value) {
        is LeagueState.Error ->
            ShowError(
                show = showDialog,
                message = (leaguesState.value as LeagueState.Error).message,
            ) {
                leaguesViewModel.onDialogDismiss()
            }

        LeagueState.Loading -> ShowLoader(modifier = modifier)
        is LeagueState.Success ->
            ShowListLeagues(
                leaguesViewModel = leaguesViewModel,
                leagues = (leaguesState.value as LeagueState.Success).leagues,
                navigateToHome = navigateToHome,
            )
    }
}

@Composable
fun ShowListLeagues(
    leaguesViewModel: LeaguesViewModel,
    leagues: List<Response>,
    navigateToHome: (timeZone: String, leagueId: Int, season: Int) -> Unit,
) {
    TitleLeagueScreen(Modifier)
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_16dp)))
    ListLeagues(
        leaguesViewModel = leaguesViewModel,
        modifier = Modifier,
        leagues = leagues,
        navigateToHome = navigateToHome,
    )
}

@Composable
fun TitleLeagueScreen(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.leagues_title),
        color = colorResource(id = R.color.purple2_700),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_24dp)),
    )
}

@Composable
fun ListLeagues(
    leaguesViewModel: LeaguesViewModel,
    modifier: Modifier,
    leagues: List<Response>,
    navigateToHome: (timeZone: String, leagueId: Int, season: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_80dp)),
    ) {
        items(leagues) { response ->
            ItemLeague(response.league, modifier) {
                leaguesViewModel.saveLeagueId(response.league.id)
                navigateToHome(
                    DEFAULT_TIME_ZONE,
                    response.league.id,
                    getCurrentSeason(),
                )
            }
        }
    }
}

@Composable
fun ItemLeague(
    league: League,
    modifier: Modifier,
    onItemSelected: (league: League) -> Unit,
) {
    Column(modifier = modifier.clickable { onItemSelected(league) }) {
        Spacer(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)),
        )
        Row {
            if (league.logo.isEmpty()) {
                LoadImageIndeterminateFlag()
            } else {
                LoadLeagueLogo(league.logo)
            }
            Text(
                text = league.name,
                modifier =
                    modifier
                        .padding(start = dimensionResource(id = R.dimen.dimen_8dp))
                        .align(alignment = Alignment.CenterVertically),
            )
        }
        Spacer(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)),
        )
        Divider(
            modifier =
                modifier
                    .height(dimensionResource(id = R.dimen.dimen_1dp)),
        )
    }
}

@Composable
fun LoadLeagueLogo(logo: String) {
    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(logo)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
        contentDescription = stringResource(id = R.string.countries_flag_content_description),
        contentScale = ContentScale.Crop,
        modifier =
            Modifier
                .size(dimensionResource(id = R.dimen.dimen_40dp))
                .clip(CircleShape),
    )
}
