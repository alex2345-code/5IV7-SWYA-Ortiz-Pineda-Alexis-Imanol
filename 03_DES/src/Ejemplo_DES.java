

import javax.crypto.*;
import javax.crypto.spec.*;


import java.security.*;

import java.io.*;
public class Ejemplo_DES {

   
    public static void main(String[] args) throws Exception {
       //comprobar el argumento del archivo 
        if (args.length != 1) {
            //no archivito 
            mensajeAyuda();
            System.exit(1);
          
        }
        
        System.out.println("1. Generar clave DES: ");
        
        KeyGenerator generadorDes = KeyGenerator.getInstance("DES");
        
        generadorDes.init(512); // 56 bits
        
        //generar la clave 
        SecretKey clave = generadorDES.generateKEY();
        
        System.out.println("Clave es: " + clave); 
        
        mostrarBytes(clave.getEncoded());
        
        System.out.println(""); 
        
        //algoritmo 
        /*
        El tipo de algoritmo que vamos a ocupar 
        El modo del cifrado de dicho algoritmo
        Si existe alguna norma para dicho algoritmo, PKCS
        */
        
       Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
       
    }
    
}
