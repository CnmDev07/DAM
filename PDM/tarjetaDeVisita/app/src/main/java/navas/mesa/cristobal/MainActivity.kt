package navas.mesa.cristobal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navas.mesa.cristobal.ui.theme.TarjetaDeVisitaTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TarjetaDeVisitaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TarjetaDeVisita()
                }
            }
        }
    }
}

@Composable
fun TarjetaDeVisita() {
    val customFontFamily = FontFamily(
        Font(R.font.dot, FontWeight.Normal)
    )

    Image(
        painterResource(id = R.drawable.fondo),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop

    )
    Column(Modifier.fillMaxSize()){
        Row(
            Modifier.weight(1f) ,
            horizontalArrangement = Arrangement.Center
        ) {
            Column (Modifier.fillMaxSize()){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.usuario),
                        contentDescription = null,

                        modifier = Modifier
                            .size(150.dp)
                            .offset(y = 50.dp)

                    )
                }

                Row(
                Modifier.weight(1f)
            ) {
                Text(
                    text = "Cristóbal Navas Mesa",
                    fontSize = 48.sp,

                    modifier = Modifier
                        .padding(top = 15.dp)
                        .offset(y = -10.dp),

                    style = androidx.compose.ui.text.TextStyle(
                        lineHeight = 45.sp,
                        fontFamily = customFontFamily
                    ),

                    textAlign = TextAlign.Center

                )
            }

            }

        }
        Row(
            Modifier
                .weight(1f)
                .padding(top = 50.dp)
        ) {

            Column (
                Modifier
                    .fillMaxSize()
                    .offset(y = -20.dp)
            ){
                Row (
                    Modifier
                        .weight(1f)
                        .padding(top = 50.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.correo_electronico),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 30.dp, height = 30.dp)
                            .weight(1f)

                    )
                    Text(
                        text = "crinavmes@gmail.com",
                        modifier = Modifier
                            .weight(3f)

                    )
                }
                Row (
                    Modifier
                        .weight(1f)
                        .padding(top = 25.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 30.dp, height = 30.dp)
                            .weight(1f)

                    )
                    Text(
                        text = "linkedin.com/in/cristóbal-navas",
                        modifier = Modifier
                            .weight(3f)


                    )
                }
                Row (
                    Modifier
                        .weight(2f)
                        .padding(bottom = 100.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.llamada_telefonica),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 30.dp, height = 30.dp)
                            //.padding(top = 20.dp)
                            .weight(1f)

                    )
                    Text(
                        text = "+34662549876",
                        modifier = Modifier
                            .weight(3f)
                            .padding(end = 100.dp)
                        //.padding(end = 20.dp, top = 20.dp)
                    )
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TarjetaDeVisitaTheme {
        TarjetaDeVisita()
    }
}