package com.wsyy.util;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.core.Conventions;
import org.springframework.util.Assert;

public class Model extends HashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Model() {
		
	}

	public Model(String attributeName, Object attributeValue) {
		addAttribute(attributeName, attributeValue);
	}

	/**
	 * 设置属性
	 *@author: TP 2019年8月1日
	 *@param:  attributeName 属性名；attributeValue 属性值
	 *@return: Model
	 */
	public Model addAttribute(String attributeName, Object attributeValue){
		Assert.notNull(attributeName, "model属性名不能为空！");
		put(attributeName,attributeValue);
		return this;
	}
	
	/**
	 * 设置返回结果附加信息和状态
	 *@author: TP 2019年8月1日
	 *@param:  message 说明信息；status 状态
	 *@return: Model
	 */
	public Model result(String message,Object status){
		put("message",message);
		put("status",status);
		return this;
	}
	
	/**
	 * 设置属性，属性名默认为传入参数的名称
	 *@author: TP 2019年8月1日
	 *@param:  attributeValue 设置属性值
	 *@return: Model
	 */
	@SuppressWarnings("rawtypes")
	public Model addAttribute(Object attributeValue){
		Assert.notNull(attributeValue, "model属性不能为空！");
		if (((attributeValue instanceof Collection)) && (((Collection)attributeValue).isEmpty())) {
			return this;
	    }
	    return addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
	}

	/**
	 * 设置集合属性的值
	 *@author: TP 2019年8月1日
	 *@param:  list 集合，属性名默认为list
	 *@return: Model
	 */
	public Model addAttribute(Collection<?> list){
		Assert.notNull(list, "model属性不能为空！");
		
	    return addAttribute("list", list);
	}
}
