package lexer.utils;

public enum Tokens {
    //Keywords
    IF,
    THEN,
    ELSE,
    WHILE,
    DO,
    FOR,
    NUMBER,
    ID,
    ASSIGN,
    RELOP,
    //Separators
    S_COLON,
    ERROR,
    EMPTY;

    public static int compareTo(Tokens x,Tokens y) {
        for(Tokens currentTokens: values()){
            if(currentTokens.equals(x))
                return 1;
            if(currentTokens.equals(y))
                return -1;
        }
        return 0;
    }
}

