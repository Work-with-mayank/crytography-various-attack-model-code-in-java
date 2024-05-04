9. Alice is of one of the employee of company XYZ. He wants to ensure that whatever 
data he is sending to Bob should be checked for accuracy. Implement the RSA digital 
signature for the message "This is an example” for showing the Digital Signature in 
such a scenario.
 
CODE:- 
 
import java.security.*;
public class RSADigitalSignature 
{
 public static void main(String[] args) throws Exception
{
 KeyPair keyPair = generateKeyPair();
 String message = "This is an mayank";
 byte[] signature = sign(message, keyPair.getPrivate());
 boolean isVerified = verify(message, signature, keyPair.getPublic());
 System.out.println("Is the signature verified? " + isVerified);
 }
 private static KeyPair generateKeyPair() throws Exception 
{
 return KeyPairGenerator.getInstance("RSA").initialize(2048).generateKeyPair();
 }
 private static byte[] sign(String message, PrivateKey privateKey) throws Exception
{
 Signature signature = Signature.getInstance("SHA256withRSA");
 signature.initSign(privateKey);
 signature.update(message.getBytes());
 return signature.sign();
 }
 private static boolean verify(String message, byte[] signature, PublicKey publicKey) throws 
Exception
{
 Signature verifier = Signature.getInstance("SHA256withRSA");
 verifier.initVerify(publicKey);
 verifier.update(message.getBytes());
 return verifier.verify(signature);
 }
}
