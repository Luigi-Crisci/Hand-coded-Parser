package lexer.utils;

import java.util.Set;
import org.reflections.*;
import lexer.lexeme.AbstractLexemeAnalyzer;

public class LexemeAnalyzerLoader {
	
	private static final Reflections reflections;

	static{
		reflections = new Reflections("lexer.lexeme");
	}

	public static Set< Class<? extends AbstractLexemeAnalyzer>> loadLexemeAnalyzerClasses() {
		return reflections.getSubTypesOf(AbstractLexemeAnalyzer.class);
	}
}
