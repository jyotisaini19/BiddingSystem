package validation;

import exceptions.BuyerDoesNotExists;
import pojo.Buyer;
import service.BuyerService;

import javax.xml.bind.ValidationException;

public class BuyerValidator implements Validator<Buyer>
{

    BuyerService buyerService;

    public BuyerValidator(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @Override
    public boolean validate(Buyer buyer) throws BuyerDoesNotExists
    {
        if (buyerService.isBuyerPresent(buyer)) {
            return true;
        }

        throw new BuyerDoesNotExists(buyer.getEmail());
    }
}
