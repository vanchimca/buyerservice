package com.auction.buyerservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.auction.buyerservice.model.BidDetails;


public interface BidRepository extends MongoRepository<BidDetails, String>, BidRepositoryCustom {

		List<BidDetails> findByProductId(String productId);
}
