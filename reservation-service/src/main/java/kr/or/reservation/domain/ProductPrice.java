package kr.or.reservation.domain;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.reservation.common.ToStringStyleCustom;

public class ProductPrice {

	private long id;
	private long productId;
	private PriceType priceType;
	private long price;
	private String discountRate;
	private Timestamp createDate;
	private Timestamp modifyDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, new ToStringStyleCustom());
	}

}
