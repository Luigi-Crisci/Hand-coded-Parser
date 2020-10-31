package lexer.lexeme;

import lexer.utils.*;

import java.nio.ByteBuffer;

public class NumberLexemeAnalyzer extends AbstractLexemeAnalyzer {

    @Override
    public RecognizedToken check(ByteBuffer buffer) {

        reset();

        while (true) {
            switch (state) {
                case 0: { // Initial state
                    nextChar(buffer);
                    if (LexerUtils.isBlank(readChar)){
                        stringBuffer.deleteCharAt(0);
                        continue;
                    }
                    if (LexerUtils.isDigit(readChar)) {
                        if (readChar.equals("0"))
                            state = 1;
                        else
                            state = 2;
                        break;
                    }
                    return constructToken(Tokens.ERROR);
                }

                case 1: { // Read "0"
                    nextChar(buffer);
                    if (readChar.equals(".")) {
                        state = 3; // It's float time
                        break;
                    }
                    retract();
                    return constructToken(Tokens.NUMBER);
                }

                case 2: { // Read "1-9" as first char
                    nextChar(buffer);
                    if (!LexerUtils.isDigit(readChar)) {
                        if (readChar.equals(".")) {
                            state = 3; // Float
                            break;
                        }
                        retract();
                        return constructToken(Tokens.NUMBER);
                    }
                    continue;
                }

                case 3: { // Read a ., expecting at least a digit
                    nextChar(buffer);
                    if (LexerUtils.isDigit(readChar)) {
                        state = 4;
                        continue;
                    }
                    retract(2); // Delete . and current char
                    return constructToken(Tokens.NUMBER);
                }

                case 4: { // Check for float suffix
                    nextChar(buffer);
                    if (LexerUtils.isDigit(readChar))
                        continue;
                    retract();
                    return constructToken(Tokens.NUMBER);
                }
            }
        }
    }

}
