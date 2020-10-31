package lexer.utils;

import lexer.com.compiler.Token;

public class RecognizedToken {

    public Token token;
    public int character_read;

    public RecognizedToken(){
        token = null;
        character_read = 0;
    }

    public RecognizedToken(Token t, int character_read) {
        this.character_read = character_read;
        this.token = t;
    }

    public int compareTo(RecognizedToken token1){
        if(!LexerUtils.isError(token1.token.getName()) && LexerUtils.isError(token.getName()))
            return -1;
        if (!LexerUtils.isError(token.getName()) && LexerUtils.isError(token1.token.getName()))
            return 1;

        return Integer.compare(character_read, token1.character_read);
    }
}
