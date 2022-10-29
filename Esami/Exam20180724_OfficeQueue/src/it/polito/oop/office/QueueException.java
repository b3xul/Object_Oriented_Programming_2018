package it.polito.oop.office;

public class QueueException extends Exception {
    private static final long serialVersionUID = 1L;
    public QueueException() { }
    public QueueException(String msg) {
        super(msg);
    }
}
