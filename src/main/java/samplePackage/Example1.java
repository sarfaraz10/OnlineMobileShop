package samplePackage;


class Example2{
	Example2(){
		System.out.println("Inside example 2");
	}
	static {
		System.out.println("in hello 3");
	}
}

public class Example1 {
	static{
		Example2 ex2 = new Example2();
		System.out.println("in hello 2");
	}
	Example1(){
		System.out.println("default");
	}
	Example1(int a){
		new Example1();
	}
	public static void main(String args[]) {
		System.out.println("in main");
		new Example1(5);
	}
	static{
		System.out.println("in hello 1");
	}
}
