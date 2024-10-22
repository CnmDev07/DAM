import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import navas.mesa.cristobal.treintadias.R
import navas.mesa.cristobal.treintadias.data.City

@Composable
fun CityItem(
    city: City,
    modifier: Modifier = Modifier
) {

    val isExpanded = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .clickable { isExpanded.value = !isExpanded.value }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CityIcon(city.imageResourceId)
                CityInformation(city.name, isExpanded.value)
                Spacer(Modifier.weight(1f))
            }


            if (isExpanded.value) {
                CityDetails(city.population, city.country)
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
            .fillMaxWidth(0.7f)
            .padding(dimensionResource(R.dimen.padding_small)),
        painter = painterResource(imageResourceId),
        contentDescription = null
    )
}


@Composable
fun CityInformation(
    nameRes: Int,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp)

    ) {
        Text(
            text = stringResource(nameRes),
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
fun CityDetails(
    populationRes: Int,
    countryRes: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_small))) {
        Text(
            text = stringResource(countryRes),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(populationRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
