package navas.mesa.cristobal.mycity.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import historiaList
import navas.mesa.cristobal.mycity.R
import navas.mesa.cristobal.mycity.data.Fiestas
import navas.mesa.cristobal.mycity.data.Gastronomia
import navas.mesa.cristobal.mycity.data.Historia
import navas.mesa.cristobal.mycity.data.Sitios
import navas.mesa.cristobal.mycity.model.fiestasList
import navas.mesa.cristobal.mycity.model.gastronomiaList
import navas.mesa.cristobal.mycity.model.sitiosList


@Composable
fun CategoriaScreen(categoriaNombre: String?, navController: NavController){
    Column {
        Text(
            text = categoriaNombre ?: "Categoria desconocida",
            style = MaterialTheme.typography.headlineLarge
        )
        if (categoriaNombre == stringResource(id = R.string.sitios)){
            LazyColumn {
                items(sitiosList){ sitios ->
                    SitiosCard (sitios, navController)
                    
                }
            }
        }else if(categoriaNombre == stringResource(id = R.string.fiestas)){
            LazyColumn {
                items(fiestasList){ fiestas ->
                    FiestasCard(fiestas , navController )
                }
            }
        }else if (categoriaNombre == stringResource(id = R.string.historia)){
            LazyColumn {
                items(historiaList){historia ->
                    HistoriaCard(historia , navController )
                }
            }
        }else if(categoriaNombre == stringResource(id = R.string.gastronomia)){
            LazyColumn {
                items(gastronomiaList){gastronomia ->
                    GastronomiaCard(gastronomia , navController)
                }
            }
        }
    }
}

@Composable
fun SitiosCard(sitios: Sitios, navController: NavController){
    val sitioNombre = stringResource(id = sitios.name)
    
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("sitios/$sitioNombre")
            }
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(
                    id = sitios.imageResId) , 
                    contentDescription = sitioNombre,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
            )
            Text(text = sitioNombre, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun FiestasCard(fiestas: Fiestas, navController: NavController){
    val fiestaNombre = stringResource(id = fiestas.name)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("fiestas/$fiestaNombre")
            }
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(id = fiestas.imageResId),
                contentDescription = fiestaNombre,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = fiestaNombre, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun GastronomiaCard(gastronomia: Gastronomia, navController: NavController){
    val gastronomiaNombre = stringResource(id = gastronomia.name)
    
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("gastronomia/$gastronomiaNombre")
            }
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(id = gastronomia.imageResId) , 
                contentDescription = gastronomiaNombre,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = gastronomiaNombre, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun HistoriaCard(historia: Historia, navController: NavController){
    val historiaNombre = stringResource(id = historia.name)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("historia/$historiaNombre")
            }
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(id = historia.imageResId),
                contentDescription = historiaNombre,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = historiaNombre, style = MaterialTheme.typography.headlineSmall)
        }
    }
}