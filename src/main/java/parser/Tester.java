package parser;

import lexer.com.compiler.Lexer;

public class Tester {

	public static void main(String args[]) {

		if(args.length < 1)
			throw new IllegalArgumentException("Too few parameters");

		Lexer lexer = new Lexer();
		lexer.initialize(args[0]);
		Parser parser = new Parser(lexer);
		
		if(parser.parse())
			System.out.println("Il file rispetta la grammatica");
		else
			System.out.println("Il file non rispetta la grammatica");

	}

}
