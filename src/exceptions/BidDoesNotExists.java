package exceptions;

public class BidDoesNotExists extends RuntimeException
{
    String message;

    public BidDoesNotExists(String message) {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
