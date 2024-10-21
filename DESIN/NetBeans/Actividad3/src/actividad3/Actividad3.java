/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crixo
 */
public class Actividad3 {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Formulario_principal fp = new Formulario_principal();
    
    
        fp.setVisible(true);
    }
    public static List <String> leerArchivo(String archivo)throws IOException{
        List<String> lineas = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea = br.readLine()) != null){
                lineas.add(linea);
            }
        }
        return lineas;
    }
    
    public static String elegirFrase(String archivo ){
        String fraseElegida = "";
        try{
            
            List<String> lineas = leerArchivo(archivo);
            if(!lineas.isEmpty()){
                
                int indice = (int) (Math.random() * lineas.size());
                fraseElegida = lineas.get(indice);
                
            }
        
        }catch(IOException e){
            
        }
        return fraseElegida;
    }
    
}
