package service;

import enums.AuctionStatus;
import exceptions.AuctionDoesNotExists;
import pojo.Auction;
import pojo.Bid;
import pojo.Seller;

import java.util.HashSet;
import java.util.Set;

public class AuctionServiceImpl implements AuctionService
{

    Set<Auction> auctionList;
    BiddingService biddingService;

    public AuctionServiceImpl(BiddingService biddingService) {
        this.auctionList = new HashSet<>();
        this.biddingService = biddingService;
    }

    @Override
    public Auction createAuction(Seller seller, Double lowest, Double highest, Double fee)
    {
        Auction auction = new Auction(seller, highest, lowest, fee);
        seller.addAuction(auction);
        auctionList.add(auction);
        return auction;
    }

    @Override
    public Bid closeAuction(Auction auction) throws AuctionDoesNotExists
    {
        if (!auctionList.contains(auction)) {
            throw new AuctionDoesNotExists("Auction does not exits");
        }
        Bid bid = this.biddingService.getWinningBid(auction);
        auction.setStatus(AuctionStatus.CLOSED);
        return bid;
    }

}
