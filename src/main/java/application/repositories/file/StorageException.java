package application.repositories.file;

/**
 * Created by matan,
 * On 23/09/2016.
 */

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}