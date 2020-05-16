package com.jornah.javabase.functionOverload;

public class VariableAndArray {
	
	//conclusion:can't overLoad between
	//variable argument and Array
	public static void main(String args[]) {
		int a[]= {1,2,3};
		System.out.println(fun1(a));
		
	}
//	public String fun1(int ...args) {
//		return "Variable Args";
//	}
	public static String fun1(int args[]) {
		return "Array";
	}

}
