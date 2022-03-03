package com.auction.buyerservice.service;

import java.util.List;
import java.util.Optional;

import com.auction.buyerservice.model.BidDetails;
import com.fasterxml.jackson.core.JsonProcessingException;



public interface BuyerService {

	public String saveBidDetails(BidDetails bidDetails);
	public List<BidDetails> updateBidPice(String _id, String mail, Double bidPrice);
	
}
