package com.jornah.javabase.aboutextend;
//public class Zi extends Fu{
public class Zi{
	public String name="Fu111";
    static {
        System.out.println("eclipse static Zi");
    }
    {
        System.out.println("eclipse construct Zi:::: classLoader::"+this.getClass().getClassLoader());
    }
	public void show(){
		System.out.println("Zi eclipse show");
		
		//System.out.println("-------------");
		//System.out.println("showFather show:");
		//super.show();
		//System.out.println("-------------");
	}
	public void show2(){
	System.out.println("Zi eclipse show2");
	}
//    @Override
    public void showShowShow() {
        System.out.println("Zi eclipse showSHowSHow");
        
    }

}