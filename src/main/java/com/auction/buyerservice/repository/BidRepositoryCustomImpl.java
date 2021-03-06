package com.auction.buyerservice.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.auction.buyerservice.model.BidDetails;
import com.mongodb.client.result.UpdateResult;

public class BidRepositoryCustomImpl implements BidRepositoryCustom{

	@Autowired
    MongoTemplate mongoTemplate;
	
	@Override
    public List<BidDetails> updateBidPrice(String _id, String mail, Double bidPrice) {

        Query query = new Query();
        
        query.addCriteria(Criteria
				.where("_id").exists(true)
				.andOperator(Criteria.where("mail").is(mail),
						Criteria.where("_id").is(_id)));
        
        Update update = new Update();
        update.set("bidAmount", bidPrice);

        UpdateResult result = mongoTemplate.updateFirst(query, update, BidDetails.class);
        
		/*
		 * List<BidDetails> bidDetails = mongoTemplate.find(query, BidDetails.class);
		 * return bidDetails;
		 */
        return mongoTemplate.find(query, BidDetails.class).stream().collect(Collectors.toList());
        

    }
	
	@Override
    public int getCount(String productId) {
		Query query = new Query();
		
		query.addCriteria(Criteria.where("productId").exists(true)
				.andOperator(Criteria.where("productId").is(productId)));
		
		List<BidDetails> bidDetails = mongoTemplate.find(query, BidDetails.class);
		
		return bidDetails.size();
		
    }
	
	@Override
	public List<BidDetails> findByProductId(String productId){
		
		Query query = new Query();
		query.with(PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC,"bidAmount")));
		query.addCriteria(Criteria.where("productId").exists(true)
				.andOperator(Criteria.where("productId").is(productId)));
		
		
		List<BidDetails> bidDetails = mongoTemplate.find(query, BidDetails.class);
		
		return bidDetails;
		
	}
	
	@Override
	public Boolean isBidPalced(String productId, String mail) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").exists(true)
				.andOperator(Criteria.where("productId").is(productId),Criteria.where("mail").is(mail)));
				//.andOperator(Criteria.where("mail").is(mail)));
		
		List<BidDetails> bidDetails = mongoTemplate.find(query, BidDetails.class);
		if(bidDetails.size() > 0) {
			return true;
		}else {
			return false;
		}
		
		
	}
}
