5. The project investigates a cipher that is somewhat more complicated than 
the simple substitution cipher. In the Playfair cipher, there is not a single 
translation of each letter of the alphabet; that is, you don’t just decide that 
every B will be turned into an F. Instead, pairs of letters are translated into 
other pairs of letters. Here is how it works. To start, pick a keyword that does 
not contain any letter more than once. For example, I’ll pick the word keyword. 
Now write the letters of that word in the first squares of a five-by-five matrix. 
Then finish filling up the remaining squares of the matrix with the remaining 
letters of the alphabet, in alphabetical order. Since there are 26 letters and 
only 25 squares, we assign I and J to the same square. So implement Playfair 
Cipher to encrypt & decrypt the given message where the key matrix can be 
formed by using a given keyword. 
 
CODE:- 
 
import java.util.Scanner;
public class PlayfairCipher
{
 private char[][] keyMatrix;
 public PlayfairCipher(String key)
 {
 keyMatrix = generateKeyMatrix(key);
 }
 public String encrypt(String plaintext)
{
 StringBuilder ciphertext = new StringBuilder();
 for (int i = 0; i < plaintext.length(); i += 2) {
 char first = plaintext.charAt(i);
 char second = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';
 int[] firstPos = findPosition(first);
 int[] secondPos = findPosition(second)
 ciphertext.append(getEncryptedPair(firstPos, secondPos));
 }
 return ciphertext.toString();
 }
 Public String decrypt(String ciphertext) {
 StringBuilder plaintext = new StringBuilder();
 for (int i = 0; i < ciphertext.length(); i += 2) {
 char first = ciphertext.charAt(i);
 char second = ciphertext.charAt(i + 1);
 int[] firstPos = findPosition(first);
 int[] secondPos = findPosition(second);
 plaintext.append(getDecryptedPair(firstPos, secondPos));
 }
 return plaintext.toString();
 }
 private char[][] generateKeyMatrix(String key) {
 char[][] matrix = new char[5][5];
 boolean[] usedLetters = new boolean[26];
 int row = 0, col = 0
 for (char letter : key.toCharArray()) {
 if (!usedLetters[letter - 'A']) {
 matrix[row][col] = letter;
 usedLetters[letter - 'A'] = true;
 col = (col + 1) % 5;
 if (col == 0) row++;
 }
 }
 for (char letter = 'A'; letter <= 'Z'; letter++) 
 {
 if (letter != 'J' && !usedLetters[letter - 'A']) 
 {
 matrix[row][col] = letter;
 col = (col + 1) % 5;
 if (col == 0) row++
 }
 return matrix;
 }
 private int[] findPosition(char letter) {
 int[] position = new int[2];
 for (int i = 0; i < keyMatrix.length; i++) {
 for (int j = 0; j < keyMatrix[i].length; j++) {
 if (keyMatrix[i][j] == letter) {
 position[0] = i;
 position[1] = j;
 return position;
 }
 }
 }
 return position;
 }
 private String getEncryptedPair(int[] firstPos, int[] secondPos) 
{
 if (firstPos[0] == secondPos[0]) {
 firstPos[1] = (firstPos[1] + 1) % 5;
 secondPos[1] = (secondPos[1] + 1) % 5;
 } else if (firstPos[1] == secondPos[1]) {
 firstPos[0] = (firstPos[0] + 1) % 5;
 secondPos[0] = (secondPos[0] + 1) % 5;
 } else {
 int temp = firstPos[1];
 firstPos[1] = secondPos[1];
 secondPos[1] = temp;
 }
 return "" + keyMatrix[firstPos[0]][firstPos[1]] + keyMatrix[secondPos[0]][secondPos[1]];
 }
 private String getDecryptedPair(int[] firstPos, int[] secondPos) {
 if (firstPos[0] == secondPos[0]) {
 firstPos[1] = (firstPos[1] - 1 + 5) % 5;
 secondPos[1] = (secondPos[1] - 1 + 5) % 5;
 } else if (firstPos[1] == secondPos[1]) {
 firstPos[0] = (firstPos[0] - 1 + 5) % 5;
 secondPos[0] = (secondPos[0] - 1 + 5) % 5;
 } else {
 int temp = firstPos[1];
 firstPos[1] = secondPos[1];
 secondPos[1] = temp;
 }
 return "" + keyMatrix[firstPos[0]][firstPos[1]] + keyMatrix[secondPos[0]][secondPos[1]];
 }
 public static void main(String[] args)
{
 Scanner scanner = new Scanner(System.in);
 System.out.print("Enter the keyword: ");
 String keyword = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
 PlayfairCipher playfairCipher = new PlayfairCipher(keyword);
 System.out.print("Enter the plaintext: ");
 String plaintext = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
 String ciphertext = playfairCipher.encrypt(plaintext);
 System.out.println("Encrypted text: " + ciphertext);
 String decryptedText = playfairCipher.decrypt(ciphertext);
 System.out.println("Decrypted text: " + decryptedText);
 scanner.close();
 }
}