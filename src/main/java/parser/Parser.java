package parser;
/*
	Grammar:
	Program -> Stmt Program2
	Program2 -> ; Stmt Program2
	Program2 -> ''
	Stmt -> if Expr then Stmt else Stmt
	Stmt -> id assign Expr
	Stmt -> do Stmt while Expr
	Expr -> id relop Expr2
	Expr -> num relop Expr2
	Expr2 -> id Expr3
	Expr2 -> num Expr3
	Expr3 -> relop Expr2
	Expr3 -> ''
*/

import java.util.*;

import lexer.com.compiler.Lexer;
import lexer.com.compiler.Token;
import lexer.utils.LexerUtils;
import lexer.utils.Tokens;
import static lexer.utils.Tokens.*;

class Parser {

	private int pointer;
	private ArrayList<Token> input;
	private Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		pointer = 0;
		input = new ArrayList<>();
	}

	public boolean parse() {
		return productionProgram();
	}

	public boolean productionProgram() {
		int startingPos = pointer;
		if (!productionStmt())
			return errorRecovery(startingPos);
		if (!productionProgram2())
			return errorRecovery(startingPos);
		return true;
	}

	public boolean productionProgram2() {
		int startingPos = pointer;

		if (nextTokenIs(EMPTY))
			return true;

		resetPosition(startingPos);
		if (nextTokenIs(S_COLON)) {
			if (!productionStmt())
				return errorRecovery(startingPos);
			return productionProgram2();
		}

		// Epslon transition
		resetPosition(startingPos);
		return true;
	}

	public boolean productionStmt() {
		int startingPos = pointer;
		
		if (nextTokenIs(IF)) {
			if (productionExpr()) {
				if (nextTokenIs(THEN)) {
					if (productionStmt()) {
						if (nextTokenIs(ELSE)) {
							if (productionStmt())
								return true;
						}
					}
				}
			}
		}

		resetPosition(startingPos);
		if (nextTokenIs(ID)) {
			if (nextTokenIs(ASSIGN))
				if (productionExpr())
					return true;
		}

		resetPosition(startingPos);
		if (nextTokenIs(DO)) {
			if (productionStmt()) {
				if (nextTokenIs(WHILE)) {
					if (productionExpr())
						return true;
				}
			}
		}

		return errorRecovery(startingPos);
	}

	public boolean productionExpr() {
		int startingPos = pointer;

		if (nextTokenIs(ID)) {
			if (nextTokenIs(RELOP)) {
				if (productionExpr2())
					return true;
			}
		}

		resetPosition(startingPos);
		if (nextTokenIs(NUMBER)) {
			if (nextTokenIs(RELOP)) {
				if (productionExpr2())
					return true;
			}
		}

		return errorRecovery(startingPos);
	}

	public boolean productionExpr2() {
		int startingPos = pointer;

		if (nextTokenIs(ID)) {
			if (productionExpr3())
				return true;
		}

		resetPosition(startingPos);
		if(nextTokenIs(NUMBER)){
			if(productionExpr3())
				return true;
		}

		return errorRecovery(startingPos);
	}

	public boolean productionExpr3() {
		int startingPos = pointer;
		if (nextTokenIs(RELOP)) {
			return productionExpr2();
		}

		resetPosition(startingPos);
		return true;
	}

	private boolean nextTokenIs(Tokens currentToken) {

		Token t;
		try {
			if (!(t = lexer.nextToken()).is(EMPTY) || !input.contains(LexerUtils.EMPTY_TOKEN))
				input.add(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		t = input.get(pointer);
		if(input.size() > pointer && !t.equals(LexerUtils.EMPTY_TOKEN))
			pointer++;
		return t.is(currentToken);
	}
	
	private boolean errorRecovery(int startingPos) {
		pointer = startingPos;
		return false;
	}

	private void resetPosition(int startingPos){
		pointer = startingPos;
	}
	
}