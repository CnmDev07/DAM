package navas.mesa.cristobal.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navas.mesa.cristobal.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    val primero = R.drawable.creacion
    val segundo = R.drawable.grito
    val tercero = R.drawable.meninas
    val cuarto = R.drawable.monalisa
    val quinto = R.drawable.nocheestrellada

    var titulo by remember { mutableStateOf(R.string.tituloPrimero) }
    var año by remember { mutableStateOf(R.string.añoPrimero) }
    var cuadroActual by remember { mutableStateOf(primero) }

    Box(
        modifier = modifier.fillMaxSize()
            .background(Color(0xFFCAF3F7)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Imagendisplay(cuadroActual = cuadroActual)
            Spacer(modifier = modifier.size(15.dp))
            tituloDisplay(titulo = titulo, año = año)
            Spacer(modifier = modifier.size(15.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    when (cuadroActual) {
                        primero -> {
                            cuadroActual = quinto
                            titulo = R.string.tituloQuinto
                            año = R.string.añoQuinto
                        }
                        segundo -> {
                            cuadroActual = primero
                            titulo = R.string.tituloPrimero
                            año = R.string.añoPrimero
                        }
                        tercero -> {
                            cuadroActual = segundo
                            titulo = R.string.tituloSegundo
                            año = R.string.añoSegundo
                        }
                        cuarto -> {
                            cuadroActual = tercero
                            titulo = R.string.tituloTercero
                            año = R.string.añoTercero
                        }
                        quinto -> {
                            cuadroActual = cuarto
                            titulo = R.string.tituloCuarto
                            año = R.string.añoCuarto
                        }
                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray
                    )
                    ) {
                    Text(
                        text = "Anterior",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {
                    when (cuadroActual) {
                        primero -> {
                            cuadroActual = segundo
                            titulo = R.string.tituloSegundo
                            año = R.string.añoSegundo
                        }
                        segundo -> {
                            cuadroActual = tercero
                            titulo = R.string.tituloTercero
                            año = R.string.añoTercero
                        }
                        tercero -> {
                            cuadroActual = cuarto
                            titulo = R.string.tituloCuarto
                            año = R.string.añoCuarto
                        }
                        cuarto -> {
                            cuadroActual = quinto
                            titulo = R.string.tituloQuinto
                            año = R.string.añoQuinto
                        }
                        quinto -> {
                            cuadroActual = primero
                            titulo = R.string.tituloPrimero
                            año = R.string.añoPrimero
                        }
                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray
                    )

                ) {
                    Text(
                        text = "Siguiente",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Imagendisplay(
    @DrawableRes cuadroActual: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(300.dp)
            .background(Color.White)
            .border(1.dp, Color.Gray)
            .shadow(4.dp)

    ) {
        Image(
            painter = painterResource(id = cuadroActual),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}



@Composable
fun tituloDisplay(
    @StringRes titulo: Int,
    @StringRes año: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = titulo),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = año),
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpace()
}
