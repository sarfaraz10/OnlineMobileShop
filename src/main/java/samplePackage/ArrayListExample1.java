package samplePackage;
import java.util.*;
public class ArrayListExample1 {
	public static void main(String args[]) {
		ArrayList<String> alist = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for(int i=0;i<n;i++) {
			alist.add(scanner.next());
		}
		while(true) {
			System.out.println("1.Add\n2.Update\n3.Delete\npress other to exit");
			int k = scanner.nextInt();
			if(k==1) {
				System.out.println("Enter to Add : ");
				alist.add(scanner.next());
			}
			else if(k==2) {

				System.out.println("Enter Position : ");
				int l = scanner.nextInt();
				System.out.println("Enter Value : ");
				alist.set(l,scanner.next());
			}
			else if(k==2) {

				System.out.println("Enter Position : ");
				alist.remove(scanner.nextInt());
			}
			else {
				break;
			}
			
		}
		System.out.println(alist);
	}
}
