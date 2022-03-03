package com.auction.buyerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.buyerservice.kafka.KafkaConsumer;
import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.repository.BidRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class BuyerServiceQueryImpl implements BuyerServiceQuery{

	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	KafkaConsumer kafkaConsumer;
	
public List<BidDetails> retrieveBids(String productId) throws JsonProcessingException{
		
		//List<BidDetails> bidDetails =kafkaConsumer.retrieveBids(productId);
		List<BidDetails> bidDetails = bidRepository.findByProductId(productId);
		return bidDetails;
	}

public int getCount(String productId){
	  
	  int size = bidRepository.getCount(productId);
	  
	  return size;
}

public Boolean isBidPlaced(String productId, String mail) {
	   return bidRepository.isBidPalced(productId, mail);
}
}
