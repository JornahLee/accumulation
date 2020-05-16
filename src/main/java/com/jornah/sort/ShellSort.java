package com.jornah.sort;

public class ShellSort {
	public static void main(String args[]) {
		int a[]= {4,55,12,51,5};
		sort(a);
		for(int n:a) {
			System.out.println(n);	
		}
		
	}
	
	public static void sort(int nums[]) {
		int h=nums.length/2;
		while(h>1) {// grouping the array
			
			for(int t=0;t<h;t++) {
				
				// sort in one group: insert Sort
				for(int i=t+h;i<nums.length;i+=h) {
					int j=i;
					int temp=nums[i];
					while(temp<nums[j-h]) {
						//element back shift
						//find the correct position
						j-=h;
						nums[j+h]=nums[j];
					}
					nums[j]=temp;
				}
					
			}
			
			h/=2;
		}
	}

}
