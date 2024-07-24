package database;

public class MySqlException extends RuntimeException{

    private static final long serialVersionUUID = 1L;

    public MySqlException (String message) {
        super(message);
    }
}
