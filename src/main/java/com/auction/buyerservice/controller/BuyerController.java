package com.auction.buyerservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.client.RestTemplate;

import com.auction.buyerservice.config.ApplicationConstants;
import com.auction.buyerservice.kafka.KafkaConsumer;
import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.service.BuyerService;
import com.auction.buyerservice.service.BuyerServiceQuery;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin(origins = {"http://eauction.s3-website-us-east-1.amazonaws.com/",  "http://sellerservice.us-east-1.elasticbeanstalk.com/"})
public class BuyerController {

	@Autowired
	BuyerService buyerService;
	
	@Autowired
	BuyerServiceQuery buyerServiceQuery;

	@Autowired
	private ConcurrentKafkaListenerContainerFactory<String, BidDetails> factory;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	/*
	 * @Autowired private KafkaConsumer kafkaConsumer;
	 */

	@PostMapping("/e-auction/api/v1/buyer/place-bid")
	public ResponseEntity<String> placeBid(@RequestBody BidDetails bidDetails) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("productId", bidDetails.getProductId());
		// calling the currency exchange service
		ResponseEntity<Boolean> responseEntity = new RestTemplate().getForEntity(
				"http://sellerservice.us-east-1.elasticbeanstalk.com/e-auction/api/v1/seller/bidEligible/{productId}", Boolean.class, uriVariables);
		if (responseEntity.getBody()) {
			if (buyerServiceQuery.isBidPlaced(bidDetails.getProductId(), bidDetails.getMail())) {
				return ResponseEntity.status(HttpStatus.OK)
						.body("Bid can not be placed by the same user for the same product");
			} else {
				buyerService.saveBidDetails(bidDetails);
				// kafkaTemplate.send(ApplicationConstants.TOPIC_NAME_COMMAND, bidDetails);
				return ResponseEntity.status(HttpStatus.OK).body("Saved Successfully");
			}
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Bid can not be added after bid end date");
		}

	}


	@GetMapping("/e-auction/api/v1/buyer/updateBid/{_id}/{mail}/{bidPrice}/{productId}")
	public ResponseEntity<String> updateBidPrice(@PathVariable("_id") String _id, @PathVariable String mail,
			@PathVariable Double bidPrice, @PathVariable String productId) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("productId", productId);
		// calling the currency exchange service
		ResponseEntity<Boolean> responseEntity = new RestTemplate().getForEntity(
				"http://sellerservice.us-east-1.elasticbeanstalk.com/e-auction/api/v1/seller/bidEligible/{productId}", Boolean.class, uriVariables);
		if (responseEntity.getBody()) {
			List<BidDetails> bidDetails = buyerService.updateBidPice(_id, mail, bidPrice);
			return ResponseEntity.status(HttpStatus.OK).body("Bid amount updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Bid can not be updated after bid end date");
		}

	}
}
