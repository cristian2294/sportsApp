package com.arboleda.sportsapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arboleda.sportsapp.R
import com.arboleda.sportsapp.domain.models.countries.Response
import com.arboleda.sportsapp.presentation.states.CountriesState
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel

@Composable
fun CountriesScreen(countriesViewModel: CountriesViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple2_50))
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
    ) {
        InitViewModel(countriesViewModel, Modifier)
    }
}

@Composable
fun InitViewModel(countriesViewModel: CountriesViewModel, modifier: Modifier) {
    val countriesState = countriesViewModel.countriesState.observeAsState()
    when (countriesState.value) {
        is CountriesState.Error -> ShowError((countriesState.value as CountriesState.Error).message)
        CountriesState.Loading -> ShowLoader(modifier)
        is CountriesState.Success -> {
            ShowListCountries((countriesState.value as CountriesState.Success).countries)
        }

        else -> Unit
    }
}

@Composable
fun Title(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.countries_title),
        color = colorResource(id = R.color.purple2_700),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_24dp)),
    )
}

@Composable
fun ListCountries(modifier: Modifier, countries: List<Response>) {
    LazyColumn(
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_80dp)),
    ) {
        items(countries) { country ->
            Spacer(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)),
            )
            Row(modifier = modifier) {
                if (country.flag.isNullOrEmpty()) {
                    LoadImageIndeterminateFlag()
                } else {
                    LoadFlagCountry(country.flag)
                }
                Text(
                    text = country.name,
                    modifier = modifier.padding(start = dimensionResource(id = R.dimen.dimen_8dp))
                        .align(alignment = Alignment.CenterVertically),

                )
            }
            Spacer(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8dp)),
            )
            Divider(
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.dimen_1dp)),
            )
        }
    }
}

@Composable
fun LoadImageIndeterminateFlag() {
    Image(
        painter = painterResource(
            id = R.drawable.ic_indeterminate_country,
        ),
        contentDescription = stringResource(
            id = R.string.countries_indeterminate_flag_content_description,
        ),
    )
}

@Composable
fun LoadFlagCountry(flag: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(flag)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = stringResource(id = R.string.countries_flag_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.dimen_40dp))
            .clip(CircleShape),
    )
}

@Composable
fun ShowLoader(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_64dp))
                .align(alignment = Alignment.Center),
            color = colorResource(id = R.color.purple2_500),
        )
    }
}

@Composable
fun ShowError(message: String?) {
    // TODO: implement in the future
}

@Composable
fun ShowListCountries(countries: List<Response>) {
    Title(Modifier)
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_16dp)))
    ListCountries(Modifier, countries)
}
