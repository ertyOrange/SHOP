package com.lyq.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 性别的枚举
 * @author yy263
 *
 */
public enum Sex {
	BOY {
		@Override
		public String getName(){
			return "男";
		}
	},
	
	GIRL{
		@Override
		public String getName(){
			return "女";
		}
	},
	
	ALL{
		@Override
		public String getName(){
			return "不限";
		}
	};
	/**
	 * 获取名称的抽象方法
	 */
	
	public abstract String getName();
	
	public static List<String> getValues(){
		List<String> list=new ArrayList<String>();
		
		for(Sex sex:Sex.values()){
			list.add(sex.getName());
		}
		
		return list;
	}
}
