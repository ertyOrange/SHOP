package com.lyq.model.product;

import java.io.Serializable;
import java.util.Date;

import com.lyq.model.Sex;

public class ProductInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private String description;
	private Date createTime =new Date();
	private Float baseprice;
	private Float marketprice;
	private Float sellprice;
	private Sex sexrequest;
	private Boolean commend=false;//是否推荐商品（默认为false）
	private Integer clickcount=1;//访问量（统计受欢迎的程度）
	private Integer sellCount=0; //销售的数量（统计热销的产品）
	private ProductCategory category;//所类别
	private UpLoadFile upLoadFile;		//上传文件
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Float getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(Float baseprice) {
		this.baseprice = baseprice;
	}
	public Float getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(Float marketprice) {
		this.marketprice = marketprice;
	}
	public Float getSellprice() {
		return sellprice;
	}
	public void setSellprice(Float sellprice) {
		this.sellprice = sellprice;
	}
	public Sex getSexrequest() {
		return sexrequest;
	}
	public void setSexrequest(Sex sexrequest) {
		this.sexrequest = sexrequest;
	}
	public Boolean getCommend() {
		return commend;
	}
	public void setCommend(Boolean commend) {
		this.commend = commend;
	}

	public Integer getClickcount() {
		return clickcount;
	}
	public void setClickcount(Integer clickcount) {
		this.clickcount = clickcount;
	}

	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public UpLoadFile getUpLoadFile() {
		return upLoadFile;
	}
	public void setUpLoadFile(UpLoadFile upLoadFile) {
		this.upLoadFile = upLoadFile;
	}
	public Integer getSellCount() {
		return sellCount;
	}
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}
	
	
	
	
	
}
