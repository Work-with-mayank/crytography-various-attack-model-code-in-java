6. Write a program to implement Hill Cipher to encrypt & decrypt the given 
message by using a given key matrix. Show the values for the key and its 
corresponding key inverse values. 
 
CODE:- 
 
import java.util.ArrayList;
import java.util.Scanner;
public class HillCipherExample 
 private static int[][] getKeyMatrix() {
 Scanner scanner = new Scanner(System.in);
 System.out.println("Enter key matrix:");
 String key = scanner.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();
 int size = (int) Math.sqrt(key.length());
 if (key.length() != size * size) {
 throw new RuntimeException("Cannot form a square matrix");
 }
 int[][] keyMatrix = new int[size][size];
 int index = 0;
 for (int i = 0; i < size; i++) {
 for (int j = 0; j < size; j++) {
 keyMatrix[i][j] = key.charAt(index++) - 'A';
 }
 }
 return keyMatrix;
 }
 private static int calculateDeterminant(int[][] matrix) {
 return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
 }
 private static int[][] calculateInverseMatrix(int[][] keyMatrix) {
 int determinant = calculateDeterminant(keyMatrix) % 26;
 int inverseDeterminant = 0
 for (int i = 1; i < 26; i++) {
 if ((determinant * i) % 26 == 1) {
 inverseDeterminant = i;
 break;
 }
 }
 int[][] inverseMatrix = new int[2][2];
 inverseMatrix[0][0] = keyMatrix[1][1] * inverseDeterminant % 26;
 inverseMatrix[0][1] = (26 - keyMatrix[0][1]) * inverseDeterminant % 26;
 inverseMatrix[1][0] = (26 - keyMatrix[1][0]) * inverseDeterminant % 26;
 inverseMatrix[1][1] = keyMatrix[0][0] * inverseDeterminant % 26;
 return inverseMatrix;
 }
 private static ArrayList<Integer> convertToNumbers(String text) {
 ArrayList<Integer> numbers = new ArrayList<>();
 text = text.replaceAll("[^a-zA-Z]", "").toUpperCase();
 for (char c : text.toCharArray()) {
 numbers.add(c - 'A');
 }
 return numbers;
 }
 private static void printResult(String label, int adder, ArrayList<Integer> numbers) {
 System.out.print(label);
 for (int i = 0; i < numbers.size(); i++) {
 System.out.print(Character.toChars(numbers.get(i) + adder + 'A'));
 if (i + 1 < numbers.size()) {
 System.out.print("-");
 }
 }
 System.out.println();
 }
 private static void performEncryptionOrDecryption(boolean isEncryption, String text, boolean 
alphaZero) {
 int adder = alphaZero ? 0 : 1;
 int[][] keyMatrix = getKeyMatrix();
 int[][] inverseMatrix = calculateInverseMatrix(keyMatrix);
 ArrayList<Integer> numbers = convertToNumbers(text);
 ArrayList<Integer> result = new ArrayList<>();
 for (int i = 0; i < numbers.size(); i += 2) {
 int x = (keyMatrix[0][0] * numbers.get(i) + keyMatrix[0][1] * numbers.get(i + 1)) % 26;
 int y = (keyMatrix[1][0] * numbers.get(i) + keyMatrix[1][1] * numbers.get(i + 1)) % 26;
 result.add(alphaZero ? x : (x == 0 ? 26 : x));
 result.add(alphaZero ? y : (y == 0 ? 26 : y));
 }
 printResult((isEncryption ? "Encoded" : "Decoded") + " phrase: ", adder, result);
 }
 public static void encrypt(String text, boolean alphaZero) {
 performEncryptionOrDecryption(true, text, alphaZero);
 }
 public static void decrypt(String text, boolean alphaZero) {
 
performEncryptionOrDecryption(false, text, alphaZero);
 }
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
 System.out.println("Hill Cipher Implementation (2x2)");
 System.out.println("-------------------------");
 System.out.println("1. Encrypt text (A=0, B=1, ..., Z=25)");
 System.out.println("2. Decrypt text (A=0, B=1, ..., Z=25)");
 System.out.println("3. Encrypt text (A=1, B=2, ..., Z=26)");
 System.out.println("4. Decrypt text (A=1, B=2, ..., Z=26)");
 System.out.println();
 System.out.println("Type any other character to exit");
 System.out.println();
 System.out.print("Select your choice: ");
 String opt = scanner.nextLine();
 switch (opt)
{
 case "1":
 System.out.print("Enter phrase to encrypt: ");
 String phraseToEncrypt = scanner.nextLine();
 encrypt(phraseToEncrypt, true);
 break;
 case "2":
 System.out.print("Enter phrase to decrypt: ");
 String phraseToDecrypt = scanner.nextLine();
 decrypt(phraseToDecrypt, true);
 break;
 case "3":
 System.out.print("Enter phrase to encrypt: ");
 String phraseToEncryptAlphaOne = scanner.nextLine();
 encrypt(phraseToEncryptAlphaOne, false);
 break;
 case "4":
 System.out.print