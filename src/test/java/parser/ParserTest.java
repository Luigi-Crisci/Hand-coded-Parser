package parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lexer.com.compiler.Lexer;

/**
 * Unit test for simple App.
 */
public class ParserTest {

    @Test
    public void sourceFile1Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source1.txt");
        Parser parser = new Parser(lexer);
        //Test fails because do and while at line 4 are inverted - Syntax error
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void sourceFile2Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source2.txt");
        Parser parser = new Parser(lexer);
        //Test fails because line 4 has lexical error
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void sourceFile3Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source3.txt");
        Parser parser = new Parser(lexer);
        //TODO: FIX
        //Test should fail because if does not end
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void sourceFile4Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source4.txt");
        Parser parser = new Parser(lexer);
        //Test fails while is not followed by a valid EXPR production
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void sourceFile5Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source5.txt");
        Parser parser = new Parser(lexer);
        //Test fails because of wrong construct
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void sourceFile6Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source6.txt");
        Parser parser = new Parser(lexer);
        //Test fails because line 3 and 5 are not valid statements
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void sourceFile7Test() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/file_source7.txt");
        Parser parser = new Parser(lexer);
        //Test fails because while must be followed by an EXPR
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void nestedIfsTest() {

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
    public void doWhileFalseTest() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/dowhile2");
        Parser parser = new Parser(lexer);

        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void generalFalseTest() {

        Lexer lexer = new Lexer();
        lexer.initialize("./testfiles/general_false");
        Parser parser = new Parser(lexer);

        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }
}
