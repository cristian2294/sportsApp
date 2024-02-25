package com.arboleda.sportsapp.presentation.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arboleda.sportsapp.R

@Composable
fun HomeScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (menuOptions, title, mainMatch, otherMatches) = createRefs()

        TitleComponent(
            Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )

        MainMatchComponent(
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
        text = stringResource(id = R.string.home_matches_title),
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
fun MainMatchComponent(modifier: Modifier) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .height(200.dp),
    ) {
        HeaderMainMatchComponent()
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)))
        ScoreMainMatchComponent()
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)))
        TeamNamesMainMatchComponent()
    }
}

@Composable
fun HeaderMainMatchComponent() {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.home_matches_title_local_team),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = R.string.home_matches_title_time_match),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = R.string.home_matches_title_visitor_team),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun ScoreMainMatchComponent() {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_indeterminate_country),
            contentDescription =
                stringResource(
                    id = R.string.home_matches_local_team_name_content_description,
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
                text = "0",
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
                text = "0",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_indeterminate_country),
            contentDescription =
                stringResource(
                    id = R.string.home_matches_visitor_team_name_content_description,
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
fun TeamNamesMainMatchComponent() {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Team Name",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Team Name",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun OtherMatchesComponent(modifier: Modifier) {
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
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)))
        ScoreOtherMatchesComponent(modifier)
    }
}

@Composable
fun TitleOtherMatchesComponent(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.home_matches_title),
            modifier =
                modifier
                    .padding(
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
fun ScoreOtherMatchesComponent(modifier: Modifier) {
    LazyColumn {
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
        item {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_local_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )
                Text(
                    text = "0",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_indeterminate_country),
                    contentDescription =
                        stringResource(
                            id = R.string.home_matches_visitor_team_name_content_description,
                        ),
                    modifier =
                        Modifier
                            .width(dimensionResource(id = R.dimen.dimen_48dp))
                            .height(dimensionResource(id = R.dimen.dimen_48dp))
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_4dp)),
                )

                Text(
                    text = "Team Name",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),
                    modifier =
                        modifier
                            .padding(end = dimensionResource(id = R.dimen.dimen_16dp))
                            .align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
fun LocalTeamOtherMatchesComponent(modifier: Modifier) {
    Text(
        text = "Team Name",
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.white),
        modifier = modifier.padding(start = dimensionResource(id = R.dimen.dimen_16dp)),
    )
    Image(
        painter = painterResource(id = R.drawable.ic_indeterminate_country),
        contentDescription =
            stringResource(
                id = R.string.home_matches_visitor_team_name_content_description,
            ),
        modifier =
            Modifier
                .width(dimensionResource(id = R.dimen.dimen_36dp))
                .height(dimensionResource(id = R.dimen.dimen_36dp)),
    )
}

@Composable
fun MenuOption(modifier: Modifier) {
    var index by rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current

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
                    contentDescription = stringResource(id = R.string.home_menu_matches),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.home_menu_matches))
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
