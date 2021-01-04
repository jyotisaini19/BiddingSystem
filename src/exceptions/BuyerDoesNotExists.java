package exceptions;

import javax.xml.bind.ValidationException;

public class BuyerDoesNotExists extends ValidationException
{
    String message;
    public BuyerDoesNotExists(String message)
    {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return String.format("Buyer {} does not exits", this.message);
    }

}
