package com.jornah.collection;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionTest {
	
	class Inner{
		String name;
		public Inner(String str) {
			name=str;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "Inner [name=" + name + "]";
		}
		
	}
	/*
	 * quote is in collection
	 */
	@Test
	public void test1() {
		HashMap<String,Inner> map=new HashMap<String,Inner>();
		Inner inner = new Inner("ada");
		map.put("inner",inner);
		map.put("inner2",inner);
		inner.setName("asdad");
		
		for(String s:map.keySet()) {
			System.out.println(map.get(s));
		}
		
		
	}
	@Test
	public void testIterator() {
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		for(String str:list) {
			if("1".equals(str)) {
				list.remove(str);
			}
		}
		
		Map<String,String> map=new HashMap<>();
		map.put("key1", "value1");
		for(String str:map.keySet()) {
			String string = map.get("key1");
		}
		for(Entry<String, String> set:map.entrySet()) {
			set.getValue();
		}
		
		
	}

}
