package samplePackage2;

public class Example1 {
	public static void main(String[] args) {
		try {
			int a = 9/0;
		}
		catch(Exception e) {
			System.out.println(e.toString()+" Not Runned");			
		}
		finally {
			System.out.println("Runned");
		}
	}
}

