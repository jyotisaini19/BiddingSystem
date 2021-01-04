package pojo;

import java.util.UUID;

public class Bid
{
    Buyer buyer;
    Auction auction;
    Double price;

    public Bid(Buyer buyer, Auction auction, Double price) {
        this.buyer = buyer;
        this.auction = auction;
        this.price = price;
    }

    public Buyer getBuyer()
    {
        return buyer;
    }

    public Auction getAuction()
    {
        return auction;
    }

    public Double getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Bid{" +
                "buyer=" + buyer +
                ", auction=" + auction +
                ", price=" + price +
                '}';
    }
}
