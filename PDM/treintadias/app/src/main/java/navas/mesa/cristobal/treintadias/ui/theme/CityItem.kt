package navas.mesa.cristobal.treintadias

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import navas.mesa.cristobal.treintadias.data.City

@Composable
fun CityItem(
    city: City,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                CityIcon(city.imageResourceId)
                CityInformation(city.name, city.population, city.country)
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CityIcon(
    imageResourceId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small)),
        painter = painterResource(imageResourceId),
        contentDescription = null
    )
}

@Composable
fun CityInformation(
    nameRes: Int,
    populationRes: Int,
    countryRes: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(nameRes),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(populationRes),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(countryRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


