package service;

import exceptions.AuctionDoesNotExists;
import exceptions.BidDoesNotExists;
import exceptions.NoBidsForAuctionFound;
import pojo.Auction;
import pojo.Bid;
import pojo.Bids;
import pojo.Buyer;
import validation.BidValidator;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BiddingServiceImpl implements BiddingService
{

    Map<Auction, Bids> auctionBidsMap;
    Map<Buyer, Integer> buyerBidCount;
    BidValidator bidValidator;

    public BiddingServiceImpl(BidValidator bidValidator)
    {
        this.auctionBidsMap = new HashMap<>();
        this.buyerBidCount = new HashMap<>();
        this.bidValidator = bidValidator;
    }

    @Override
    public Bid placeBid(Buyer buyer, Auction auction, Double price) throws BidDoesNotExists, ValidationException
    {
        Bid bid = new Bid(buyer, auction, price);
        bidValidator.validate(bid);
        // update the buyer bids count..
        addBidToBuyersCount(bid);
        // add bid to auctions mapping.
        addBidForAuction(bid);
        // add buyer to this auction..
        auction.addBuyer(buyer);
        return bid;
    }

    @Override
    public boolean updateBid(Bid bid, Double price) throws ValidationException
    {
        withdrawBid(bid);
        placeBid(bid.getBuyer(), bid.getAuction(), price);
        return true;
    }

    @Override
    public boolean withdrawBid(Bid bid) throws ValidationException
    {
        bidValidator.validate(bid);
        removeBidFromBuyerCount(bid);
        removeBidForAuction(bid);
        bid.getAuction().removeBuyer(bid.getBuyer());
        return true;
    }

    @Override
    public Bid getWinningBid(Auction auction) throws AuctionDoesNotExists, NoBidsForAuctionFound
    {

        if (!this.auctionBidsMap.containsKey(auction) || this.auctionBidsMap.get(auction).getBidsMapping().size() == 0) {
            throw new NoBidsForAuctionFound(String.format("No bids for Auction {} ", auction));
        }

        Bids bids = this.auctionBidsMap.get(auction);


        for (Map.Entry<Double, Set<Buyer>> entry: bids.getBidsMapping().entrySet()) {
            Double price = entry.getKey();
            Set<Buyer> buyers = entry.getValue();

            Buyer preferredBuyer = null;


            // Just 1 buyers.. declare winner!!!.
            if (buyers.size() == 1) {
                for (Buyer b: buyers) {
                    preferredBuyer = b;
                }
            } else  {
                // Find if only one preferred buyer..
                int pBuyers = 0 ;

                for (Buyer b : buyers) {

                    if (pBuyers > 1) {
                        break;
                    }

                    if (b.isPreferredBuyer()) {
                        preferredBuyer = b;
                        pBuyers++;
                    }
                }
            }

            if (preferredBuyer != null) {
                return new Bid(preferredBuyer, auction, price);
            }
        }


        return getDefaultWinner(bids, auction);
    }

    public Bid  getDefaultWinner(Bids bids, Auction auction) {

        for (Map.Entry<Double, Set<Buyer>> entry : bids.getBidsMapping().entrySet()) {
            for (Buyer b : entry.getValue()) {
                return new Bid(b, auction, entry.getKey());
            }
        }
        return null;
    }

    public void addBidForAuction(Bid bid) {
        Buyer buyer = bid.getBuyer();
        Auction auction = bid.getAuction();
        double price = bid.getPrice();

        // put this bid in bids for this auction.
        Bids bidsMapping = auctionBidsMap.getOrDefault(auction, new Bids());
        TreeMap<Double, Set<Buyer>> bidBuyerMapping = bidsMapping.getBidsMapping();
        Set<Buyer> buyers = bidBuyerMapping.getOrDefault(price, new HashSet<>());
        buyers.add(buyer);

        bidBuyerMapping.put(price, buyers);
        auctionBidsMap.put(auction, bidsMapping);
    }

    public void removeBidForAuction(Bid bid) {
        Buyer buyer = bid.getBuyer();
        Auction auction = bid.getAuction();
        double price = bid.getPrice();

        // put this bid in bids for this auction.
        Bids bidsMapping = auctionBidsMap.getOrDefault(auction, new Bids());
        TreeMap<Double, Set<Buyer>> bidBuyerMapping = bidsMapping.getBidsMapping();
        Set<Buyer> buyers = bidBuyerMapping.get(price);

        if (buyers.size() == 1) {
            bidBuyerMapping.remove(price);
        } else {
            buyers.remove(buyer);
            bidBuyerMapping.put(price, buyers);
            auctionBidsMap.put(auction, bidsMapping);
        }
    }

    public void addBidToBuyersCount(Bid bid) {
        Buyer buyer = bid.getBuyer();
        buyer.addAuction(bid.getAuction());
        buyerBidCount.put(buyer, buyerBidCount.getOrDefault(buyer, 0) + 1);
    }

    public void removeBidFromBuyerCount(Bid bid) {
        Buyer buyer = bid.getBuyer();
        buyer.removeAuction(bid.getAuction());
        buyerBidCount.put(buyer, buyerBidCount.getOrDefault(buyer, 1) - 1);
    }
}
