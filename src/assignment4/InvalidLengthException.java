package assignment4;

public class InvalidLengthException extends Exception {

    public InvalidLengthException(String message, int i)
    {
        System.out.println("Invalid length. Name of tweet: " + message + ", Tweet number: " + (i+1));
    }


}
