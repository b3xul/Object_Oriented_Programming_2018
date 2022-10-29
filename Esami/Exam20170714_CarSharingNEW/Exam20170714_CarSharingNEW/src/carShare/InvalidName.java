package carShare;

public class InvalidName extends Exception {
    private static final long serialVersionUID = 1L;
    public InvalidName(){}
    public InvalidName(String message){super(message);}
}
