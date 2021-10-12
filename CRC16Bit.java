import java.util.ArrayList;
import java.util.Scanner;

public class CRC16Bit {

    //Declaring the constent Polinomial that sender and reciver agreed on 
     static final int[] agreedPolynomial = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        // ArrayList<Integer> messageModified = new ArrayList<>();
        
        // message Input
        System.out.print( " [+] Enter message to send: ");
        String messageString = input.next();      

        //Printing agreed polynomial b/w sender and reciver
        System.out.print("\n Agreed 16 bit polynomial b/w Sender and Reciver : ");
        for (Integer digit : agreedPolynomial) {
            System.out.print(digit);
        }      

        // Sender
        String sentMessage = Sender(messageString);

        System.out.println("\n [>>] Sending message : " + sentMessage);
        // Reciver
        boolean recivedMessage = Reciver(sentMessage);
        if(recivedMessage)
            System.out.println("\n [>>] Message recived with no error  ");
        else
        System.out.println("\n [>>] Message recived with error ");


    }


// Sender function to generate checksum and generating send message

    static String Sender(String messageString){

        String sendingMessage = "";
        ArrayList<Integer> messageModified = new ArrayList<>();

        //Converting String to array list
        for (int i = 0; i < messageString.length(); i++) {
            messageModified.add( Integer.valueOf( Character.toString(messageString.charAt(i))));
            
        }
        
        // If the input data bit is less than agreed polynomial we add 16 0's to message
        if( messageModified.size() < agreedPolynomial.length){
            
            for (int i = 0; i < (agreedPolynomial.length-1) ; i++) {
                messageModified.add(0);
                
            }            
        }

        //division is done by this function and returns check sum
        ArrayList<Integer> checkSum = Divider( messageModified);

        System.out.print("\n [>>] The checksum is : ");
        //converting arrayList to string
        for (Integer digit : checkSum) {
            System.out.print(digit);
            sendingMessage += digit;
        }

        return messageString + sendingMessage;
    }

    static Boolean Reciver (String recivedStringMessage){

        ArrayList<Integer> messageModified = new ArrayList<>();
        String recivedMessage = "";
        boolean isSuccessfull = true;
        

        //Converting String to array list
        for (int i = 0; i < recivedStringMessage.length(); i++) {
            messageModified.add( Integer.valueOf( Character.toString(recivedStringMessage.charAt(i))));
            
        }

        ArrayList<Integer> checkSum = Divider(messageModified);

        for (Integer digit : checkSum) {
            recivedMessage += digit;
            if( digit !=0)
                isSuccessfull = false;
        }

        System.out.println("\n [>>] Recived message checksum : " + recivedMessage);

        return isSuccessfull;

    }

    static ArrayList<Integer> Divider(ArrayList<Integer> message) {

        ArrayList<Integer> checkSum = new ArrayList<>();
        ArrayList<Integer> tempResult = new ArrayList<>();
        
        for (int i = 0; i < agreedPolynomial.length; i++) {
            checkSum.add(message.get(i));
        }

        for (int i = agreedPolynomial.length; i < message.size(); i++) {

             if( checkSum.get(0) == 1){
                 for (int j = 0; j < agreedPolynomial.length; j++) {
                     tempResult.add( agreedPolynomial[j] ^ checkSum.get(j));
                 }

                 tempResult.remove(0);
                 tempResult.add(message.get(i));
                 checkSum.clear();
                 checkSum.addAll(tempResult);
             }
             else{
                 checkSum.add(message.get(i));
                 checkSum.remove(0);
             }
           
             tempResult.clear();

            
        }
        checkSum.remove(0);
        return checkSum;
    }

    // Reciver function to see whether got message has error or not 
    
}
