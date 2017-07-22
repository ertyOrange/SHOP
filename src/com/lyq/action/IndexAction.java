package com.lyq.action;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyq.model.product.ProductCategory;
import com.lyq.model.product.ProductInfo;
@Scope("prototype")
@Controller("indexAction")
public class IndexAction  extends BaseAction{

	private List<ProductCategory> categories;
	private List<ProductInfo> product_commend;
	private List<ProductInfo> product_sellCount;
	private List<ProductInfo> product_clickCount;
	
	@Override
	public String execute() throws Exception {
		//��ѯ�������
		String where="where parent is null";
		categories= categoryDao.find(-1,-1, where,null).getList();
		
		//��ѯ�Ƽ�����Ʒ
		product_commend=productDao.findCommend();
		
		//��ѯ������õ���Ʒ
		product_sellCount=productDao.findSellCount();
		
		//��ѯ������ߵ���Ʒ
		product_clickCount=productDao.findClickcount();
		return SUCCESS;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public List<ProductInfo> getProduct_commend() {
		return product_commend;
	}

	public void setProduct_commend(List<ProductInfo> product_commend) {
		this.product_commend = product_commend;
	}

	public List<ProductInfo> getProduct_sellCount() {
		return product_sellCount;
	}

	public void setProduct_sellCount(List<ProductInfo> product_sellCount) {
		this.product_sellCount = product_sellCount;
	}

	public List<ProductInfo> getProduct_clickCount() {
		return product_clickCount;
	}

	public void setProduct_clickCount(List<ProductInfo> product_clickCount) {
		this.product_clickCount = product_clickCount;
	}
	
	
	
	
	
}
