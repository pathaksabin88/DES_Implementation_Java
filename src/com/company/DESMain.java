package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babu Sabin on 3/16/2017.
 */
public class DESMain {
    public static void main(String[] args) {
        GetKeyMessage getKeyMessage = new GetKeyMessage();
        int messageChoice = getKeyMessage.getMessageChoice();
        String message = getKeyMessage.getMessage(messageChoice);
        int keyChoice = getKeyMessage.getKeyChoice();
        String key = getKeyMessage.getKey(keyChoice);
        List<String> splittedMessage = getKeyMessage.splitMessage(message,messageChoice);
        String encryptedTextInHex = new String();
        String encryptedTextInASCII = new String();
        String decryptedTextInHex = new String();
        String decryptedTextInASCII = new String();
        List<int[]> cipherTextBitsList = new ArrayList<int[]>();
        int[] keyBits = getKeyMessage.getKeyBits(key,keyChoice);
        int block = 0;
        DESImplementation desImplementation = new DESImplementation();
        for(String eachBlock: splittedMessage){
            block += 1;
            System.out.println("Message Block"+block+" : "+eachBlock);
            int[] messageBits = getKeyMessage.getMessageBits(eachBlock,messageChoice);
            int[] cipherBits = desImplementation.encrypt(messageBits,keyBits);
            cipherTextBitsList.add(cipherBits);
            encryptedTextInHex += desImplementation.bitsToHex(cipherBits);
            encryptedTextInASCII += desImplementation.bitsToASCII(cipherBits);
        }
        System.out.println("\n\n\nFinal Encrypted Text in ASCII : \n"+encryptedTextInASCII);
        System.out.println("Final Encrypted Text in Hex : \n"+encryptedTextInHex.toUpperCase());

        System.out.println("\n\nNow Decryption\n\n\n ");

        for(int[] cipherTextBits: cipherTextBitsList ){
            int[] decryptedBits = desImplementation.decrypt(cipherTextBits,keyBits);
            decryptedTextInASCII += desImplementation.bitsToASCII(decryptedBits);
            decryptedTextInHex += desImplementation.bitsToHex(decryptedBits);
        }

        System.out.println("\n\n\nFinal Decrypted Text in ASCII : \n"+decryptedTextInASCII);
        System.out.println("Final Decrypted Text in Hex : \n"+decryptedTextInHex.toUpperCase());


    }
}
