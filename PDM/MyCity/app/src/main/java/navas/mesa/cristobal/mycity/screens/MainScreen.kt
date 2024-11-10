package navas.mesa.cristobal.mycity.screens

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import navas.mesa.cristobal.mycity.data.Categoria
import navas.mesa.cristobal.mycity.model.categoryList

import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items


@Composable
fun MainScreen(navController: NavController){
    Column {
        Text(
            text = "Cádiz",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn{
            items(categoryList){ categoria ->
                CategoriaCard(categoria , navController  )

            }
        }
    }
}
@Composable
fun CategoriaCard(categoria: Categoria, navController: NavController){
    val categoriaNombre = stringResource(id = categoria.name)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("categoria/$categoriaNombre")
            }
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(id = categoria.imageResId),
                contentDescription = categoriaNombre,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = categoriaNombre, style = MaterialTheme.typography.headlineSmall)
        }
    }
}