package service;

import exceptions.AuctionDoesNotExists;
import exceptions.BidDoesNotExists;
import exceptions.NoBidsForAuctionFound;
import pojo.Auction;
import pojo.Bid;
import pojo.Buyer;

import javax.xml.bind.ValidationException;

public interface BiddingService
{
    /**
     *
     * @param buyer
     * @param auction
     * @param price
     * @return
     * @throws ValidationException
     */
    Bid placeBid(Buyer buyer, Auction auction, Double price) throws ValidationException;

    /**
     *
     * @param bid
     * @param priceS
     * @return
     * @throws BidDoesNotExists
     * @throws ValidationException
     */
    boolean updateBid(Bid bid, Double price) throws BidDoesNotExists, ValidationException;

    /**
     *
     * @param bid
     * @return
     * @throws BidDoesNotExists
     * @throws ValidationException
     */
    boolean withdrawBid(Bid bid) throws BidDoesNotExists, ValidationException;

    /**
     *
     * @param auction
     * @return
     * @throws AuctionDoesNotExists
     * @throws NoBidsForAuctionFound
     */
    Bid getWinningBid(Auction auction) throws AuctionDoesNotExists, NoBidsForAuctionFound;

}
