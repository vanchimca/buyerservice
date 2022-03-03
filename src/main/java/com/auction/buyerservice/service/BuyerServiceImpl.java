package com.auction.buyerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.buyerservice.kafka.KafkaConsumer;
import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.repository.BidRepository;
import com.fasterxml.jackson.core.JsonProcessingException;



@Service
public class BuyerServiceImpl implements BuyerService{
	
	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	KafkaConsumer kafkaConsumer;

	public String saveBidDetails(BidDetails bidDetails) {
		
		bidDetails = bidRepository.save(bidDetails);
		return "Saved Succesfully";
	}
	
	
	  public List<BidDetails> updateBidPice(String _id, String mail, Double bidAmount){
		  
		  List<BidDetails> bidDetails = bidRepository.updateBidPrice(_id, mail, bidAmount);
		  
		  return bidDetails;
	}
	  
	
}
