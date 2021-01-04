package service;

import pojo.Buyer;
import pojo.User;

import java.util.HashMap;
import java.util.Map;

public class BuyerService implements UserService<Buyer>
{
    Map<String, User> buyers;

    public BuyerService() {
        this.buyers = new HashMap<>();
    }

    @Override
    public Buyer createAccount(String name, String email)
    {
        Buyer buyer = new Buyer(name, email);
        buyers.put(email, buyer);
        return buyer;
    }

    /**
     *
     * @param buyer
     * @return
     */
    public boolean isBuyerPresent(Buyer buyer) {
        return this.buyers.containsKey(buyer.getEmail());
    }
}
