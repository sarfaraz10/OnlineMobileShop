package samplePackage;

class ExampleException extends Exception{
	public ExampleException(String s) {
		super(s);
	}
}
class ExampleExceptionEx{
	void hello1() {
		try {
			throw new ExampleException("hello da mapla ");
		}
		catch(ExampleException e) {
			System.out.println(e.toString()+" \n"+e.getMessage());
		}
		finally {
			System.out.println("Hello Execution complete");
		}
	}
}

public class ExceptionExample {
	public static void main(String args[]) {
		try {
			ExampleExceptionEx ex = new ExampleExceptionEx();
			ex.hello1();
			throw new ExampleException("vanakkam da mapla ");
		}
		catch(ExampleException e) {
			System.out.println(e.toString()+" \n"+e.getMessage());
		}
		finally {
			System.out.println("Execution complete");
		}
	}
}
