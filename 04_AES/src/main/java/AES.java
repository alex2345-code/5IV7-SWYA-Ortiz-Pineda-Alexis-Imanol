/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexisimanolortizpineda
 */

import java.security.*;

//vamos a ocupar a spec para la generacion de las subllaves

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;



public class AES {
    
    //llave
    public static final byte[] keyvalue  = new byte[]{
        /*
        la llave puede ser de 128, 
        128     16 caracteres      9 
        192     24 caracteres      11
        256     32 caracteres      13
        */
        'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 
        'q', 'w', 'e', 'r', 't', 'y', 'u', 'i',
        'q', 'w', 'e', 'r', 't', 'y', 'u', 'i'
    };
    
    private static final String instancia = "AES";
    
    //vmaos a crear los metodos para el cifrado 
    
    public static String encrypt(String Data) throws Exception {
        // para poder cifrar debemos de generar las llaves 
        // pero vamos a crear un metodo para dicha generacion 
        Key key = generateKey();
        
        //inicialisamos el cifrado 
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.ENCRYPT_MODE, key);
        
        //vamos a obtener el mensaje y transformarlo en bytes 
        byte[] encValores = cifrado.doFinal(Data.getBytes());
        
        //vamos a paplicar un formato para que pueda ser leido el mensaje cifrado 
        //vamos aplicar el formta de codificacion base 64
        System.out.println("Valores sin formato:" + encValores);
        
        String valoresencriptados = new BASE64Encoder().encode(encValores);
        
        return valoresencriptados;
        
    }
    
    
    //vamos a crear los metodos para el descifrado 
    
    public static String decrypt(String valoresencriptados) throws Exception {
        // para poder cifrar debemos de generar las llaves 
        // pero vamos a crear un metodo para dicha generacion 
        Key key = generateKey();
        
        //inicialisamos el cifrado 
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.DECRYPT_MODE, key);
        
        
        //primero debemos de saber con que formato viene 
        byte [] decodificadordevalores = new BASE64Decoder().decodeBuffer(valoresencriptados);
        
        //vamos a obtener el mensaje y transformarlo en bytes 
        byte[] decValores = cifrado.doFinal(decodificadordevalores);
        
        //vamos a paplicar un formato para que pueda ser leido el mensaje cifrado 
        //vamos aplicar el formta de codificacion base 64
        System.out.println("Valores sin formato:" + decValores);
        
        String valoresdescifrados = new String(decValores);
        
        return valoresdescifrados;
        
    }
    
   
    private static Key generateKey() throws Exception {
        //vamos a ocupar las llaves de aes 
        //de la clase secret KeySpec
        
        Key key = new SecretKeySpec(keyvalue, instancia);
        return key;
        
    }
    
    
}
