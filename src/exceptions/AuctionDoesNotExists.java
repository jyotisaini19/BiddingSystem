package exceptions;

import javax.xml.bind.ValidationException;

public class AuctionDoesNotExists extends ValidationException
{
    String message;

    public AuctionDoesNotExists(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }
}
