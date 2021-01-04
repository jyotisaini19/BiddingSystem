package exceptions;

public class InvalidBiddingAmount extends RuntimeException
{
    String message;

    public InvalidBiddingAmount(String message) {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
