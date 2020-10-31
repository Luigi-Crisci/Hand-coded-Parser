package lexer.com.compiler;

import java.util.HashMap;
import java.util.List;

import lexer.utils.LexerUtils;

/**
 * StringTable
 */
public class StringTable extends HashMap<Integer,String> {

	private static final long serialVersionUID = -4322311144038354562L;

	public StringTable() {
		super();
		//This section is disabled because Keywords are not recognized inside the installID call, before inserting them into StringTable
		// List<Token> l = LexerUtils.keywordsTokens; 
		// for (int i = 0; i < l.size(); i++) 
		// 	put(i, l.get(i).getName());
		
	}
}