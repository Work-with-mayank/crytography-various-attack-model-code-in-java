12. Write a program to implement Rabin Miller Primality Test to check whether given 
number is prime or composite. 
CODE:-
import java.math.BigInteger;
import java.util.Random;
public class RabinMillerPrimalityTest 
{
 Public static void main(String[] args)
 {
 BigInteger number = new BigInteger("1125899839733759"); 
 int iterations = 5; // Number of iterations for the Rabin-Miller test
 boolean isPrime = isPrime(number, iterations);
 if (isPrime) {
 System.out.println(number + " is likely prime.");
 } else {
 System.out.println(number + " is composite.");
 }
 }
 static boolean isPrime(BigInteger n, int iterations) 
 {
 // Handle small numbers
 if (n.compareTo(BigInteger.valueOf(2)) == 0) {
 return true;
 }
 if (n.compareTo(BigInteger.valueOf(2)) < 0 || 
n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
 return false;
 }
 
 BigInteger d = n.subtract(BigInteger.ONE);
 int r = 0;
 while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
 d = d.divide(BigInteger.valueOf(2));
 r++;
 }
 // Rabin-Miller test
 Random rand = new Random();
 for (int i = 0; i < iterations; i++) {
 BigInteger a = new BigInteger(n.bitLength(), rand);
 a = a.mod(n.subtract(BigInteger.valueOf(2))).add(BigInteger.valueOf(2)); // Ensure 2 <= a < 
n-2
 BigInteger x = a.modPow(d, n);
 if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
 continue;
 }
 for (int j = 0; j < r - 1; j++) {
 x = x.modPow(BigInteger.valueOf(2), n);
 if (x.equals(BigInteger.ONE)) {
 return false; // Composite
 }
 if (x.equals(n.subtract(BigInteger.ONE))) {
 break;
 }
 }
 if (!x.equals(n.subtract(BigInteger.ONE))) {
 return false; // Composite
 }
 }
 return true; // Likely prime
 }
}
