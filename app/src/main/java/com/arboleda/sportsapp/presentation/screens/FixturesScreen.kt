package com.arboleda.sportsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arboleda.sportsapp.R
import com.arboleda.sportsapp.domain.models.fixtures.Response
import com.arboleda.sportsapp.presentation.states.FixturesState
import com.arboleda.sportsapp.presentation.viewmodels.fixtures.FixturesViewModel

@Composable
fun FixturesScreen(
    fixturesViewModel: FixturesViewModel = hiltViewModel(),
    timeZone: String,
    leagueId: Int,
    season: Int,
) {
    fixturesViewModel.getAllFixtures(timeZone, leagueId, season)
    val fixturesState by fixturesViewModel.fixturesState.collectAsState()
    val showDialog: Boolean by fixturesViewModel.showDialog.collectAsState(initial = true)

    when (fixturesState) {
        is FixturesState.Error -> {
            ShowError(
                show = showDialog,
                message = ((fixturesState as FixturesState.Error).message),
            ) {
                fixturesViewModel.onDialogDismiss()
            }
        }

        FixturesState.Loading -> {
            ShowLoader(modifier = Modifier)
        }

        is FixturesState.Success -> {
            ShowFixtures(fixtures = ((fixturesState as FixturesState.Success).fixtures))
        }
    }
}

@Composable
fun ShowFixtures(fixtures: List<Response>) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            menuOptions,
            title,
            mainMatch,
            otherMatches,
        ) = createRefs()

        TitleComponent(
            Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )

        MainMatchComponent(
            fixtures,
            Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.dimen_24dp),
                    start = dimensionResource(id = R.dimen.dimen_16dp),
                    end = dimensionResource(id = R.dimen.dimen_16dp),
                )
                .constrainAs(mainMatch) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )

        OtherMatchesComponent(
            fixtures,
            Modifier
                .constrainAs(otherMatches) {
                    top.linkTo(mainMatch.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )

        MenuOption(
            Modifier.constrainAs(menuOptions) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
    }
}

@Composable
fun TitleComponent(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.home_fixtures_title),
        modifier =
            modifier.padding(
                top = dimensionResource(id = R.dimen.dimen_12dp),
                start = dimensionResource(id = R.dimen.dimen_16dp),
                end = dimensionResource(id = R.dimen.dimen_16dp),
            ),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
fun MainMatchComponent(
    fixtures: List<Response>,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .height(200.dp),
    ) {
        HeaderMainMatchComponent()
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)))
        ScoreMainMatchComponent(fixtures)
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)))
        TeamNamesMainMatchComponent(fixtures)
    }
}

@Composable
fun HeaderMainMatchComponent() {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.home_fixtures_title_local_team),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = R.string.home_fixtures_title_time_match),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = R.string.home_fixtures_title_visitor_team),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun ScoreMainMatchComponent(fixtures: List<Response>) {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(fixtures.first().teams.home.logo)
                    .crossfade(true)
                    .build(),
            placeholder = painterResource(id = R.drawable.ic_indeterminate_country),
            contentDescription =
                stringResource(
                    id = R.string.home_fixtures_local_team_name_content_description,
                ),
            modifier =
                Modifier
                    .width(dimensionResource(id = R.dimen.dimen_48dp))
                    .height(dimensionResource(id = R.dimen.dimen_48dp))
                    .weight(1f),
        )

        Row(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(top = dimensionResource(id = R.dimen.dimen_12dp)),
        ) {
            Text(
                text = removeDecimalZero(fixtures.first().score.fulltime.home.toString()),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
            Text(
                text = "-",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
            Text(
                text = removeDecimalZero(fixtures.first().score.fulltime.away.toString()),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }

        AsyncImage(
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(fixtures.first().teams.away.logo)
                    .crossfade(true)
                    .build(),
            placeholder = painterResource(id = R.drawable.ic_indeterminate_country),
            contentDescription =
                stringResource(
                    id = R.string.home_fixtures_local_team_name_content_description,
                ),
            modifier =
                Modifier
                    .width(dimensionResource(id = R.dimen.dimen_48dp))
                    .height(dimensionResource(id = R.dimen.dimen_48dp))
                    .weight(1f),
        )
    }
}

@Composable
fun TeamNamesMainMatchComponent(fixtures: List<Response>) {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        Text(
            text = fixtures.first().teams.home.name,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = fixtures.first().teams.away.name,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun OtherMatchesComponent(
    fixtures: List<Response>,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .clip(
                    shape =
                        RoundedCornerShape(
                            topStart = dimensionResource(id = R.dimen.dimen_40dp),
                            topEnd = dimensionResource(id = R.dimen.dimen_40dp),
                        ),
                )
                .background(color = colorResource(id = R.color.purple2_400)),
    ) {
        TitleOtherMatchesComponent(modifier)
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_12dp)))
        ScoreOtherMatchesComponent(fixtures, modifier)
    }
}

@Composable
fun TitleOtherMatchesComponent(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.home_fixtures_title),
            modifier =
                modifier.padding(
                    top = dimensionResource(id = R.dimen.dimen_12dp),
                    start = dimensionResource(id = R.dimen.dimen_16dp),
                    end = dimensionResource(id = R.dimen.dimen_16dp),
                ),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.white),
        )
    }
}

@Composable
fun ScoreOtherMatchesComponent(
    fixtures: List<Response>,
    modifier: Modifier,
) {
    LazyColumn {
        items(fixtures) { fixture ->
            Row(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = fixture.teams.home.name,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.weight(2f),
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(fixture.teams.home.logo)
                            .crossfade(true)
                            .build(),
                    placeholder = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_fixtures_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_24dp))
                            .height(dimensionResource(id = R.dimen.dimen_24dp))
                            .weight(1f),
                )

                Text(
                    text = removeDecimalZero(fixture.score.fulltime.home.toString()),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.weight(0.5f),
                )
                Text(
                    text = "-",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.weight(0.5f),
                )
                Text(
                    text = removeDecimalZero(fixture.score.fulltime.away.toString()),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.weight(0.5f),
                )

                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(fixture.teams.away.logo)
                            .crossfade(true)
                            .build(),
                    placeholder = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_fixtures_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_24dp))
                            .height(dimensionResource(id = R.dimen.dimen_24dp))
                            .weight(1f),
                )

                Text(
                    text = fixture.teams.away.name,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.weight(2f),
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_16dp)))
        }
    }
}

@Composable
fun MenuOption(modifier: Modifier) {
    var index by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            selected = index == 0,
            onClick = {
                index = 0
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.SportsSoccer,
                    contentDescription = stringResource(id = R.string.home_menu_fixtures),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.home_menu_fixtures))
            },
        )

        NavigationBarItem(
            selected = index == 1,
            onClick = {
                index = 1
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.ListAlt,
                    contentDescription = stringResource(id = R.string.home_menu_positions),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.home_menu_positions))
            },
        )

        NavigationBarItem(
            selected = index == 2,
            onClick = {
                index = 2
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Groups,
                    contentDescription = stringResource(id = R.string.home_menu_teams),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.home_menu_teams))
            },
        )
    }
}

private fun removeDecimalZero(value: Any): String {
    return value.toString().removeSuffix(".0")
}
