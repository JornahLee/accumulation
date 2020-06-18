package com.jornah.thread.threadlocal;

public class Run  implements Runnable{
	
	static int flag=1;
	ThreadLocal<Person> tlocal=new ThreadLocal<Person>();
	public Run() {
		tlocal.set(new Person("person1"));
	}
	@Override
	public void run() {
		if(flag==1) {
			System.out.println(tlocal.get());
			
		}
		if(flag==2) {
			System.out.println(tlocal.get());
		}
		
	}
	public static void main(String args[]) throws InterruptedException {
		Run r=new Run();
		new Thread(r).start();
		flag=2;
		new Thread(r).start();
	}
	

}
