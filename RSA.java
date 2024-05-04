11. Alice uses a Bob’s public key for sending a confidential message. Alice select a 
two large prime numbers to generate a private and public key so that eve could not 
break the ciphertext. So as a developer implement this Algorithm to generate a pair of 
keys and each message should be encrypted by different key pairs.
CODE:-
import java.math.BigInteger;
import java.util.Random;
public class RSA
{
 public static void main(String[] args) 
 {
 KeyPair bobKeys = generateKeyPair();
 String message = "Hello Bob!";
 BigInteger ciphertext = encrypt(message, bobKeys.publicKey);
 String decryptedMessage = decrypt(ciphertext, bobKeys.privateKey);
 System.out.println("Original Message: " + message);
 System.out.println("Encrypted Message: " + ciphertext);
 System.out.println("Decrypted Message: " + decryptedMessage);
 }
 static KeyPair generateKeyPair() {
 BigInteger p = generateLargePrime();
 BigInteger q = generateLargePrime();
 BigInteger n = p.multiply(q);
 BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
 BigInteger e = BigInteger.valueOf(65537);
 BigInteger d = e.modInverse(phi);
 return new KeyPair(new PublicKey(n, e), new PrivateKey(n, d));
 }
 static BigInteger encrypt(String message, PublicKey publicKey) 
 {
 return new BigInteger(message.getBytes()).modPow(publicKey.e, publicKey.n);
 }
 static String decrypt(BigInteger ciphertext, PrivateKey privateKey)
 {
 return new String(ciphertext.modPow(privateKey.d, privateKey.n).toByteArray());
 }
 static BigInteger generateLargePrime()
 {
 return BigInteger.probablePrime(512, new Random());
 }
}
class KeyPair {
 PublicKey publicKey;
 PrivateKey privateKey;
 KeyPair(PublicKey publicKey, PrivateKey privateKey) 
{
 this.publicKey = publicKey;
 this.privateKey = privateKey;
 }
}
class PublicKey {
 BigInteger n;
 BigInteger e;
 PublicKey(BigInteger n, BigInteger e)
{
 this.n = n;
 this.e = e;
 }
}
class PrivateKey 
{
 BigInteger n;
 BigInteger d;
 PrivateKey(BigInteger n, BigInteger d)
 
{
 this.n = n;
 this.d = d;
 }
}