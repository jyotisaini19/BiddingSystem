import exceptions.AuctionDoesNotExists;
import pojo.Auction;
import pojo.Bid;
import pojo.Buyer;
import pojo.Seller;
import service.AuctionService;
import service.AuctionServiceImpl;
import service.BiddingService;
import service.BiddingServiceImpl;
import service.BuyerService;
import service.SellerService;
import validation.BidValidator;
import validation.BuyerValidator;

import javax.xml.bind.ValidationException;

public class Runner
{
    public static void main(String[] args) throws ValidationException
    {

        BuyerService buyerService = new BuyerService();

        BuyerValidator buyerValidator = new BuyerValidator(buyerService);
        BidValidator bidValidator = new BidValidator(buyerValidator);

        BiddingService biddingService = new BiddingServiceImpl(bidValidator);
        AuctionService auctionService = new AuctionServiceImpl(biddingService);

        SellerService sellerService = new SellerService(biddingService);
        Seller sellerOne = sellerService.createAccount("seller-one", "seller-one@gmail.com");


        Buyer buyerOne = buyerService.createAccount("buyer-one", "buyer-one@gmail.com");
        Buyer buyerTwo = buyerService.createAccount("buyer-two", "buyer-two@gmail.com");
        Buyer buyerThree = buyerService.createAccount("buyer-three", "buyer-three@gmail.com");



        Auction auctionOne = auctionService.createAuction(sellerOne, 10d, 40d, 5d);

        System.out.println("seller profit: " + sellerService.getSellerProfit(sellerOne));

        System.out.println("---- Placing bids ----");

        biddingService.placeBid(buyerOne, auctionOne, 20d);
        biddingService.placeBid(buyerTwo, auctionOne, 30d);
        Bid bidByThree = biddingService.placeBid(buyerThree, auctionOne, 20d);


        System.out.println( "seller profit: " + sellerService.getSellerProfit(sellerOne));

        System.out.println("Winnig bid is: " +  biddingService.getWinningBid(auctionOne));

        System.out.println("---- Updating Bid by buyer three ----");
        biddingService.updateBid(bidByThree, 40d);

        System.out.println( "seller profit: " + sellerService.getSellerProfit(sellerOne));

        System.out.println("Winnig bid is: " +  biddingService.getWinningBid(auctionOne));

        System.out.println("Auction closed: With Winning Bid: " + auctionService.closeAuction(auctionOne));

        biddingService.placeBid(buyerThree, auctionOne, 20d);
    }
}
