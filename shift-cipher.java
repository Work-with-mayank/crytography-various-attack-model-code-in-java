1. Alice and Bob wish to share private messages using a shift cipher algorithm 
to ensure confidentiality. Both can work as a sender and receiver so individual 
functions should be implemented for encryption, decryption and brute force 
with the following conditions: 
● Plaintext should be in lowercase. (Do not accept any number or special 
symbol) 
 ● Ciphertext should be in uppercase. (Do not accept any number or special 
symbol) 
● Brute force attack. (Find the key value) 
 
CODE:- 
 
 
 public class ShiftCipher {
 public static void main(String[] args) {
 String plaintext = "mayan";
 int key = 3;
 // Encryption
 String ciphertext = encrypt(plaintext, key);
 System.out.println("Encrypted: " + ciphertext);
 // Decryption
 String decryptedText = decrypt(ciphertext, key);
 System.out.println("Decrypted: " + decryptedText);
 // Brute Force Attack
 bruteForceAttack(ciphertext);
 
 // Encryption function
 public static String encrypt(String plaintext, int key) {
 StringBuilder ciphertext = new StringBuilder();
 for (int i = 0; i < plaintext.length(); i++) {
 char currentChar = plaintext.charAt(i);
 if (Character.isLowerCase(currentChar)) {
 char encryptedChar = (char) ((currentChar - 'a' + key) % 26 + 'A');
 ciphertext.append(encryptedChar);
 } else {
 // Ignore non-lowercase characters
ciphertext.append(currentChar);
 }
 }
 return ciphertext.toString();
 }
 // Decryption function
 public static String decrypt(String ciphertext, int key) {
 StringBuilder decryptedText = new StringBuilder();
 for (int i = 0; i < ciphertext.length(); i++) {
 char currentChar = ciphertext.charAt(i);
 if (Character.isUpperCase(currentChar)) {
 char decryptedChar = (char) ((currentChar - 'A' - key + 26) % 26 + 'a');
 decryptedText.append(decryptedChar);
 } else {
 // Ignore non-uppercase characters
 decryptedText.append(currentChar);
 }
 }
 return decryptedText.toString();
 }
 // Brute Force Attack function
 public static void bruteForceAttack(String ciphertext) {
 System.out.println("Brute Force Attack:");
 for (int key = 1; key <= 25; key++) {
 String decryptedText = decrypt(ciphertext, key);
 System.out.println("Key " + key + ": " + decryptedText);
 }
 }
}