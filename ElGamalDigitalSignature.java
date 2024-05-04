10. Alice is of one of the employee of company XYZ. He wants to ensure that whatever 
data he is sending to Bob should be checked for accuracy. Implement the Elgamal 
digital signature for the message "Hello how are you” for showing the Digital Signature 
in such a scenario. 
 
CODE:- 
 
import java.security.*;
public class ElGamalDigitalSignature 
{
 public static void main(String[] args) throws Exception
 {
 KeyPair keyPair = generateKeyPair();
 PrivateKey privateKey = keyPair.getPrivate();
 PublicKey publicKey = keyPair.getPublic();
 String message = "Hello, how are you";
 byte[] signature = sign(message, privateKey);
 boolean isVerified = verify(message, signature, publicKey);
 System.out.println("Message: " + message);
 System.out.println("Signature: " + bytesToHex(signature));
 System.out.println("Signature Verified: " + isVerified);
 }
 private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
 return KeyPairGenerator.getInstance("ElGamal").initialize(2048).generateKeyPair();
 }
 private static byte[] sign(String message, PrivateKey privateKey)
 throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
 Signature signature = Signature.getInstance("SHA256withElGamal");
 signature.initSign(privateKey);
 signature.update(message.getBytes());
 return signature.sign();
 }
 private static boolean verify(String message, byte[] signature, PublicKey publicKey)
 throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
 {
 Signature verifier = Signature.getInstance("SHA256withElGamal");
 verifier.initVerify(publicKey);
 verifier.update(message.getBytes());
 return verifier.verify(signature);
 }
 private static String bytesToHex(byte[] bytes) {
 StringBuilder hexString = new StringBuilder();
 for (byte b : bytes) {
 hexString.append(String.format("%02X", b));
 }
 return hexString.toString();
 }
}
