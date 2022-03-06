package com.auction.buyerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.service.BuyerService;
import com.auction.buyerservice.service.BuyerServiceQuery;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin(origins = {"http://eauction.s3-website-us-east-1.amazonaws.com/",  "http://ec2-3-88-0-13.compute-1.amazonaws.com:8090"})

public class BuyerQueryController {
	
	@Autowired
	BuyerServiceQuery buyerService;
	
	@Autowired
	private ConcurrentKafkaListenerContainerFactory<String, BidDetails> factory;
	
	@GetMapping("/e-auction/api/v1/buyer/bids/{productId}")
	public ResponseEntity<List<BidDetails>> retrieveBids(@PathVariable("productId") String productId) throws JsonProcessingException  
	{  
		/*List<BidDetails> bidDetails =buyerService.retrieveBids(productId);		
		return  ResponseEntity.status(HttpStatus.OK).body(bidDetails);*/
		
		/*kafkaTemplate.send(ApplicationConstants.TOPIC_NAME, productId);
		 * ConsumerFactory consumerFactory = factory.getConsumerFactory();
		 * Consumer<String, BidDetails> consumer = consumerFactory.createConsumer(); try
		 * { consumer.subscribe(Arrays.asList(ApplicationConstants.TOPIC_NAME));
		 * ConsumerRecords consumerRecords = consumer.poll(0);
		 * Iterable<ConsumerRecord<String, BidDetails>> records =
		 * consumerRecords.records(ApplicationConstants.TOPIC_NAME);
		 * Iterator<ConsumerRecord<String, BidDetails>> iterator = records.iterator();
		 * 
		 * while (iterator.hasNext()) { bidDetails.add(iterator.next().value()); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }*/
		 
		 //Working Kafka
		List<BidDetails> bidDetails =buyerService.retrieveBids(productId);
		return  ResponseEntity.status(HttpStatus.OK).body(bidDetails);
	}
	
	@GetMapping("/e-auction/api/v1/buyer/getCount/{productId}")
	public ResponseEntity<Integer> getCount(@PathVariable("productId") String productId)  
	{  
		int size =buyerService.getCount(productId);
		return  ResponseEntity.status(HttpStatus.OK).body(size);  
	}
}
