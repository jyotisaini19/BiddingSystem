package service;

import exceptions.AuctionDoesNotExists;
import pojo.Auction;
import pojo.Bid;
import pojo.Seller;

public interface AuctionService
{
    /**
     *
     * @param seller
     * @param lowest
     * @param highest
     * @param fee
     * @return
     */
    public Auction createAuction(Seller seller, Double lowest, Double highest, Double fee);

    /**
     *
     * @param auction
     * @return
     * @throws AuctionDoesNotExists
     */
    public Bid closeAuction(Auction auction) throws AuctionDoesNotExists;

}
