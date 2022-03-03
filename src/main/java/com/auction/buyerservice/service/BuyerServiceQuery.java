package com.auction.buyerservice.service;

import java.util.List;

import com.auction.buyerservice.model.BidDetails;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BuyerServiceQuery {
	public List<BidDetails> retrieveBids(String productId) throws JsonProcessingException;
	public int getCount(String productId);
	public Boolean isBidPlaced(String productId, String mail);
}
