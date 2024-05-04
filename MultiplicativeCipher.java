2. Alice and Bob wish to share private messages using a multiplicative cipher 
algorithm to ensure confidentiality. The key value taken by both parties should 
be coprime with modulo 26. Both can work as a sender and receiver so 
individual functions should be implemented for encryption, decryption and 
brute force with the following conditions must be satisfied: 
● Plaintext should be in lowercase. (Do not accept any number or special 
symbol) 
● Ciphertext should be uppercase. (Do not accept any number or special 
symbol) 
● Brute force attack. (Find the key value 
 
CODE:- 
 
public class MultiplicativeCipher {
 public static void main(String[] args) {
 String plaintext = "hello";
 int key = 9;
 // Check if the key is coprime with 26
 if (isCoprime(key, 26)) {
 // Encryption
 String ciphertext = encrypt(plaintext, key);
 System.out.println("Encrypted: " + ciphertext);
 // Decryption
 String decryptedText = decrypt(ciphertext, key);
 System.out.println("Decrypted: " + decryptedText)
 // Brute Force Attack
 bruteForceAttack(ciphertext);
 } else {
 System.out.println("Key is not coprime with 26. Choose a different key.");
 }
 }
 // Encryption function
 public static String encrypt(String plaintext, int key) {
 StringBuilder ciphertext = new StringBuilder()
 for (int i = 0; i < plaintext.length(); i++) {
 char currentChar = plaintext.charAt(i);
 if (Character.isLowerCase(currentChar)) {
 char encryptedChar = (char) (((currentChar - 'a') * key) % 26 + 'A');
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
 int modInverse = findModularInverse(key, 26);
 StringBuilder decryptedText = new StringBuilder();
 for (int i = 0; i < ciphertext.length(); i++) {
 char currentChar = ciphertext.charAt(i);
 if (Character.isUpperCase(currentChar)) {
 char decryptedChar = (char) (((currentChar - 'A') * modInverse) % 26 + 'a');
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
 if (isCoprime(key, 26)) {
 String decryptedText = decrypt(ciphertext, key);
 System.out.println("Key " + key + ": " + decryptedText);
 }
 }
 }
 // Check if two numbers are coprime
 public static boolean isCoprime(int a, int b) {
 return findGCD(a, b) == 1;
 }
 // Find the greatest common divisor using Euclid's algorithm
 public static int findGCD(int a, int b) {
 while (b != 0) {
 int temp = b;
 b = a % b;
 a = temp;
 }
 return a;
 }
 // Find the modular inverse using extended Euclidean algorithm
 public static int findModularInverse(int a, int m) {
 for (int x = 1; x < m; x++) {
 if ((a * x) % m == 1) {
 return x;
 }
 }
 return -1; // Modular inverse does not exist
 }
}
