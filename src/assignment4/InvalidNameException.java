package assignment4;

public class InvalidNameException extends Exception {

    public InvalidNameException(String message, int i)
    {
        System.out.println("Invalid name: " + message + ", Tweet number: " + (i+1));
    }


}
