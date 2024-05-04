8. User A and B want to communicate with each other by shared key so both parties 
decided that using Asymmetric key cryptography to generate a shared key and 
exchange with the help of Diffie-Hellman key exchange Algorithm. Perform exchange 
encryption & decryption using key exchange algorithm. 
 
CODE:- 
 
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.KeyAgreement;
public class DiffieHellman {
 public static void main(String[] args) throws Exception {
 KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
 keyPairGenerator.initialize(2048)
 // Alice
 KeyPair keyPairAlice = keyPairGenerator.generateKeyPair();
 PublicKey publicKeyAlice = keyPairAlice.getPublic();
 String publicKeyStrAlice = 
Base64.getEncoder().encodeToString(publicKeyAlice.getEncoded());
 PublicKey receivedPublicKeyAlice = KeyFactory.getInstance("DH")
 .generatePublic(newX509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStrAlice)));
 // Bob
 KeyPair keyPairBob = keyPairGenerator.generateKeyPair();
 PublicKey publicKeyBob = keyPairBob.getPublic();
 String publicKeyStrBob = Base64.getEncoder().encodeToString(publicKeyBob.getEncoded());
 PublicKey receivedPublicKeyBob = KeyFactory.getInstance("DH")
 .generatePublic(new 
X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStrBob)));
 // Key agreement
 KeyAgreement keyAgreementAlice = KeyAgreement.getInstance("DH");
 keyAgreementAlice.init(keyPairAlice.getPrivate());
 keyAgreementAlice.doPhase(receivedPublicKeyBob, true);
 byte[] sharedSecretAlice = keyAgreementAlice.generateSecret();
 KeyAgreement keyAgreementBob = KeyAgreement.getInstance("DH");
 keyAgreementBob.init(keyPairBob.getPrivate());
 keyAgreementBob.doPhase(receivedPublicKeyAlice, true);
 byte[] sharedSecretBob = keyAgreementBob.generateSecret();
 System.out.println("Shared Secret Alice: " + 
Base64.getEncoder().encodeToString(sharedSecretAlice));
 System.out.println("Shared Secret Bob: " + 
Base64.getEncoder().encodeToString(sharedSecretBob));
 }
}
