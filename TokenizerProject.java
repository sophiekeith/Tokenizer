import java.io.IOException;
import java.util.Scanner;

public class TokenizerProject {

	public static void main(String[] args) throws IOException {

		//get the input file
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of input file: ");
		String inputFile = in.nextLine();

		Tokenizer t = new Tokenizer(inputFile);
		t.getTokens();
			 	//while (ct != 33) { // recall that 33 denotes end-of-file
			    //	System.out.println(ct);
			    //	t.skipToken(); // skip past the current token
			    //	ct = t.getToken(); // get next token which is now 
			                       // the "current" token
			  	//}

	}

}
