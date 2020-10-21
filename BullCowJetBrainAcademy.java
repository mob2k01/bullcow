package bullscows;
import java.util.*;

public class BullCowJetBrainAcademy {
    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        String slength = null;
        int length ;
        int lenghtLetter;
        try {
            slength = scanner.nextLine();
            length = Integer.valueOf(slength);
            System.out.println("Input the number of possible symbols in the code:");
            slength = scanner.nextLine();
            lenghtLetter = Integer.valueOf(slength);
            if(length > lenghtLetter){
                System.out.println("Error: it's not possible to generate a code with a length of " + length +" with " + lenghtLetter +" unique symbols.");
            } else if (lenghtLetter > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else if (length == 0 || lenghtLetter == 0) {
                System.out.println("Error");
            } else {
                System.out.print("The secret is prepared: ");
                generateAsterix(length);
                generateChar(lenghtLetter);
                System.out.println("Okay, let's start a game!");
                gamelog(length,lenghtLetter);
            }
        } catch (Exception e) {
            System.out.println("Error: \"" + slength + "\" isn't a valid number.");
        }
    }
    public static void generateChar(int lenghtLetter){
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
        System.out.println(" (0-9, a-"+alphabet.charAt(lenghtLetter-1)+").");
    }
    public static void generateAsterix(int lenght){
        for ( int i = 0 ; i < lenght ; i++ ){
            System.out.print("*");
        }
    }
    public static String generateLetter(int length, int lengthLetter) {
        StringBuilder digits= new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz"/*""zyxwvutsrqponmlkjihgfedcba9876543210"*/);

        String secret = "";

        Random rand = new Random();

        while( secret.length() < length ) {
            int i = rand.nextInt(lengthLetter);
            secret += digits.charAt(i);
            digits.deleteCharAt(i);
        }

        return secret;
    }
    public static boolean check(String secret, String answer){
        int bull=0, cow=0;
        char[] s = secret.toCharArray();
        char[] a = answer.toCharArray();
        for (int i = 0;i < s.length;i++){
            for (int j = 0;j < a.length;j++){
                if (s[i] == a[j] && i == j) {
                    bull++;
                }
                if (s[i] == a[j] && i != j) {
                    cow++;
                }
            }
        }
        if ( bull==s.length ){
            System.out.println("Grade: "+bull+" bulls.");
            System.out.println("Congrats! You guessed the secret code.");
            return true;
        }
        if ( bull==0 && cow==0 ){
            System.out.print("None. ");
            return false;
        } else if ( bull==0 ){
            System.out.print("Grade: "+cow+" cows. ");
            return false;
        } else if ( cow==0 ){
            System.out.print("Grade: "+bull+" bulls. ");
            return false;
        } else if ( bull==1 ){
            System.out.print("Grade: "+bull+" bull and "+cow+" cows. ");
            return false;
        } else if ( cow==1 ){
            System.out.print("Grade: "+bull+" bulls and "+cow+" cow. ");
            return false;
        } else {
            System.out.print("Grade: "+bull+" bulls and "+cow+" cows. ");
            return false;
        }
    }
    public static void gamelog(int lenght, int lenghtNumber){
        boolean res = false;
        int i=1;
        if (lenght > 10) {
            System.out.println("Error: can't generate a secret number with a length of " + lenghtNumber
                    + " because there aren't enough unique digits.");
        } else {
            String secret = generateLetter(lenght,lenghtNumber);
            try (Scanner scanner = new Scanner(System.in)) {
                String answer = " ";
                while (!res) {
                    System.out.println("\nTurn " + i + ". Answer:");
                    answer = scanner.nextLine();
                    res = check(secret, answer);
                    i++;
                }
            }
        }
    }
}
