package samplePackage;
/*public class Sample1 {
	
	protected int b=5;
	void run() {
		System.out.println("Runs in sample1 class");
	}
	
	void display() {
		
	}
	
}
public class Sample2 extends Sample1 {
	void run() {
		System.out.println("Runs in sample2 class "+b);
		display();
	}
	void run(int a) {
		System.out.println(a);
	}
}*/


abstract class abstractClass{
    abstract void run1();
    abstract void run2();
    abstract void run3();
    void concrete1(){
        System.out.println("concrete1");
    }
    void concrete2(){
        System.out.println("concrete2");
    }
}


public class Sample extends abstractClass{
    void run1(){
        System.out.println("run1");
    }
    void run2(){
        System.out.println("run2");
    }

    void run3(){
        System.out.println("run3");
    }
	
	public static void main(String args[]) {
		System.out.println("Main");
		/*
		 * Sample2 s1 = new Sample2(); Sample1 s2 = new Sample1(); Sample1 s3 = new
		 * Sample2(); s1.run(); s2.run(); s1.run(5); s3.run();
		 */
	}
	
	static{
		System.out.println("static");
	}
	
}

