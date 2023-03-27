package Classes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class EncryptDecrypt {

    /**
     * Encrypting an image file
     * @param fileName name of the file in a string
     */
    public static void encryptImage(String fileName) throws IOException {

        int key = 12345;    //encrypting key
        FileInputStream inputStream = new FileInputStream(fileName);    //create the file input stream to read the file
        byte[] data = new byte[inputStream.available()];    //get the file data to a list of bytes
        inputStream.read(data);
        int i = 0;
        for (byte b : data) {   //run a for loop to each of the list of byte
            data[i] = (byte)(b ^ key);  //encrypt the data by XOR operation
            i++;
        }
        FileOutputStream outputStream = new FileOutputStream(fileName); //create the file output stream to write the file
        outputStream.write(data);   //writing the data to the same file
        outputStream.close();
        inputStream.close();
    }

    /**
     * Decrypting an image file
     * @param fileName name of the file in a string
     */
    public static void decryptImage(String fileName) throws IOException {

        int key = 12345;    //decrypt key
        FileInputStream inputStream = new FileInputStream(fileName);    //create the file input stream to read the file
        byte[] data = new byte[inputStream.available()];    //get the file data to a list of bytes
        inputStream.read(data);
        int i = 0;
        for (byte b : data) {   //run a for loop to each of the list of byte
            data[i] = (byte)(b ^ key);  //decrypt the data by XOR operation
            i++;
        }
        FileOutputStream outputStream = new FileOutputStream(fileName); //create the file output stream to write the file
        outputStream.write(data);   //writing the data to the same file
        outputStream.close();
        inputStream.close();
    }

    /**
     * Encrypting patient notes
     * @param note patient note
     */
    public static String encryptPatientNotes(String note) {
        SecretKey secretKey = getSecretKey();   //call the getSecretKey to get the secretKey
        try {
            Cipher cipher = Cipher.getInstance("AES");  // create the Cipher object by specifying AES Advanced Encryption Standard algorithm
            return encryptString(note, secretKey, cipher);  //call the encryptString method and return the encrypted String
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) { //catch exceptions
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decrypting patient notes
     * @param encryptedNote getting the encrypted note
     */
    public static String decryptPatientNotes(String encryptedNote) {
        SecretKey secretKey = getSecretKey();   //call the getSecretKey to get the secretKey
        try {
            Cipher cipher = Cipher.getInstance("AES");  // create the Cipher object by specifying AES Advanced Encryption Standard algorithm
            return decryptString(encryptedNote, secretKey, cipher); //call the decryptString method and return the decrypted String
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**Getting the secret key from the file or create a key
     * @return SecretKey
     */
    public static SecretKey getSecretKey(){
        try {
            SecretKey secretKey = null; //create a null value secret  key
            File encryptDecryptKeyFile=new File("encryptDecryptKey.txt");   //create the encryptDecryptKey file
            if(encryptDecryptKeyFile.exists()){ //check if the file is exists
                Scanner read = new Scanner(encryptDecryptKeyFile);   //if file exist read input from file
                //run a while loop until reading file does not have content.
                while (read.hasNextLine()) {
                    String fileLine = read.nextLine();  //getting the data of the line
                    if (fileLine != null) { //check if the line is not null
                        secretKey=convertStringToSecretKey(fileLine);   //if not null get the secretKey
                    }
                }
            }else { //if the file is not there
                FileWriter fileWriter=new FileWriter(encryptDecryptKeyFile);    //create a fileWriter object
                secretKey=generateKey("AES");   //call the generateKey method with AES  Advanced Encryption Standard algorithm
                String key=convertSecretKeyToString(secretKey); //convert the key to string
                fileWriter.write(key);  //write the key to the file
                fileWriter.flush();
                fileWriter.close();
            }
            return secretKey;   //return the secret key
        } catch (IOException | NoSuchAlgorithmException e) {    //catch exceptions
            e.printStackTrace();
            return null;
        }
    }

    /**Generating the Key
     * @return SecretKey
     */
    public static SecretKey generateKey(String encryptionType){
        try{
            KeyGenerator keyGenerator=KeyGenerator.getInstance(encryptionType); //create an instance of key with the encryption algorithm
            SecretKey secretKey=keyGenerator.generateKey(); //make the secret key
            return  secretKey;  //return the secret key
        } catch (NoSuchAlgorithmException e) {  //catch exceptions
            e.printStackTrace();
            return null;
        }
    }

    /**Return the encrypted String
     * @return encrypted string
     */
    public static String encryptString(String dataToEncrypt, SecretKey secretKey, Cipher cipher){
        try {
            byte[] text=dataToEncrypt.getBytes(StandardCharsets.UTF_8); //get the list of byte of the string to be encrypted
            cipher.init(Cipher.ENCRYPT_MODE,secretKey); //initialize the Cipher object with encrypt mode and give the key
            byte[] textEncrypted= cipher.doFinal(text); //encrypt the byte list
            return Base64.getEncoder().encodeToString(textEncrypted);   //return the encrypted byte by converting to string
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) { //catch exceptions
            e.printStackTrace();
            return null;
        }
    }

    /**Return the decrypted String
     * @return decrypted string
     */
    public static String decryptString(String dataToDecrypt, SecretKey secretKey,Cipher cipher){
        try {
            cipher.init(Cipher.DECRYPT_MODE,secretKey); //initialize the Cipher object with encrypt mode and give the key
            byte[] textDecrypted=cipher.doFinal(Base64.getDecoder().decode(dataToDecrypt)); //decrypt the encrypted string
            return new String(textDecrypted);   //return the decrypted byte list by converting to string
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) { //catch exceptions
            e.printStackTrace();
            return null;
        }
    }

    /**Converting secret key to string
     * @return string
     */
    public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
        byte[] rawData = secretKey.getEncoded();    //encode the secret key in to bytes of list
        return Base64.getEncoder().encodeToString(rawData); //convert the bytes of list to string and return it
    }

    /**Converting string to secret key
     * @return SecretKey
     */
    public static SecretKey convertStringToSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey); //decode the string in to bytes of list
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");  //convert the bytes of list to SecretKey and return it
    }
}

//reference
//https://www.geeksforgeeks.org/encrypt-and-decrypt-image-using-java/
//https://www.baeldung.com/java-aes-encryption-decryption
//https://www.baeldung.com/java-cipher-class
//https://www.youtube.com/watch?v=nzUealgF0hs
//https://www.baeldung.com/java-secret-key-to-string
