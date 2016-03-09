package su.vpb.limit;

public class InvalidBoundsException extends Exception {
    public InvalidBoundsException(){
        System.out.println("The beginning of the interval is after the end of the interval.");
    }
}
