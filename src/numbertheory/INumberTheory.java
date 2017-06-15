package numbertheory;

import java.math.BigInteger;

public interface INumberTheory {

	/**
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public long powModMethod1(long x, long y, long mod);

	/**
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public long powModMethod2(long x, long y, long mod);

	/**
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public BigInteger binaryPowMod(BigInteger x , BigInteger y, BigInteger mod);

	/**
	 * @param x the base of power.
	 * @param y the exponent of power. 
	 * @param mod the number which we make mode with.
	 * @return BigInteger number which is the result of ((x^y)%mod).
	 */
	public BigInteger fastPowMod(BigInteger x , BigInteger y, BigInteger mod);
		
	/**
	 * (a * mulInverse) (mod n) = 1 (mod n).
	 * @param a the number which we want to calculate its multiplicative inverse. 
	 * @param n the number which we make mod with.
	 * @return multiplicative inverse of a mod n.
	 */
	public long mulInverse(long a, long n);
	
	/**
	 * @param M large number.
	 * @param mi factorizing numbers for M. 
	 * @param A large number in range form 0 to M-1.
	 * @return mapping for A (a1,a2,a3,....,ak).
	 */
	public long[] mapFromAToai(long M, long[] mi, long A);
	
	/**
	 * @param M large number.
	 * @param mi factorizing numbers for M. 
	 * @param mappingOfA array of mapping numbers of large number A.
	 * @return large number A that has unique mapping of (a1,a2,a3,....,ak).
	 */
	public long CRT(long M, long[] mi, long[] mappingOfA);
	
	/**
	 * @param ai[] mapping of A.
	 * @param bi[] mapping of B. 
	 * @param mi[] mapping of M.
	 * @return result of A + B using mapping of A and mapping of B.
	 */
	public long add(long[] ai, long[] bi, long[] mi);

}
