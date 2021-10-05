

/*
cifrado siemtrico 
modo de bloque de 64 bit s
maneja una llave privada de 64 bits => 8 caracteres 
Se debe de tomar solamente 56 bits para la llave 

Permutaciones, Cajas de sustitucion 

vamos a crear una aplicacion en la cual va a recibir un archivo en txt 
con un mensaje secreto y vamos a cifrarlo por medio del cifrado DES
y nos va a generar un archico, cifrado y para descifrarlo un archivom decifrado
para ello vamos a ocupar las librerias de 
SECURITY
CRYPTO
 */
import javax.crypto.*;
import javax.crypto.spec.*;
//generar las llaves 


import java.security.*;
//todos los tipos de algoritmo de cifrado :3

import java.io.*;

public class Ejemplo_DES {

   
    public static void main(String[] args) throws Exception {
        //comprobar el argumento del archivo o fichero f
        if (args.length != 1) {
            //no archivito 
            mensajeAyuda();
            System.exit(1);

        }
        
        /*
        lo primero que tenemos que haer es cargar una instancia del proveedor
        del cifrado que vamos a ocupar en esta ocacion vamos a ocupar 
        un cifrado DES     
        */

        System.out.println("1. Generar clave DES: ");

        
        //la clase que se encarga de generar llaves
        KeyGenerator generadorDes = KeyGenerator.getInstance("DES");

        
        //inicializar el tamammio de la llave del generador 
        generadorDes.init(512); // 56 bits

        //generar la clave 
        SecretKey clave = generadorDES.generateKEY();//es generar las 16 llaves
        

        System.out.println("Clave es: " + clave);

        mostrarBytes(clave.getEncoded());       
        // la llave nmo tiene formato, como tal son los bits
        System.out.println("");

        //algoritmo 
        /*
        El tipo de algoritmo que vamos a ocupar 
        El modo del cifrado de dicho algoritmo
        Si existe alguna norma para dicho algoritmo, PKCS (estandares para 
        lso cifrados)
         */
        
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
        
        /*
        vamos a ocupar una isntancioa del algorito DES
        en el modo de cifrado por bloques electronico
        vamos aplicar PCS5 isando el relleno para los bloques
        
        ALGORITMO : DES
        MODO : ECB(Electronic Code Book)
        RELLENO : PKCS5
        */
        
        System.out.println("2.- Cifrar con DES el archivo : " + args[0]);
        cifrado.init(0, clave);
    }

    private static void mensajeAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
