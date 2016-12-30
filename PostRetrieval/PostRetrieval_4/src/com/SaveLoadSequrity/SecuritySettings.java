package com.SaveLoadSequrity;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sergei on 07.06.2016.
 */
public final class SecuritySettings {

    /********************************************************************************/
    // класс для создания и хранения ключа
    private final static class MySecretKey implements SecretKey {

        private byte[] key = new byte[]{1, 2, 3, 4, 5, 6, 7, 8}; // ключ
        // не должен иметь длину более 8 байт, для безопасного шифрования его
        // необходимо изменить

        public String getAlgorithm() {
            return "DES";
        }

        public String getFormat() {
            return "RAW";
        }

        public byte[] getEncoded() {
            return key;
        }
    }

    private static SecretKey key;

    private static Cipher ecipher;
    private static Cipher dcipher;

    static {
        try {
            // генерируем ключ для шифрования.
            key = new MySecretKey();
            // Здесь мы создаем два объекта — один для шифрования, другой — дешифрования.
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            // в этих двух cтрочках мы задаем параметры для объектов и передаем туда ключ.
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /********************************************************************************/
    /**
     * Функция шифрования
     * @param str строка открытого текста
     * @return зашифрованная строка в формате Base64
     */

    /*В функцию передается строка для шифрования. Но, функции шифрования работают
    с битовыми массивами, поэтому строку переводим в битовый массив. Также, при
    создании строки играет роль кодировка. Чтобы избежать неприятностей, нужно
    использовать Base64-кодирование.*/
    public  String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    /********************************************************************************/
    /**
     * Функция расшифрования
     * @param str зашифрованная строка в формате Base64
     * @return расшифрованная строка
     */

   /* Здесь мы принимаем уже зашифрованную строку, которую побитово
    расшифровываем.*/
    public  String decrypt(String str)  {
        try {
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}