# Machine Coding Question:
### Implement Bidding System

Design an auction system Foobar where there’ll be sellers who can create an auction for the item they want to sell. They’ll be specifying the lowest and highest bid that can be placed. There will be a participation charge applicable to buyers who’ll be bidding for this auction.

The system should be able to handle multiple auctions at a time. Seller’s profit/loss will be calculated as: WinningBid + 20% of totalParticipationCostByBuyers – averageOfLowestAndHighestBid. Remaining 80% of participation cost goes to foobar as commission.

The highest unique bid will be the winning bid.. If for a particular auction these are the bids – buyer1 19, buyer2 19, buyer3 17, buyer4 17, buyer5 10: then buyer5 will be the winner. They required the following operations:

Create a seller
create a buyer
auction creation by a registered seller
bid on an auction by any registered buyer
buyer can update the bid amount
Withdraw bid
close an auction and return the winning bid
profit/loss of any seller till now
Brownie points for this: If any buyer has placed a bid on more than 2 auctions than he is a preferred buyer. So if in an auction there are two bids buyer1 19 and buyer2 19 and buyer2 is the preferred buyer then he is the winner. If both buyer1 and buyer2 are preferred buyers then fall back to the next highest bid and apply the same logic.