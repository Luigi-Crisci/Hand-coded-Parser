package lexer.lexeme;

import java.nio.ByteBuffer;

import lexer.utils.LexerUtils;
import lexer.utils.RecognizedToken;
import lexer.utils.Tokens;

public class SeparatorLexemeAnalyzer extends AbstractLexemeAnalyzer {

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
					if (readChar.equals(";"))
						return constructToken(Tokens.S_COLON);
					return constructToken(Tokens.ERROR);
				}
			}
		}
	}

}
