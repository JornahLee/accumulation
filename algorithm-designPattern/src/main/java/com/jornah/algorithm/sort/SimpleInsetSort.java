package com.jornah.algorithm.sort;

public class SimpleInsetSort {
	
	public static void main(String args[]) {
		int a[]= {4,55,12,51,5,1};
		sort(a);
		for(int n:a) {
			System.out.println(n);	
		}
		
	}
	public static void sort(int nums[]) {
		
		for(int i=1;i<nums.length;i++) {
			int j=i;
			int temp=nums[j];
			while(temp<nums[j-1]&&j-1>0) {
				j--;
				nums[j+1]=nums[j];
			}
			nums[j]=temp;
		}
		
		
	}

}
