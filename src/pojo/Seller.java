package pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Seller extends User
{

    Set<Auction> auctions;

    public Seller(String name, String email) {
        super(name, email);
        auctions = new HashSet<>();
    }

    public Set<Auction> getAuctions()
    {
        return auctions;
    }

    public void addAuction(Auction auction) {
        auctions.add(auction);
    }
}
