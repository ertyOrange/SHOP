/**
 * 
 */
/**
 * @author yy263
 *
 */
package com.lyq.action.interceptor;

import java.util.Map;

import com.lyq.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CustomerLoginInteceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context= invocation.getInvocationContext();
		Map<String ,Object> session=context.getSession();
		if(session.get("customer")!=null){
			return invocation.invoke();// 调用执行方法
		}
		return BaseAction.CUSTOMER_LOGIN;
	}
	
}