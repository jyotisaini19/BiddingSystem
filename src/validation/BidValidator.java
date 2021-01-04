package validation;

import enums.AuctionStatus;
import exceptions.InvalidBiddingAmount;
import pojo.Auction;
import pojo.Bid;
import pojo.Buyer;

import javax.xml.bind.ValidationException;

public class BidValidator implements Validator<Bid>
{
    BuyerValidator buyerValidator;

    public BidValidator(BuyerValidator buyerValidator) {
        this.buyerValidator = buyerValidator;
    }

    @Override
    public boolean validate(Bid obj) throws ValidationException
    {
       Buyer buyer = obj.getBuyer();

       buyerValidator.validate(buyer);

       Auction auction = obj.getAuction();

       if (auction.getStatus() == AuctionStatus.CLOSED || auction.getStatus() == AuctionStatus.CANCELLED) {
            throw new ValidationException("Auction not OPEN!!");
       }

       Double price =  obj.getPrice();

       if (price > auction.getHigestBid() || price < auction.getLowerstBid()) {
           throw new InvalidBiddingAmount(String.format("Price must be between %f and %f", auction.getLowerstBid(), auction.getHigestBid()));
       }

       return true;
    }
}
