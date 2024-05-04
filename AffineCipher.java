3. To enhance the security Amit and Anil agreed to use the affine cipher 
algorithm with two keys. So the first key must be coprime with modulo 26 and 
the second key varies from 1 to 26. Both can work as a sender and receiver so 
individual functions should be implemented for encryption, decryption and 
brute force with the following conditions must be satisfied: 
● Plaintext should be in lowercase. (Do not accept any number or special 
symbol) 
● Ciphertext should be uppercase. (Do not accept any number or special 
symbol) 
● Brute force attack. (Find the key value 
 
CODE:- 
 
public class AffineCipher {
 public static void main(String[] args) {
 String plaintext = "hello";
 int key1 = 5; // First key (coprime with 26)
 int key2 = 8; // Second key (1 to 26)
 // Check if the first key is coprime with 26
 if (isCoprime(key1, 26)) {
 // Encryption
 String ciphertext = encrypt(plaintext, key1, key2);
 System.out.println("Encrypted: " + ciphertext);
 // Decryption
 String decryptedText = decrypt(ciphertext, key1, key2);
 System.out.println("Decrypted: " + decryptedText);
 // Brute Force Attack
 bruteForceAttack(ciphertext);
 } else {
 System.out.println("First key is not coprime with 26. Choose a different key.");
 }
 }
 // Encryption function
 public static String encrypt(String plaintext, int key1, int key2) {
 StringBuilder ciphertext = new StringBuilder();
 for (int i = 0; i < plaintext.length(); i++) {
char currentChar = plaintext.charAt(i);
 if (Character.isLowerCase(currentChar)) {
 char encryptedChar = (char) (((currentChar - 'a') * key1 + key2) % 26 + 'A');
 ciphertext.append(encryptedChar);
 } else {
 // Ignore non-lowercase characters
 ciphertext.append(currentChar);
 }
 }
 return ciphertext.toString();
 }
 // Decryption function
 public static String decrypt(String ciphertext, int key1, int key2) {
 int modInverse = findModularInverse(key1, 26);
 StringBuilder decryptedText = new StringBuilder();
 for (int i = 0; i < ciphertext.length(); i++) {
 char currentChar = ciphertext.charAt(i);
 if (Character.isUpperCase(currentChar)) {
 char decryptedChar = (char) ((modInverse * (currentChar - 'A' - key2 + 26)) % 26 + 'a');
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
 for (int key1 = 1; key1 <= 25; key1++) {
 if (isCoprime(key1, 26)) {
 for (int key2 = 1; key2 <= 26; key2++) {
 String decryptedText = decrypt(ciphertext, key1, key2);
 System.out.println("Key1: " + key1 + ", Key2: " + key2 + ": " + decryptedText);
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
