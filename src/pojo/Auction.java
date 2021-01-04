package pojo;

import enums.AuctionStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Auction
{
    Seller seller;
    Double higestBid;

    public Seller getSeller()
    {
        return seller;
    }

    public AuctionStatus getStatus()
    {
        return status;
    }

    Double lowerstBid;
    Double fee;
    AuctionStatus status;
    Set<Buyer> buyers;

    public Double getHigestBid()
    {
        return higestBid;
    }

    public Double getLowerstBid()
    {
        return lowerstBid;
    }


    public Auction(Seller seller, Double higestBid, Double lowerstBid, Double fee)
    {
        this.seller = seller;
        this.higestBid = higestBid;
        this.lowerstBid = lowerstBid;
        this.fee = fee;
        this.status = AuctionStatus.OPEN;
        this.buyers = new HashSet<Buyer>();
    }

    public void setStatus(AuctionStatus status)
    {
        this.status = status;
    }

    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    public void  removeBuyer(Buyer buyer) {
        buyers.remove(buyer);
    }

    public Double getParticipationFeeTotal() {
        return fee* this.buyers.size();
    }
}
