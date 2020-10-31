package lexer.com.compiler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import lexer.lexeme.AbstractLexemeAnalyzer;
import lexer.utils.LexemeAnalyzerLoader;
import lexer.utils.RecognizedToken;
import lexer.utils.Tokens;
import static lexer.utils.LexerUtils.EMPTY_TOKEN;

public class Lexer {

	private final int BUFFER_DIMENSION = 3000000;

	private FileChannel inputFileChannel;
	private ByteBuffer buffer;
	private static StringTable stringTable;
	public Set<AbstractLexemeAnalyzer> analyzers;

	public Lexer() {
		stringTable = new StringTable();// Get all analyzer classes from package
		buffer = ByteBuffer.allocate(BUFFER_DIMENSION);
		allocateAnalyzers();
	}

	public Boolean initialize(String filePath) {
		try {
			inputFileChannel = FileChannel.open(Paths.get(filePath));
			// Prima lettura
			inputFileChannel.read(buffer);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Token nextToken() throws Exception {

	
		buffer.rewind();
		if (isEmpty())
			return EMPTY_TOKEN;

		RecognizedToken recognizedToken;
		recognizedToken = analyzers.stream().map(e -> e.check(buffer.asReadOnlyBuffer())).max((token1, token2) -> {
			int res = token1.compareTo(token2); // First we compare token length
			if (res == 0)
				res = token1.token.compareTo(token2.token); // If equals, the chose is based on token priority
			return res;
		}).get();

		consumeCharacterReadFromBuffer(recognizedToken);

		installID(recognizedToken.token);

		return recognizedToken.token;
	}

	private void consumeCharacterReadFromBuffer(RecognizedToken recognizedToken) {
		buffer.position(recognizedToken.character_read);
		buffer = buffer.compact();
		buffer.rewind();
	}

	private void installID(Token token) {
		int key;
		if (Tokens.valueOf(token.getName()).equals(Tokens.ID)) {
			if (stringTable.containsValue(token.getAttribute())) {
				key = stringTable.entrySet().parallelStream().filter(e -> e.getValue().equals(token.getAttribute()))
						.findFirst().get().getKey();
				token.setAttribute("" + key);
			} else {
				key = stringTable.size();
				stringTable.putIfAbsent(stringTable.size(), token.getAttribute());
				token.setAttribute("" + key);
			}
		}
	}

	private void allocateAnalyzers() {
		analyzers = new HashSet<>();
		LexemeAnalyzerLoader.loadLexemeAnalyzerClasses().forEach(e -> {
			try {
				analyzers.add(e.getConstructor().newInstance()); // Declare an object foreach class
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		});
	}

	private boolean isEmpty() {
		ByteBuffer temp = buffer.asReadOnlyBuffer();
		while(temp.get()==' '){}
		return temp.get() == (char) 0;
	}

	public StringTable getStringTable() {
		return stringTable;
	}
}
