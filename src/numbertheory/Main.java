package numbertheory;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		INumberTheory test = NumberTheoryImpl.getInstance();
		boolean exit = false;
		while(!exit){
			System.out.println("Enter 1 to make binary power with mod");
			System.out.println("Enter 2 to find Multiplicative inverse of a mod n");
			System.out.println("Enter 3 to make the mapping from large number A to (ai)");
			System.out.println("Enter 4 to make inverse mapping from (ai) to the large number A");
			System.out.println("Enter E to exit");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if(input.length() == 0 || input.length() >1){
				continue;
			}
			char choose = input.charAt(0);
			Character choos = Character.toLowerCase(choose);
			choose = (char)choos;
			switch (choose) {
			case '1':
			{
				System.out.println("Enter x , y , n to make (x^y)mod n ");
				BigInteger x = scanner.nextBigInteger();
				BigInteger y = scanner.nextBigInteger();
				BigInteger n = scanner.nextBigInteger();
				BigInteger result = test.binaryPowMod(x, y, n);
				System.out.println("Result = " + result);
				System.err.println();
				break;
			}
			case '2':
			{
				System.out.println("Enter a , n to find Multiplicative inverse of a mod n");
				long a , n;
				a = scanner.nextLong();
				n = scanner.nextLong();
				try{
					long result = test.mulInverse(a, n);
					System.out.println("Result = " + result);
				}catch (RuntimeException e){
					System.out.println(e.getMessage());
				}
				System.out.println();
				break;
			}
			case '3':
			{
				System.out.println("Enter M , k , mi[k] , A to find mapping of (a1,a2,a3,....,ak)");
				long M, k, A;
				M = scanner.nextLong();
				k = scanner.nextLong();
				long[] mi = new long[(int)k];
				for(int i=0;i<k;i++){
					mi[i] = scanner.nextLong();
				}
				A = scanner.nextLong();
				try{
					long[] result = test.mapFromAToai(M, mi, A);
					System.out.print("ai[] = [ " );
					for(int i=0;i<result.length - 1;i++){
						System.out.print(result[i] + " , ");
					}
					System.out.println(result[result.length -1 ] + " ]");
				}catch (RuntimeException e){
					System.out.println(e.getMessage());
				}
				System.out.println();
				break;
			}
			case '4':{
				System.out.println("Enter M , k , mi[k] , ai[k] to find inverse mapping A");
				long M, k;
				M = scanner.nextLong();
				k = scanner.nextLong();
				long[] mi = new long[(int)k];
				long[] ai = new long[(int)k];
				for(int i=0;i<k;i++){
					mi[i] = scanner.nextLong();
				}
				for(int i=0;i<k;i++){
					ai[i] = scanner.nextLong();
				}
				try{
					long result = test.CRT(M, mi, ai);
					System.out.println("A = " + result);
				}catch (RuntimeException e){
					System.out.println(e.getMessage());
				}
				System.out.println();
				break;
			}
			case 'e' :{
				exit = true;
				scanner.close();
				break;
			}
			default:
				System.out.println("Invalid input , try again !");
			}
		}
	}
}