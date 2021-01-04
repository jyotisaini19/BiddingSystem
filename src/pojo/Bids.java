package pojo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Bids
{
    protected TreeMap<Double, Set<Buyer>> bidsMapping;

    public TreeMap<Double, Set<Buyer>> getBidsMapping()
    {
        return bidsMapping;
    }

    public  Bids() {
        this.bidsMapping = new TreeMap<>(Collections.reverseOrder());
    }
}
