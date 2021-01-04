package exceptions;

public class UserExistsException extends RuntimeException
{
    String message;

    public UserExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
