package pojo;

import java.util.HashSet;
import java.util.Set;

public class Buyer extends User
{
    Set<Auction> auctions;

    public Set<Auction> getAuctions()
    {
        return auctions;
    }

    public Buyer(String name, String email)
    {
        super(name, email);
        auctions = new HashSet<>();
    }

    /**
     * Method to check if a buyer is a preferred buyer.
     * @return
     */
    public boolean isPreferredBuyer() {
        return auctions.size() > 1;
    }

    /**
     * Method to add auction in buyers list.
     * @param auction
     */
    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    /**
     *
     * @param auction
     */
    public void removeAuction(Auction auction) {
        this.auctions.remove(auction);
    }

    @Override
    public String toString()
    {
        return "Buyer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
