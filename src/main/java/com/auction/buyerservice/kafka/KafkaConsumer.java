package com.auction.buyerservice.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.auction.buyerservice.config.ApplicationConstants;
import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.repository.BidRepository;
import com.auction.buyerservice.service.BuyerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumer {
	
	/*
	 * @Autowired BuyerService buyerService;
	 */
	
	@Autowired
	BidRepository bidRepository;
	
	/*
	 * @Autowired BuyerService buyerService;
	 */
	
	@KafkaListener(groupId = ApplicationConstants.GROUP_ID_JSON, topics = ApplicationConstants.TOPIC_NAME, containerFactory = ApplicationConstants.KAFKA_LISTENER_CONTAINER_FACTORY)
	public List<BidDetails> retrieveBids(String productId) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<BidDetails> bidDetails =bidRepository.findByProductId(productId);
		
		return bidDetails;
	}
	
	@KafkaListener(groupId = ApplicationConstants.GROUP_ID_COMMAND, topics = ApplicationConstants.TOPIC_NAME_COMMAND, containerFactory = ApplicationConstants.KAFKA_LISTENER_CONTAINER_FACTORY)
	public String placeBids(BidDetails bidDetails) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		//List<BidDetails> bidDetails =bidRepository.findByProductId(productId);
		bidRepository.save(bidDetails);
		
		return "Bid Placed Successfully";
	}
}
