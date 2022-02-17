package com.auction.buyerservice.repository;

import java.util.List;

import com.auction.buyerservice.model.BidDetails;



public interface BidRepositoryCustom {

	List<BidDetails> updateBidPrice(String _id, String mail, Double bidPrice);
}
