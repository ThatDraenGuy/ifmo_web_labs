package draen.exceptions;

public class UsernameTakenException extends DtoException{
    public UsernameTakenException(String message) {
        super(message);
    }
}
