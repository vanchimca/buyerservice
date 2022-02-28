package com.auction.buyerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auction.buyerservice.kafka.KafkaConsumer;
import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.service.BuyerService;
import com.fasterxml.jackson.core.JsonProcessingException;




@RestController
@CrossOrigin(origins = {"http://localhost:4200",  "http://localhost:8090"})
public class BuyerController {

	@Autowired
	BuyerService buyerService;
	
	@Autowired
	private ConcurrentKafkaListenerContainerFactory<String, BidDetails> factory;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	/*
	 * @Autowired private KafkaConsumer kafkaConsumer;
	 */
	
	@PostMapping("/e-auction/api/v1/buyer/place-bid")
	public ResponseEntity<String> placeBid(@RequestBody BidDetails bidDetails){
		
		if(buyerService.isBidPlaced(bidDetails.getProductId(), bidDetails.getMail())) {
			return  ResponseEntity.status(HttpStatus.OK).body("Bid can not be placed by the same user for the same product");
		}else {
			buyerService.saveBidDetails(bidDetails);
			return  ResponseEntity.status(HttpStatus.OK).body("Saved Successfully");
			
		}
	}
	
	@GetMapping("/e-auction/api/v1/buyer/bids/{productId}")
	public ResponseEntity<List<BidDetails>> retrieveBids(@PathVariable("productId") String productId) throws JsonProcessingException  
	{  
		/*List<BidDetails> bidDetails =buyerService.retrieveBids(productId);
		//kafkaTemplate.send(ApplicationConstants.TOPIC_NAME, productId);
		return  ResponseEntity.status(HttpStatus.OK).body(bidDetails);*/
		/*
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
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		List<BidDetails> bidDetails =buyerService.retrieveBids(productId);
		return  ResponseEntity.status(HttpStatus.OK).body(bidDetails);
	}
	
	
	@GetMapping("/e-auction/api/v1/buyer/updateBid/{_id}/{mail}/{bidPrice}")
	public ResponseEntity<String> updateBidPrice(@PathVariable("_id") String _id, @PathVariable String mail, @PathVariable Double bidPrice)  
	{  
		List<BidDetails> bidDetails =buyerService.updateBidPice(_id, mail, bidPrice);
		return  ResponseEntity.status(HttpStatus.OK).body("Bid amount updated successfully");  
	}
	
	@GetMapping("/e-auction/api/v1/buyer/getCount/{productId}")
	public ResponseEntity<Integer> getCount(@PathVariable("productId") String productId)  
	{  
		int size =buyerService.getCount(productId);
		return  ResponseEntity.status(HttpStatus.OK).body(size);  
	}
}
