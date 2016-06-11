/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutdemo;



import java.util.Scanner;
import java.io.*;

public class CsProje 
{
    public CsProje()
    {
        
    }
    
    public String[] checkWord(String word) throws IOException
    {
        String[] suggestions;
        if(isAllLetter(word))
        {
            if(isEnglishWord(word) == false)
            {
                String suggested = wordSuggestion(word);
                suggestions = suggested.split(" ");
            }
            else
                suggestions = new String[0];
        }
        else
        {
            String punctPart = returnPunctPart(word);
            String actualWord = returnStringPart(word);
            if(isEnglishWord(actualWord) == false)
            {
                String suggested = wordSuggestion(actualWord);
                suggestions = suggested.split(" ");
                for(int i = 0; i < suggestions.length; i++)
                    suggestions[i] += punctPart;
            }
            else
                suggestions = new String[0];
        }
        return suggestions;
    }
    
    //Method that returns the first letter of word to determine related sub-thesaurus.
    public String firstLetter(String word)
    {	
    	return word.substring(0, 1);
    }
    
    //Method that determines that input word is valid or not
    public boolean isEnglishWord(String myWord) throws IOException
    {	
    		
    	Scanner fileScan = new Scanner(getFile(myWord));
    	
    	boolean isFound = false;
    	while(fileScan.hasNext() && !isFound)
    	{	
    		String line = fileScan.next();
    		
    		if(myWord.equalsIgnoreCase(line))
                    isFound = true;
       	}
	    	return isFound;
    }
    
    // Method that suggest new words which is identified accroding to their letter distance
    public String wordSuggestion(String wrongWord) throws IOException
    {	
    	String text = "";
    	int distance = 0;
    	Scanner fileScan = new Scanner(getFile(wrongWord));
    	
    	while(fileScan.hasNext())
    	{
    		String line;
    		line = fileScan.next();
	    	if (wrongWord.length() <= 3)    // If the length of word is smaller than or equal to 3, takes levensthein distance as 1.
	    	{                               // It limited itself with this distance because suggesting excessive amount of word is not desired.
	    		if(levenshteinDistance(wrongWord, line)<=1)
	    			text = text + line + " ";
	    	}
	    	else if (wrongWord.length() > 3 && line.length() <= 7)  // Condition for words are between the length of 3 and 7 (seven is included).
	    	{
	    		if(levenshteinDistance(wrongWord, line)<=2)
	    			text = text + line + " ";
	    	}
	    	else if (wrongWord.length() > 7)  // Condition for words are longer than the length of 7.
	    	{
	    		if(levenshteinDistance(wrongWord, line)<=3)
	    			text = text + line + " ";
	    	}
       	}
    	return text;
    }
    
    //Method that returns file depends on fisrt letter of wrong word 	
    public File getFile(String myWord)
    {
    	String firstLetter = firstLetter(myWord);
    	File file = new File(firstLetter + ".txt");
    	return file;
    	
    } 

    public int levenshteinDistance( String s1, String s2 ) 
    {
	return dist( s1.toCharArray(), s2.toCharArray() );
    }

    public int dist( char[] s1, char[] s2 ) 
    {

        // Distance matrix - to memorize distances between substrings
        // needed to avoid recursion
        int[][] d = new int[ s1.length + 1 ][ s2.length + 1 ];

        // d[i][j] - would contain distance between such substrings:
        // s1.subString(0, i) and s2.subString(0, j)

        for( int i = 0; i < s1.length + 1; i++ ) 
        {
            d[ i ][ 0 ] = i;
        }

        for(int j = 0; j < s2.length + 1; j++) 
        {
            d[ 0 ][ j ] = j;
        }

        for( int i = 1; i < s1.length + 1; i++ ) 
        {
            for( int j = 1; j < s2.length + 1; j++ ) 
            {
                int d1 = d[ i - 1 ][ j ] + 1;
                int d2 = d[ i ][ j - 1 ] + 1;
                int d3 = d[ i - 1 ][ j - 1 ];
                if ( s1[ i - 1 ] != s2[ j - 1 ] ) 
                {
                    d3 += 1;
                }
                d[ i ][ j ] = Math.min( Math.min( d1, d2 ), d3 );
            }
        }
        return d[ s1.length ][ s2.length ];
    }
	 
    //Check if the string conteins punct. or not
    public boolean isAllLetter(String myWord)
    {	
    	boolean returnFinal = true;
	for(int i =0; i<myWord.length(); i++)
	{
            if(Character.isLetter(myWord.charAt(i)) == false)
		returnFinal = false;
        }
        return returnFinal;
    }
    
    //Return all letter of string without any punctuation
    public String returnStringPart(String myWord)
    {	
	String returnFinal = "";
	for(int i =0; i<myWord.length(); i++)
	{
            if(Character.isLetter(myWord.charAt(i)) == true)
            {
		returnFinal += myWord.charAt(i);
            }
	}
	return returnFinal;	
    }
    // Method that return the punctuations inside the words as to protect the integrity of the text.
    public String returnPunctPart(String myWord)
    {	
	String returnFinal = "";
	for(int i =0; i<myWord.length(); i++)
	{
            if(Character.isLetter(myWord.charAt(i)) == false)
            {
		returnFinal += myWord.charAt(i); 	
            }
	}
	return returnFinal;
    } 
}