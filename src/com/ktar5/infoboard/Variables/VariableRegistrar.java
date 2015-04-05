package com.ktar5.infoboard.Variables;

import java.util.HashMap;
import java.util.Map;

public class VariableRegistrar {

	private static final Map<String, Variable<?>> variableRegistrar  = new HashMap<String, Variable<?>>();

	public static boolean registerVariable(String name, Variable<?> variable){
		if(containsVariable(name.toUpperCase())){
			return false;
		}else{
			variableRegistrar.put(name.toUpperCase(), variable);
			return true;
		}
	}

	public static boolean unregisterVariable(String name){
		if(containsVariable(name.toUpperCase())){
			variableRegistrar.remove(name.toUpperCase());
			return true;
		}else{
			return false;
		}
	}

	public static Variable<?> getVariable(String name){
		if(containsVariable(name.toUpperCase())){
			return variableRegistrar.get(name.toUpperCase());
		}else{
			return null;
		}
	}

	public static boolean containsVariable(String name){
		return variableRegistrar.containsKey(name.toUpperCase());
	}

}
