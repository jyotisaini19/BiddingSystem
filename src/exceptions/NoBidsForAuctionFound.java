package exceptions;

public class NoBidsForAuctionFound extends RuntimeException
{
    String message;

    public NoBidsForAuctionFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
