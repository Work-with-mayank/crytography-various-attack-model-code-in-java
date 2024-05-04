4. Rustom wants to send a confidential message “Meet me after toga party” to 
Kelvin. So both are ready to share the initial key and other keys are generated 
automatically then the individual function should be implemented for 
encryption, decryption and brute force with the following conditions must be 
satisfied: 
● Plaintext should be in lowercase. (Do not accept any number or special 
symbol) 
● Ciphertext should be uppercase. (Do not accept any number or special 
symbol) 
● Brute force attack. (Find the key value 
 
CODE:- 
 
public class VigenereCipher {
 public static void main(String[] args) {
 String plaintext = "meet me after toga party";
 String keyword = "KEY"; // Shared initial key
 // Encryption
 String ciphertext = encrypt(plaintext, keyword);
 System.out.println("Encrypted: " + ciphertext);
 // Decryption
 String decryptedText = decrypt(ciphertext, keyword);
 System.out.println("Decrypted: " + decryptedText);
 // Brute Force Attack
 bruteForceAttack(ciphertext);
 }
 // Encryption function
 public static String encrypt(String plaintext, String keyword) {
 StringBuilder ciphertext = new StringBuilder();
 int keywordIndex = 0;
 for (int i = 0; i < plaintext.length(); i++) {
 char currentChar = plaintext.charAt(i);
 if (Character.isLowerCase(currentChar)) {
 char encryptedChar = (char) ((currentChar - 'a' + keyword.charAt(keywordIndex) - 'A') % 
26 + 'A');
 ciphertext.append(encryptedChar);
 // Move to the next letter in the keyword
 keywordIndex = (keywordIndex + 1) % keyword.length();
 } else {
 // Ignore non-lowercase characters
 ciphertext.append(currentChar);
 }
 }
 return ciphertext.toString();
 }
 // Decryption function
 public static String decrypt(String ciphertext, String keyword) {
 StringBuilder decryptedText = new StringBuilder();
 int keywordIndex = 0;
 for (int i = 0; i < ciphertext.length(); i++) {
 char currentChar = ciphertext.charAt(i);
 if (Character.isUpperCase(currentChar)) {
 char decryptedChar = (char) ((currentChar - 'A' - (keyword.charAt(keywordIndex) - 'A') + 
26) % 26 + 'a');
 decryptedText.append(decryptedChar);
 // Move to the next letter in the keyword
 keywordIndex = (keywordIndex + 1) % keyword.length();
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
 for (int i = 0; i < 26; i++) {
 StringBuilder candidateKeyword = new StringBuilder();
 for (int j = 0; j < ciphertext.length(); j++) {
 char currentChar = ciphertext.charAt(j);
 if (Character.isUpperCase(currentChar)) {
 char decryptedChar = (char) ((currentChar - 'A' - i + 26) % 26 + 'a');
 candidateKeyword.append((char) ('A' + decryptedChar - ciphertext.charAt(j)));
 } else {
 // Ignore non-uppercase characters
 candidateKeyword.append(currentChar);
 }
 }
 System.out.println("Key: " + candidateKeyword.toString() + ", Decrypted: " + 
decrypt(ciphertext, candidateKeyword.toString()));
 }
 }
}