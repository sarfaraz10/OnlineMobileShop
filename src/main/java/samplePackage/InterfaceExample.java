package samplePackage;

interface ForExample{
	void run();
}
public class InterfaceExample{
	InterfaceExample(){
		System.out.println("test");
	}
	
	InterfaceExample(int a){
		this();
		System.out.println("test1 "+a );
	}
	
	public static void main(String args[]) {
		new InterfaceExample(5);
	}
}
