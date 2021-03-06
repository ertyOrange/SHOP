package com.lyq.model.product;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class ProductCategory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//类别编号
	
	private String name; //类别名字
	
	private int level=1;//层次
	
	private Set<ProductCategory> children;//只产品类别
	
	private ProductCategory parent;//父类别
	
	private Set<ProductInfo> products=new TreeSet<ProductInfo>();

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Set<ProductCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductCategory> children) {
		this.children = children;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

	public Set<ProductInfo> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductInfo> products) {
		this.products = products;
	}
	
	
	
}
