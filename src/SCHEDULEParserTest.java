import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

public class SCHEDULEParserTest
{

	public static void main(String[] args) throws Exception 
	{
		// Input from external file
	    String content = new Scanner(new File("target/generated-sources/antlr4/input.txt")).useDelimiter("\\Z").next();
	    System.out.println( "SCHEDULE File:\n" + content + "\n\n");
	    
		ANTLRInputStream input = new ANTLRInputStream( content );
		
		SCHEDULELexer lexer = new SCHEDULELexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		SCHEDULEParser parser = new SCHEDULEParser(tokens);
		
		Schedule schedule = new Schedule();
		
		SCHEDULEParserListener parser_listener = new SCHEDULEParserListener(schedule);
		
		parser.addParseListener(parser_listener);
		
		ParseTree tree = parser.prog();
		
	    // Input from command
	    while (true) {
	      String command = new Scanner(System.in).nextLine();
	      
	      System.out.println( "Input:\n" + command + "\n\n");
	      
	      ANTLRInputStream input_command = new ANTLRInputStream(command);
	      
	      SCHEDULELexer lexer_command = new SCHEDULELexer(input_command);
	      
	      CommonTokenStream tokens_command = new CommonTokenStream(lexer_command);
	      
	      SCHEDULEParser parser_command = new SCHEDULEParser(tokens_command);
	      
	      parser_command.addParseListener(parser_listener);
	      
	      ParseTree tree_command = parser_command.prog(); 
	    }  
	}

}
