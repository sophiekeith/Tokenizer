import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Tokenizer {
	ArrayList<String> splitTokens = new ArrayList<String>();
	public Tokenizer(String inputFile) throws IOException
	{
		//Read in file
		FileReader fileReader = new FileReader(inputFile); 
  		String fileContents = "";
 		//Read whole file into string
  		int i ; 
  		while((i =  fileReader.read())!=-1){
	   		char ch = (char)i;
	   		fileContents = fileContents + ch; 
 		}
 		//Split on space, tab, carriage return, line feed
		String[] fileContentsNoSpace =  fileContents.split("\\s+");
		this.splitTokens = new ArrayList<>(Arrays.asList(fileContentsNoSpace));
     	// This will initialize a new array list from the array, fileContentsNoSpace. It will set it to this.splitTokens 
	}
	public void getTokens(){ 
		//Use the arraylist we made in the constructor
	    for (int i = 0; i < this.splitTokens.size(); i++) { 
			String token = this.splitTokens.get(i);
			tokenize(token);
			
		}
		//The last token, will always be EOF
		System.out.println("33");
	}

	public void tokenize(String token)
	{
	
		String[] keywordsArray = {"program","begin","end","int","if","then","else","while","loop","read","write"};
		String[] symbolsArray = {";", ",", "=", "!", "[", "]", "&&", "||", "(", ")", "+", "-", "*", "!=", "==", "<", ">", "<=", ">="};
		
		List<String> keywords = Arrays.asList(keywordsArray);
		List<String> symbols = Arrays.asList(symbolsArray);
		//Check if token is keyword
			if (keywords.indexOf(token) > -1) {
				System.out.println(keywords.indexOf(token) + 1 );
			}
			//Check if token is symbol
			else if (symbols.indexOf(token) > -1) {
				// same for symbols, except the number you print is something like keywords.size() + symbols.indexOf(token) + 1.
				System.out.println(keywords.size() + symbols.indexOf(token) + 1 );
			}
			else if(integers(token))
			{
				System.out.println("31"); 
			}
			else if(identifiers(token))
			{
				System.out.println("32");
			}
			else {
				comboToken(token);
			}
	}

	public static boolean integers(String token)
	{
		//initalize boolean that determines if this is an int
		boolean isInt = false;
		//Length must be less than 9
		int tokenLength = token.length();
		boolean isDigit = true;
		if (tokenLength < 9)
		{	
			//Make sure digit is actually a digit
			for (int i = 0; i < tokenLength; i++) {
      			if (!Character.isDigit(token.charAt(i)))
      			{
        			isDigit = false;
      			}
			
			//If it is a digit, parse and print 
       			if (isDigit)
        		{
        			isInt = true;
        		}
        	}
        }
		return isInt;
	}
		
    

    public static boolean identifiers(String token)
    {
    	String[] symbolsArray = {";", ",", "=", "!", "[", "]", "&&", "||", "(", ")", "+", "-", "*", "!=", "==", "<", ">", "<=", ">="};
    	List<String> symbols = Arrays.asList(symbolsArray);
    	//Length must be less than 9

		boolean isID = false;
		boolean isDigit = false;
		boolean containsSymbol = false;
		//&& isDigit == false || containsSymbol== false
		for (int i = token.length()-1; i>0 && containsSymbol== false ; i--) {
      			if (Character.isDigit(token.charAt(i)))
      			{
        			isDigit = true;
      			}
      			if (symbols.indexOf(String.valueOf(token.charAt(i))) > -1){
      				containsSymbol = true;
      			}
        	}
			
			//It cannot contain any lowercase
			boolean hasLowercase = !token.equals(token.toUpperCase());

			
			//So, an identifier can either be 1)(has first char as uppercase, have digits, no lower case) 
			if (isDigit && containsSymbol == false && hasLowercase == false )
			{
				
					isID = true;
								
			}
			//Or an identifier can be 2) (has first char uppercase, no digits, no lower case)
			if (containsSymbol == false && hasLowercase == false)
			{
				
					isID = true;
				
			}
		
		return isID;
    }
    public static void comboToken(String token)
    {
    	String[] keywordsArray = {"program","begin","end","int","if","then","else","while","loop","read","write"};
		String[] symbolsArray = {";", ",", "=", "!", "[", "]", "&&", "||", "(", ")", "+", "-", "*", "!=", "==", "<", ">", "<=", ">="};
		boolean beginCharSymbol = false;
		
		List<String> keywords = Arrays.asList(keywordsArray);
		List<String> symbols = Arrays.asList(symbolsArray);
		//first step, convert combo token to array list

		//array list that will house all of the individual tokens
		ArrayList<String> indivTokens = new ArrayList<String>();

		//String that will gather the current token, until symbol
		String currentToken = "";
		
	
		//continue to search end of string
		for(int i =0; i<= token.length()-1; i++)
		{
			
			//if current char is a symbol, add to indivTokens
			if (symbols.indexOf(String.valueOf(token.charAt(i))) > -1)
			{
				//before adding this symbol, must add the token before, if one exists.
				if (currentToken.length()>0){
					indivTokens.add(currentToken);
				}else{ // this mean, the first character in token is symbol
					beginCharSymbol = true;
				}
				String currentChar = String.valueOf(token.charAt(i));
				indivTokens.add(currentChar);
				
			
				//must reset the currentToken
				currentToken = "";
				
			}
			else //meaning either keyword,; int or identifier
			{
				currentToken += token.charAt(i);
				
			}
			//position where first character was token, now must add rest

			if(i+1 ==token.length() && beginCharSymbol == true){
				indivTokens.add(currentToken);
			}
			
		}
		
		
		//now, we know the array is represented, we must see what they are
		for (int i =0; i< indivTokens.size(); i++)
		{
			//get current token 
			String element = indivTokens.get(i);
			//check if its a keyword
			if (keywords.indexOf(element) > -1) {
				System.out.println(keywords.indexOf(element) + 1 );
			}

			//check if its a symbol
			else if (symbols.indexOf(element) > -1) 
			{
				// same for symbols, except the number you print is something like keywords.size() + symbols.indexOf(token) + 1.
				System.out.println(keywords.size() + symbols.indexOf(element) + 1 );
			}

			//check if its a integer
			else if (integers(element))
			{
				System.out.println("31");
			}
			
			//check if its an identifier
			else if(identifiers(element))
			{
				System.out.println("32");
			}
	
			//or, its none of the above, in which case, it is not a token
			else
			{
				System.out.println("error");
			}
		
		}

    }
}