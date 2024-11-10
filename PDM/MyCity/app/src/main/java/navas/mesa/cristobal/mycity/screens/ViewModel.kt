package navas.mesa.cristobal.mycity.screens

import androidx.lifecycle.ViewModel
import navas.mesa.cristobal.mycity.data.Categoria

class CategoriaViewModel : ViewModel(){
    var categoriaSeleccionada: Categoria? = null

    fun seleccionarCategoria(categoria: Categoria){
        categoriaSeleccionada = categoria
    }
}