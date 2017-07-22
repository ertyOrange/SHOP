package com.lyq.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyq.dao.product.ProductCategoryDao;
import com.lyq.dao.product.ProductDao;
import com.lyq.dao.product.UpLoadFileDao;
import com.opensymphony.xwork2.ActionSupport;
public class BaseAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;

	protected Integer id;
	protected Integer[] ids;
	protected int pageNo = 1;
	protected int pageSize = 3;

	public static final String LIST = "list";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String SELECT = "select";
	public static final String QUERY = "query";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String INDEX = "index";
	public static final String MAIN = "main";
	public static final String MANAGER = "manager";
	public static final String TOP = "top";
	public static final String REG = "reg";
	public static final String USER_LOGIN = "userLogin";
	public static final String CUSTOMER_LOGIN = "customerLogin";
	public static final String LOGOUT = "logout";

	// 注入Dao
	@Autowired
	protected ProductCategoryDao categoryDao;
	@Autowired
	protected ProductDao productDao;
	@Autowired
	protected UpLoadFileDao uploadFileDao;

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	@Override
	public void setApplication(Map<String, Object> application) {
		// 获取Map类型的application赋值
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// 获取Map类型的session赋值
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// 获取Map类型的request赋值
		this.request = request;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	// 处理方法
	public static String getList() {
		return LIST;
	}

	public static String getEdit() {
		return EDIT;
	}

	public static String getAdd() {
		return ADD;
	}

	public static String getSelect() {
		return SELECT;
	}

	public static String getQuery() {
		return QUERY;
	}

	public static String getLeft() {
		return LEFT;
	}

	public static String getRight() {
		return RIGHT;
	}

	public static String getIndex() {
		return INDEX;
	}

	public static String getMain() {
		return MAIN;
	}

	public static String getManager() {
		return MANAGER;
	}

	public static String getTop() {
		return TOP;
	}

	public static String getReg() {
		return REG;
	}

	public static String getUserLogin() {
		return USER_LOGIN;
	}

	public static String getCustomerLogin() {
		return CUSTOMER_LOGIN;
	}

	public static String getLogout() {
		return LOGOUT;
	}

	

	
	
	
}
