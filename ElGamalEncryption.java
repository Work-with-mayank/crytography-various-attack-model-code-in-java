7. ElGamal cryptosystem can be defined as the cryptography algorithm that uses the 
public and private key concepts to secure communication between two systems. It 
can be considered the asymmetric algorithm where the encryption and decryption 
happen by using public and private keys. To encrypt the message, the public key is 
used by the client, while the message could be decrypted using the private key on the 
server end. This is considered an efficient algorithm to perform encryption and 
decryption as the keys are extremely tough to predict. The sole purpose of introducing 
the message transaction’s signature is to protect it against MITM, which this algorithm 
could very effectively achieve. Write a program to implement Elgamal Cryptosystem to 
generate the pair of keys and then show the encryption & decryption of a given 
message. 
 
CODE:- 
 
import java.math.BigInteger;
import java.security.SecureRandom;
public class ElGamalEncryption {
 public static void main(String[] args) {
 KeyPair keyPair = generateKeyPair();
 BigInteger[] cipherText = encrypt("Hello", keyPair.getPublicKey());
 String decryptedMessage = decrypt(cipherText, keyPair.getPrivateKey())
 System.out.println("Public Key (p, g, y): " + keyPair.getPublicKey());
 System.out.println("Private Key (x): " + keyPair.getPrivateKey());
 System.out.println("Encrypted Message: " + cipherText[0] + ", " + cipherText[1]);
 System.out.println("Decrypted Message: " + decryptedMessage);
 }
 private static KeyPair generateKeyPair() {
 // Step 1: Select a large prime number p
 BigInteger p = new BigInteger("23");
 // Step 2: Select a primitive root g
 BigInteger g = new BigInteger("5");
 // Step 3: Choose private key x randomly in the range [1, p-2]
 BigInteger x = new BigInteger("6");
 // Step 4: Compute public key y = g^x mod p
 BigInteger y = g.modPow(x, p);
 return new KeyPair(new PublicKey(p, g, y), x);
 }
 private static BigInteger[] encrypt(String message, PublicKey publicKey) {
 BigInteger p = publicKey.getP();
 BigInteger g = publicKey.getG();
 BigInteger y = publicKey.getY();
 // Choose a random number k in the range [1, p-2]
 BigInteger k = new BigInteger(p.bitLength() - 2, new SecureRandom()).add(BigInteger.ONE);
 // Compute c1 = g^k mod p
 BigInteger c1 = g.modPow(k, p);
 // Compute c2 = (y^k * message) mod p
 BigInteger c2 = y.modPow(k, p).multiply(new BigInteger(message.getBytes())).mod(p);
 return new BigInteger[]{c1, c2};
 }
 private static String decrypt(BigInteger[] cipherText, BigInteger privateKey) {
 BigInteger p = privateKey;
 BigInteger c1 = cipherText[0];
 BigInteger c2 = cipherText[1];
 // Compute s = c1^x mod p
 BigInteger s = c1.modPow(privateKey, p);
 // Compute s_inv = s^(-1) mod p
 BigInteger sInv = s.modInverse(p);
 // Compute decrypted message = (c2 * s_inv) mod p
 BigInteger decryptedMessage = c2.multiply(sInv).mod(p)
 return new String(decryptedMessage.toByteArray());
 }
 static class KeyPair {
 private final PublicKey publicKey;
 private final BigInteger privateKey;
 public KeyPair(PublicKey publicKey, BigInteger privateKey) {
 this.publicKey = publicKey;
 this.privateKey = privateKey;
 }
 public PublicKey getPublicKey() {
 return publicKey;
 }
 public BigInteger getPrivateKey() {
 return privateKey;
 }
 static class PublicKey {
 private final BigInteger p;
 private final BigInteger g;
 private final BigInteger y
 public PublicKey(BigInteger p, BigInteger g, BigInteger y) {
 this.p = p;
 this.g = g;
 this.y = y;
 }
 public BigInteger getP() {
 return p;
 }
 public BigInteger getG() {
 return g;
 }
 public BigInteger getY() {
 return y;
 }
 @Override
 public String toString() {
 return "(" + p + ", " + g + ", " + y + ")";
 }
 }
}
