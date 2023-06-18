package samplePackage;

public class ConstructorExample {
	
	ConstructorExample(){
		this(5);
		System.out.println("Empty");
	}
	
	ConstructorExample(int a){
		System.out.println("Parameter");
	}
	
	public static void main(String[] args) {
		new ConstructorExample();
		// TODO Auto-generated method stub
	}

}
