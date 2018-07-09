package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.ceil;

/**
 * Created by Babu Sabin on 3/16/2017.
 */
public class GetKeyMessage {
    public int getMessageChoice(){
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your choice.\n1. Enter normal PlainText \n2. Enter Hex Value of PlainText ");
        choice = input.nextInt();
        if(choice==1||choice==2){
            return choice;
        }else{
            System.out.println("Invalid Choice.\nHave a good look at menu");
            return getMessageChoice();
        }
    }
    public int getKeyChoice(){
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("\n\nEnter your choice.\n1. Enter normal key of 8 character \n2. Enter Hex Value of key of 16 Hex Character ");
        choice = input.nextInt();
        if(choice==1||choice==2){
            return choice;
        }else{
            System.out.println("Invalid Choice.\nHave a good look at menu");
            return getKeyChoice();
        }

    }


    public String getMessage(int choice){
        System.out.println("Enter a Message : ");
        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        if(choice==2){
            if(!testForHex(message)){
                System.out.println("Invalid Hex Character. Please Enter Again.");
                return getMessage(choice);
            }
        }
        return message;
    }
    public String getKey(int choice){
        System.out.println("Enter a key: ");
        Scanner input = new Scanner(System.in);
        String key = input.nextLine();
        if(choice==1){
            if (key.length()!=8){
                System.out.println("Key  must be 8 character long.");
                return getKey(choice);
            }
        }
        if(choice==2){
            if (key.length()!=16){
                System.out.println("Key  must be 16 Hexadecimal Character.");
                return getKey(choice);
            }
            if(!testForHex(key)){
                System.out.println("Invalid Hex Character. Please Enter Again.");
                return getKey(choice);
            }
        }
        return key;
    }
    public List<String> splitMessage(String message, int choice){
        int messageBlock;
        float val;
        if(choice==1){
            val = (float)message.length()/8;
            messageBlock = (int) ceil(val);
        }else{
            val = (float)message.length()/16;
            messageBlock = (int) ceil(val);
        }
        int initialBitPosition = 0;
        int finalBitPosition = 0;
        List<String> splittedMessage = new ArrayList<String>();
        if(choice==1){
            while (message.length()<(messageBlock*8)){
                message = message + "Z";
            }
        }else{
            while (message.length()<(messageBlock*16)){
                message = message + "F";
            }
        }
        for(int i=0;i<messageBlock;i++){
            String eachBlock = null;
            if(choice==1){
                finalBitPosition = initialBitPosition+8;
                eachBlock = message.substring(initialBitPosition,finalBitPosition);
                initialBitPosition += 8;
            }if(choice==2){
                finalBitPosition = initialBitPosition + 16;
                eachBlock = message.substring(initialBitPosition,finalBitPosition);
                initialBitPosition += 16;
            }
            splittedMessage.add(eachBlock);
        }
        return splittedMessage;
    }

    public int[] getMessageBits(String message,int choice){
        int messageBits[] = new int[64];
        if(choice==1){
            for(int i=0;i<8;i++){
                char ch = message.charAt(i);
                int asciiValue = (int) ch;
                String ms = Integer.toBinaryString(asciiValue);
                while(ms.length()<8){
                    ms = "0" + ms;
                }
                for(int j=0;j<8;j++){
                    messageBits[(i*8)+j] = Integer.parseInt(ms.charAt(j)+"");
                }
            }
        }else{
            for(int i=0;i<16;i++){
                String ms = Integer.toBinaryString(Integer.parseInt(message.charAt(i)+"",16));
                while(ms.length()<4){
                    ms = "0" + ms;
                }
                for(int j=0;j<4;j++){
                    messageBits[(i*4)+j] = Integer.parseInt(ms.charAt(j)+"");
                }
            }
        }
        return messageBits;
    }

    public int[] getKeyBits(String key,int key_choice){
        int keyBits[] = new int[64];
        if(key_choice==1){
            for(int i=0;i<8;i++){
                char ch = key.charAt(i);
                int asciiValue = (int) ch;
                String ks = Integer.toBinaryString(asciiValue);
                while(ks.length()<8){
                    ks = "0" + ks;
                }
                for(int j=0;j<8;j++){
                    keyBits[(i*8)+j] = Integer.parseInt(ks.charAt(j)+"");
                }
            }
        }else{
            for(int i=0;i<16;i++){
                String ks = Integer.toBinaryString(Integer.parseInt(key.charAt(i)+"",16));
                while(ks.length()<4){
                    ks = "0" + ks;
                }
                for(int j=0;j<4;j++){
                    keyBits[(i*4)+j] = Integer.parseInt(ks.charAt(j)+"");
                }
            }
        }
        return keyBits;
    }

    public boolean testForHex(String value) {
        boolean ret;
        try {
            long t = Long.parseLong(value, 16);
            ret = true;
        } catch (NumberFormatException e) {
            ret = false;
        }
        return (ret);
    }

}
