/*1- Crear un programa 'MostrarDirectorioActual' que muestre los ficheros del directorio actual */

import java.io.File;


public class MostrarDirectorioActual {

	/**
	 * @Efecto Contenido del directorio actual (directorio ".")
	 */
	public static void main(String[] args) {
	
		File f= new File(".");
		String [] listaFicheros=f.list(); //La lista nunca estará vacía (al menos contendrá "." y "..")
		
		System.out.println("Los ficheros del directorio actual son:");
		
		for (int i=0;i< listaFicheros.length;i++){
			System.out.println(listaFicheros[i]);
		}

	}

}