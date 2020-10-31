package lexer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lexer.com.compiler.Token;

public class LexerUtils {

    /**
     * Keywords tokens
     */
    public static Token EMPTY_TOKEN;
    public static Token IF_TOKEN;
    public static Token THEN_TOKEN;
    public static Token ELSE_TOKEN;
    public static Token WHILE_TOKEN;
    public static Token FOR_TOKEN;
    public static Token DO_TOKEN;
    public static List<Token> keywordsTokens;

    static {
        IF_TOKEN = new Token(Tokens.IF.name(),"");
        THEN_TOKEN = new Token(Tokens.THEN.name(),"");
        ELSE_TOKEN = new Token(Tokens.ELSE.name(),"");
        WHILE_TOKEN = new Token(Tokens.WHILE.name(),"");
        FOR_TOKEN = new Token(Tokens.FOR.name(),"");
        EMPTY_TOKEN = new Token(Tokens.EMPTY.name(),"");
        DO_TOKEN= new Token(Tokens.DO.name(),"");

        keywordsTokens = new ArrayList<>();
        keywordsTokens.add(IF_TOKEN);
        keywordsTokens.add(THEN_TOKEN);
        keywordsTokens.add(ELSE_TOKEN);
        keywordsTokens.add(WHILE_TOKEN);
        keywordsTokens.add(FOR_TOKEN);
    }

    public static boolean isKeyword(String s) {
        return s.equalsIgnoreCase("IF") || s.equalsIgnoreCase("then") || s.equalsIgnoreCase("else") || s.equalsIgnoreCase("while") || s.equalsIgnoreCase("for");
    }

    public static boolean isBlank(CharSequence c) {
        return Pattern.matches("\\s", c);
    }

    public static boolean isDigit(CharSequence c) {
        return Pattern.matches(LexemePatterns.DIGIT, c);
    }

    public static boolean isNonzeroDigit(CharSequence c) {
        return Pattern.matches(LexemePatterns.DIGIT_NONZERO, c);
    }

    public static boolean isLetter(CharSequence c) {
        return Pattern.matches(LexemePatterns.LETTER, c);
    }

    public static boolean isWord(CharSequence c) {
        return Pattern.matches(LexemePatterns.WORD, c);
    }

    public static CharSequence charToCharSequence(char c) {
        return "" + c;
    }

	public static boolean isError(String name) {
		return Tokens.ERROR.equals(Tokens.valueOf(name));
    }
    
    public static boolean isEmpty(Token t) {
		return EMPTY_TOKEN.equals(t);
    }

    public static boolean isBufferEmpty(char c){
        return ((byte) c) == ((byte)0);
    }
}
