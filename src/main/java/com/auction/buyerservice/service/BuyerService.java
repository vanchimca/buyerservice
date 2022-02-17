package com.auction.buyerservice.service;

import java.util.List;
import java.util.Optional;

import com.auction.buyerservice.model.BidDetails;



public interface BuyerService {

	public String saveBidDetails(BidDetails bidDetails);
	public List<BidDetails> retrieveBids(String productId);
	public List<BidDetails> updateBidPice(String _id, String mail, Double bidPrice);
	
}
