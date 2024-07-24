package database;

public class DBException extends RuntimeException{

    private static final long serialVersionUUID = 1L;

    public DBException(String message) {
        super(message);
    }
}
