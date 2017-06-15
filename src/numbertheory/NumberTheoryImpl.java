package numbertheory;

import java.math.BigInteger;

public class NumberTheoryImpl implements INumberTheory{
	
	static private NumberTheoryImpl numberTheoryInstance;
	private NumberTheoryImpl() {
	}
	
	/**
	 * this method is the simplest method to calculate power with mod.
	 * but it takes more time = o(exponent) = o(y).
	 * and it can cause overflow of result in case of large numbers.
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public long powModMethod1(long x, long y, long mod) {
		long result = 1;
		for(int i=0;i<y;i++){
			result *= x;
		}
		result %= mod;
		return result;
	}

	/**
	 * this method is the second simplest method to calculate power with mod.
	 * but it takes more time = o(exponent) = o(y).
	 * but it avoid overflow of result as we take mod at every step.
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public long powModMethod2(long x, long y, long mod) {
		long result = 1;
		for(int i=0;i<y;i++){
			result = ((result%mod)*(x%mod))%mod;
		}
		return result;
	}

	/**
	 * this method is the best way to calculate power with mod.
	 * and it takes less time = o(log(y)) = o(number of digits in binary representationof y).
	 * and it avoid overflow of result as we take mod at every step.
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public BigInteger binaryPowMod(BigInteger x, BigInteger y, BigInteger mod) {
		String binaryRepresentation = y.toString(2);
		BigInteger result = new BigInteger("1");
		for(int i=0;i<binaryRepresentation.length();i++){
			result = result.multiply(result).mod(mod);
			if(binaryRepresentation.charAt(i) == '1'){
				result = result.multiply(x).mod(mod);
			}
		}
		return result;
	}

	/**
	 * this method is the best way to calculate power with mod.
	 * and it takes less time = o(log(y)) = o(number of digits in binary representationof y).
	 * and it avoid overflow of result as we take mod at every step.
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public BigInteger fastPowMod(BigInteger x , BigInteger y, BigInteger mod){
		if(y.equals(new BigInteger("0")))return new BigInteger("1");
		BigInteger res = fastPowMod(x, y.divide(new BigInteger("2")), mod);
		if(y.divideAndRemainder(new BigInteger("2"))[1].equals(new BigInteger("0"))){
			return (res.mod(mod).multiply(res.mod(mod))).mod(mod);
		}
		else{
			return ((res.mod(mod).multiply(res.mod(mod))).mod(mod)).multiply(x.mod(mod)).mod(mod);
		}
	}

	/**
	 * a x + b y = gcd(a,b).
	 * @param a the number which we want to calculate its multiplicative inverse. 
	 * @param b the number which we make mod with.
	 * @return the array consisting of multiplicative inverse of a mod b ,
	 * number of multiples of b , gcd of a and b.
	 */
	private long[] extendedEuclidien(long a, long b){
		long x,y,g;
		long[] result = new long[3];
		if(b==0){
			result[0] = x = 1;
			result[1] = y = 0;
			result[2] = g = a; 
			return result;
		}
		result = extendedEuclidien(b,a%b);
		//new x = old y
		long temp = result[0];
		result[0] = x = result[1];
		//new y = old x - old y * (a/b)
		result[1] = y = temp - result[1]*(a/b);
		return result;
	}
	
	/**
	 * (a * mulInverse) (mod n) = 1 (mod n).
	 * @param a the number which we want to calculate its multiplicative inverse. 
	 * @param n the number which we make mod with.
	 * @return multiplicative inverse of a mod n.
	 */
	public long mulInverse(long a, long n){
		long[] result = extendedEuclidien(a, n);
		System.out.println("gcd(" + a + " , " + n + ") = " + result[2]);
		if(result[2]==1){
			if(result[0] < 0){
				return n - (Math.abs(result[0])%n);
			}
			return (result[0] + n)%n;
		}
		else{
			//a , n aren't relative primes 
			throw new RuntimeException("Two numbers ( " + a + " , " + n + " ) aren't relative prime");
		}
	}
	
	/**
	 * @param M large number.
	 * @param mi factorizing numbers for M. 
	 * @param A large number in range form 0 to M-1.
	 * @return mapping for A (a1,a2,a3,....,ak).
	 */
	public long[] mapFromAToai(long M, long[] mi, long A){
		long[] mappingOfA = new long[mi.length];
		long res = 1;
		for(int i=0;i<mi.length;i++){
			res*=mi[i];
		}
		if(res != M){
			throw new RuntimeException("Invalid factroizing for M");
		}
		for(int i=0;i<mi.length;i++){
			mappingOfA[i] = A%mi[i];
		}
		return mappingOfA;
	}
	
	/**
	 * @param M large number.
	 * @param mi factorizing numbers for M. 
	 * @param mappingOfA array of mapping numbers of large number A.
	 * @return large number A that has unique mapping of (a1,a2,a3,....ak).
	 */
	public long CRT(long M, long[] mi, long[] mappingOfA){
		long res = 1;
		for(int i=0;i<mi.length;i++){
			res*=mi[i];
		}
		if(res != M){
			throw new RuntimeException("Invalid factroizing for M");
		}
		
		long A =0;
		int k = mi.length;
		for(int i=0;i<k;i++){
			long Mi = M/mappingOfA[i];
			A += (Mi*mulInverse(Mi, mi[0])*mappingOfA[i])%M;
		}
		return A;
	}
	
	/**
	 * @param ai[] mapping of A.
	 * @param bi[] mapping of B. 
	 * @param mi[] mapping of M.
	 * @return result of A + B using mapping of A and mapping of B.
	 */
	public long add(long[] ai, long[] bi, long[] mi){
		long result = 0;
		for(int i=0;i<ai.length;i++){
			result += (ai[i] + bi[i])%mi[i];
		}
		return result;
	}

	
	/**
	 * singleton design pattern.
	 * @return an instance of NumberTheory class.
	 */
	static public NumberTheoryImpl getInstance(){
		if(numberTheoryInstance == null){
			numberTheoryInstance = new NumberTheoryImpl();
		}
		return numberTheoryInstance;
	}

}
