package parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lexer.com.compiler.Lexer;

/**
 * Unit test for simple App.
 */
public class SuggestedTestSuite {

    @Test
    public void Test1() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source1.txt");
        Parser parser = new Parser(lexer);
        //Test fails because do and while at line 4 are inverted - Syntax error
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void Test2() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source2.txt");
        Parser parser = new Parser(lexer);
        //Test fails because line 4 has lexical error
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void Test3() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source3.txt");
        Parser parser = new Parser(lexer);
        //TODO: FIX
        //Test should fail because if does not end
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void Test4() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source4.txt");
        Parser parser = new Parser(lexer);
        //Test fails while is not followed by a valid EXPR production
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void Test5() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source5.txt");
        Parser parser = new Parser(lexer);
        //Test fails because of wrong construct
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void Test6() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source6.txt");
        Parser parser = new Parser(lexer);
        //Test fails because line 3 and 5 are not valid statements
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }

    @Test
    public void Test7() {

        Lexer lexer = new Lexer();
        lexer.initialize("./test_suite/file_source7.txt");
        Parser parser = new Parser(lexer);
        //Test fails because while must be followed by an EXPR
        assertFalse("Il file non rispetta la grammatica", parser.parse());
    }
}
