package service;

import exceptions.UserExistsException;
import pojo.Auction;
import pojo.Bid;
import pojo.Seller;
import pojo.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SellerService implements UserService<Seller>
{
    Map<String, User> sellers;

    BiddingService biddingService;

    public SellerService(BiddingService biddingService) {
        this.sellers = new HashMap<>();
        this.biddingService = biddingService;
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public Seller createAccount(String name, String email)
    {
        if (sellers.containsKey(email)) {
            throw new UserExistsException(String.format("Seller {} exists",email));
        }
        Seller seller = new Seller(name, email);
        sellers.put(email,seller);
        return seller;
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean isSellerPresent(User user) {
        return this.sellers.containsKey(user.getEmail());
    }

    /**
     *
     * @param seller
     * @return
     */
    public Double getSellerProfit(Seller seller)
    {
        Set<Auction> auctions = seller.getAuctions();
        Double profit = 0d;

        for (Auction auction : auctions) {
            try {
                Bid winningBid = biddingService.getWinningBid(auction);
                profit += winningBid.getPrice() + (((0.2) * auction.getParticipationFeeTotal()) - ( (auction.getHigestBid() + auction.getLowerstBid()) / 2));
            } catch (Exception e) {
                continue;
            }
        }
        return profit;
    }
}
