package vigenereCipher;

import java.util.Arrays;
import java.util.List;

public interface VigenereCipher_Moran
{
	public final static List<Character> ENGLISH_LOWERCASE_LETTERS_LIST = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n',
			'o','p','q','r','s','t','u','v','w','x','y','z');
	public final static List<Character> ENGLISH_UPPERCASE_LETTERS_LIST = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N',
			'O','P','Q','R','S','T','U','V','W','X','Y','Z');
	
//part of pre: plainMessage[i] != null, for i in [0, plainMessage.length)
//part of pre: plainMessage[i].charAt(j) is in {'a', 'b', 'c', ...,'z'}, for i 
// in [0, plainMessage.length) and j in [0, plainMessage[i].length()) 
//part of pre: key != null
//part of pre: key.length() > 0
//part of post: rv.charAt(j) is in {'A', 'B', 'C', ..., 'Z'} U {' '}, for j
// in [0, rv.length())
	public static String encrypt(String[] plainMessage, String key)
	{
		assert key != null: "key is empty. " + key;
		assert key.length() > 0: "key is not long enough. " + key;
		assert plainMessage != null: "message is empty. " + plainMessage;
		assert plainMessage.length > 0: "message is too short. " + plainMessage;
		
		String encrypted = "";
		for ( int i = 0; i < plainMessage.length; i++)
		{
			String temp = plainMessage[i];
			encrypted += encrypt(temp, key) + " ";
		}
		return encrypted;
	}
		
//part of pre: plainText != null
//part of pre: plainText.charAt(j) is in {'a', 'b', 'c', ...'z'}, for j 
// in [0, plainText.length()) 
//part of pre: key != null
//part of pre: key.length() > 0
//part of post: rv.charAt(j) is in {'A', 'B', 'C', ..., 'Z'}, for j
// in [0, rv.length()) 
	public static String encrypt(String plainText, String key){
		assert plainText != null: "text is empty. " + plainText;
		assert key != null: "key is empty. " + key;
		assert key.length() > 0: "key is too short. " + key;
		assert plainText.length() > 0: "text is too short. " + plainText;
		
		String encrypted = "";
		for ( int j = 0; j < plainText.length(); j++)
		{
			String temp = plainText;
			encrypted += encrypt(temp, key) + " ";
		}
		return encrypted;
	}
	
//part of pre: encryptedMessage[i] != null, for i in [0, encryptedMessage.length)
//part of pre: encryptedMessage[i].charAt(j) is in {'A', 'B', 'C', ...,'Z'}, for i
//        in [0, encryptedMessage.length) and j in [0, encryptedMessage[i].length())
//part of pre: key != null
//part of pre: key.length() > 0
//part of post: rv.charAt(j) is in {'a', 'b', 'c', ..., 'z'} U {' '}, for j
// in [0, rv.length())
//part of post: left out, but need to express exactly where the spaces are in rv 
	public static String decrypt(String[] encryptedMessage, String key){
		assert key != null: "key is empty. " + key;
		assert key.length() > 0: "key is too short. " + key;
		assert encryptedMessage != null: "message is empty. " + encryptedMessage;
		assert encryptedMessage.length > 0: "message is too short. " + encryptedMessage;
		
		String decrypted = "";
		for ( int i = 0; i < encryptedMessage.length; i++)
		{
			String temp = encryptedMessage[i];
			decrypted += decrypt(temp, key) + " ";
		}
		return decrypted;
	}
	
//part of pre: encryptedText != null
//part of pre: encryptedText.charAt(j) is in {'A', 'B', 'C', ...,'Z'}, for j
// in [0, encryptedText.length()) 
//part of pre: key != null
//part of pre: key.length() > 0
//part of post: rv.charAt(j) is in {'a', 'b', 'c', ..., 'z'}, for j in [0, rv.length()) 
	public static String decrypt(String encryptedText, String key){
		String decrypted = "";
		for (int i = 0, j = 0; i < encryptedText.length(); i++)
		{
			decrypted += getColumn(key.charAt(j), encryptedText.charAt(i));
			j = ++j % key.length();
		}
		return decrypted;
	}

//part of pre: row is in {'a', 'b', 'c', ...'z'}
//part of pre: column is in {'a', 'b', 'c', ...'z'} 
//part of post: rv is in {'A', 'B', 'C', ..., 'Z'}
	public static char getMatrixEntry(char row, char column){
		assert ENGLISH_LOWERCASE_LETTERS_LIST.contains(row): "row is incorrect. " + row;
		assert ENGLISH_LOWERCASE_LETTERS_LIST.contains(column): "column is incorrect. " + column;
		
		int rowNumber = ENGLISH_LOWERCASE_LETTERS_LIST.indexOf(row);
		int columnNumber = ENGLISH_LOWERCASE_LETTERS_LIST.indexOf(column);
		int matrixEntryNumber = Math.abs((rowNumber + columnNumber)% 26);
		
		return ENGLISH_UPPERCASE_LETTERS_LIST.get(matrixEntryNumber);
		
	}

//part of pre: row is in {'a', 'b', 'c', ...'z'}
//part of pre: matrixEntry is in {'A', 'B', 'C', ..., 'Z'} 
//part of post: rv is in {'a', 'b', 'c', ...'z'}
	public static char getColumn(char row, char matrixEntry)
	{
		assert ENGLISH_LOWERCASE_LETTERS_LIST.contains(row): "row is incorrect. " + row;
		assert ENGLISH_UPPERCASE_LETTERS_LIST.contains(matrixEntry): "matrix entry is incorrect. " + matrixEntry;
		
		int rowNumber = ENGLISH_LOWERCASE_LETTERS_LIST.indexOf(row);
		int matrixEntryNumber = ENGLISH_UPPERCASE_LETTERS_LIST.indexOf(matrixEntry);
		int columnNumber = Math.abs(((matrixEntryNumber - rowNumber) + 26) % 26);
		return ENGLISH_LOWERCASE_LETTERS_LIST.get(columnNumber);
	}
}