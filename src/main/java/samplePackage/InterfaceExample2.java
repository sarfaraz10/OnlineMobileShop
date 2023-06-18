package samplePackage;

interface Hello1{
	void hello1();
	void hello2();
	void hello3();
}

public class InterfaceExample2 implements Hello1{
	public void hello1() {
		System.out.println("hello1");
	}
	public void hello2() {
		System.out.println("hello2");
	}
	public void hello3() {
		System.out.println("hello2");
	}
	void hello4() {
		hello1();
		hello2();
		hello3();
	}
	public static void main(String args[]) {
		InterfaceExample2 ifac = new InterfaceExample2();
		ifac.hello1();
		ifac.hello2();
		ifac.hello3();
	}
}
