package com.web.util;

public class ActionUtil {
	/**
	 * 验证传入参数是否为空，现在支持String,Integer类型
	 * @param objs 输入多个参数
	 * @return boolean
	 */
	public static boolean validateNotNull(Object... objs){
		for(Object obj:objs){
		if(obj instanceof String){
			if(obj == ""){
				return false;
			}
		}else if(obj instanceof Integer){
			if(obj == ""){
				return false;
			}
			}
		}
		return true;
	}
}
