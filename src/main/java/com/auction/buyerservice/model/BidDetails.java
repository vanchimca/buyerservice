package com.auction.buyerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "biddetails")
public class BidDetails {

	@Id
    private String _id;
	
	String productId;
	Double bidAmount;
	String buyerFirstName;
	String buyerLastName;
	String address;
	String city;
	String state;
	Integer pin;
	Integer phone;
	String mail;
	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the bidAmount
	 */
	public Double getBidAmount() {
		return bidAmount;
	}
	/**
	 * @param bidAmount the bidAmount to set
	 */
	public void setBidAmount(Double bidAmount) {
		this.bidAmount = bidAmount;
	}
	/**
	 * @return the buyerFirstName
	 */
	public String getBuyerFirstName() {
		return buyerFirstName;
	}
	/**
	 * @param buyerFirstName the buyerFirstName to set
	 */
	public void setBuyerFirstName(String buyerFirstName) {
		this.buyerFirstName = buyerFirstName;
	}
	/**
	 * @return the buyerLastName
	 */
	public String getBuyerLastName() {
		return buyerLastName;
	}
	/**
	 * @param buyerLastName the buyerLastName to set
	 */
	public void setBuyerLastName(String buyerLastName) {
		this.buyerLastName = buyerLastName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the pin
	 */
	public Integer getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	/**
	 * @return the phone
	 */
	public Integer getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
