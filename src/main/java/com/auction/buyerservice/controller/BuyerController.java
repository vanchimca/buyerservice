package com.auction.buyerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auction.buyerservice.model.BidDetails;
import com.auction.buyerservice.service.BuyerService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BuyerController {

	@Autowired
	BuyerService buyerService;
	
	@PostMapping("/e-auction/api/v1/buyer/place-bid")
	public ResponseEntity<String> placeBid(@RequestBody BidDetails bidDetails){
		
		buyerService.saveBidDetails(bidDetails);
		System.out.println("id -- "+bidDetails.get_id());
		return  ResponseEntity.status(HttpStatus.OK).body("Saved Successfully");
	}
	
	@GetMapping("/e-auction/api/v1/buyer/bids/{productId}")
	public ResponseEntity<List<BidDetails>> retrieveBids(@PathVariable("productId") String productId)  
	{  
		List<BidDetails> bidDetails =buyerService.retrieveBids(productId);
		return  ResponseEntity.status(HttpStatus.OK).body(bidDetails);  
	}
	
	@PutMapping("/e-auction/api/v1/buyer/bids/{_id}/{mail}/{bidPrice}")
	public ResponseEntity<List<BidDetails>> updateBidPrice(@PathVariable("_id") String _id, @PathVariable String mail, @PathVariable Double bidPrice)  
	{  
		List<BidDetails> bidDetails =buyerService.updateBidPice(_id, mail, bidPrice);
		return  ResponseEntity.status(HttpStatus.OK).body(bidDetails);  
	}
	
	@GetMapping("/e-auction/api/v1/buyer/getCount/{productId}")
	public ResponseEntity<Integer> getCount(@PathVariable("productId") String productId)  
	{  
		int size =buyerService.getCount(productId);
		return  ResponseEntity.status(HttpStatus.OK).body(size);  
	}
}
