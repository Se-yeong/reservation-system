package kr.or.reservation.domain;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.reservation.common.ToStringStyleCustom;

public class Product {
	private long id;
	private long categoryId;
	private String name;
	private String description;
	private Timestamp salesStart;
	private Timestamp salesEnd;
	private int salesFlag;
	private String event;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private String firstImageSaveFileName;

	private ProductDetail productDetail;
	private DisplayInfo displayInfo;
	private List<ProductImage> productImage;
	private List<ProductPrice> productPrices;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getSalesStart() {
		return salesStart;
	}

	public void setSalesStart(Timestamp salesStart) {
		this.salesStart = salesStart;
	}

	public Timestamp getSalesEnd() {
		return salesEnd;
	}

	public void setSalesEnd(Timestamp salesEnd) {
		this.salesEnd = salesEnd;
	}

	public int getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(int salesFlag) {
		this.salesFlag = salesFlag;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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

	public String getFirstImageSaveFileName() {
		return firstImageSaveFileName;
	}

	public void setFirstImageSaveFileName(String firstImageSaveFileName) {
		this.firstImageSaveFileName = firstImageSaveFileName;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<ProductImage> getProductImage() {
		return productImage;
	}

	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, new ToStringStyleCustom());
		// return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
