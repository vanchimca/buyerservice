package com.auction.buyerservice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        
        List<BidDetails> bidDetails = mongoTemplate.find(query, BidDetails.class);
        
        return bidDetails;

    }
	
	@Override
    public int getCount(String productId) {
		Query query = new Query();
		
		query.addCriteria(Criteria.where("productId").exists(true));
		
		List<BidDetails> bidDetails = mongoTemplate.find(query, BidDetails.class);
		
		return bidDetails.size();
		
    }
}
