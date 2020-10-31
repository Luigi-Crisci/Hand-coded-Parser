package parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lexer.com.compiler.Lexer;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void ifTest() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/nestedifs");
        Parser parser = new Parser(lexer);

        assertTrue("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void doWhileTest() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/dowhile");
        Parser parser = new Parser(lexer);

        assertTrue("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void doWhileTest_false() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/dowhile2");
        Parser parser = new Parser(lexer);

        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void general_false() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/general_false");
        Parser parser = new Parser(lexer);

        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }
}
