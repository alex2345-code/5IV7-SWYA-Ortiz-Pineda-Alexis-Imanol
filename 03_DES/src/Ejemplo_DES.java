

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
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");

        
        //inicializar el tamammio de la llave del generador 
        generadorDES.init(56); // 56 bits

        //generar la clave 
        SecretKey clave = generadorDES.generateKey();//es generar las 16 llaves
        

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
        
        System.out.println("2.- Cifrar con DES el archivo : " + args[0]
        + ", dejar erl resultado en : " + args[0]+ ".cifrado");
        
        cifrado.init(Cipher.ENCRYPT_MODE, clave);
        
        //el problema es como vamos a leer lso bloques 
        
        //buffer para la entrada y salida de los bits 
        
        byte[] buffer = new byte[1000]; //vamos a leer cada mil 
        byte[] bufferCifrado;
        
        //generar mis archivos 
        
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
        
        //lectura 
        
        int bytesleidos = in.read(buffer, 0, 1000);
        //mientras no este al final del fichero o del archivo 
        while(bytesleidos != -1){
            //pasar al tecto claro leido al cifrador
            bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
            //generear la salida
            out.write(bufferCifrado);
            bytesleidos = in.read(buffer, 0, 1000);
            
        }
        //vamos a reunir lso bloques del cifrado 
        bufferCifrado = cifrado.doFinal();
        //ya puedo escribir el archivo cifrado
        out.write(bufferCifrado);
        
        
        in.close();
        out.close();
        
        //vamos a descifrar
        
         System.out.println("3.- Descifrar con DES el archivo : " + args[0]
        + ", cifrado : " +"vamos a ver el resultado en el archivo:"
         + args[0]+ ".descifrado");
        
        
        cifrado.init(Cipher.DECRYPT_MODE, clave);
        
        
        //buffer para la entrada y salida de los bits 
        
       
        byte[] bufferPlano;
        
        //generar mis archivos 
        
         in = new FileInputStream(args[0]+".cifrado");
         out = new FileOutputStream(args[0]+".descifrado");
        
        //lectura 
        
        bytesleidos = in.read(buffer, 0, 1000);
        //mientras no este al final del fichero o del archivo 
        while(bytesleidos != -1){
            //pasar al tecto claro leido al cifrador
            bufferPlano= cifrado.update(buffer, 0, bytesleidos);
            //generear la salida
            out.write(bufferPlano);
            bytesleidos = in.read(buffer, 0, 1000);
            
        }
        //vamos a reunir lso bloques del cifrado 
        bufferPlano = cifrado.doFinal();
        //ya puedo escribir el archivo cifrado
        out.write(bufferPlano);
        
        in.close();
        out.close();
        
        
    }

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de cifrado DES para archios .txt");
        System.out.println("Cuidado con la llave solo debe de ser de 8 caracters");
        System.out.println("Compruebe que si cargo el fichero o archivo para cifrar");
        System.out.println("Amimir :3");
        
    }

    public static void mostrarBytes(byte[] buffer) {
       System.out.write(buffer, 0, buffer.length);
    }

    
}
