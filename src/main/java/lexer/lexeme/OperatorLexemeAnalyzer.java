package lexer.lexeme;

import lexer.utils.*;
import java.nio.ByteBuffer;

public class OperatorLexemeAnalyzer extends AbstractLexemeAnalyzer {

    @Override
    public RecognizedToken check(ByteBuffer buffer) {

        reset();

        while (true) {
            switch (state) {
                case 0: {
                    nextChar(buffer);
                    if (LexerUtils.isBlank(readChar)){
                        stringBuffer.deleteCharAt(0);
                        continue;
                    }
                    switch (readChar.charAt(0)) {
                        case '>': {
                            state = 1;
                            continue;
                        }
                        case '<': {
                            state = 2;
                            continue;
                        }
                        case '!': {
                            state = 6;
                            continue;
                        }
                    }
                    return constructToken(Tokens.ERROR);
                }
                case 1: { // Read >
                    nextChar(buffer);
                    if (readChar.equals("=")) {
                        return constructToken(Tokens.RELOP);
                    }
                    retract();
                    return constructToken(Tokens.RELOP);
                }
                case 2: { // Read <
                    nextChar(buffer);
                    if (readChar.equals("="))
                        return constructToken(Tokens.RELOP);
                    if (readChar.equals("-")) {
                        state = 3;
                        continue;
                    }
                    retract();
                    return constructToken(Tokens.RELOP);
                }
                case 3: { // Read <-
                    nextChar(buffer);
                    if (readChar.equals("-"))
                        return constructToken(Tokens.ASSIGN);

                    retract(2);
                    return constructToken(Tokens.RELOP);
                }
                case 6: { // !
                    nextChar(buffer);
                    if (readChar.equals("="))
                        return constructToken(Tokens.RELOP);
                    return constructToken(Tokens.ERROR);
                }
            }
        }

    }

}
