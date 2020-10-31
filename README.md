# Hand-coded Parser

A simple recursive-descend  Parser in pure Java

## Build  

### Requirements

- Java 11
- Maven

### IntelliJ Configuratiom

This project is provided with a set of *IntelliJ Configurations:* 

> To run it just import it as a *Maven project* and click *Run*

### Maven Build

Alternatively you can compile manually typing: 

> mvn package

The *jar* file will be placed under the *target/* directory.  

## Structure

The software is composed of two parts:  
- Lexer
- Parser

The lexer used in this software is fully described at this [link](https://gitlab.com/compilatori-a.a.-2020_21/hand-coded-lexer-es1_hcl/crisci-cuccurullo_es1_hcl).

In the following sections the structure of the *parser* will be described.

### Grammar

The **Grammar** the parser recognize is the following one:

> Progam -> Stmt Program2   
> Program2 -> ; Stmt Program2  
> Program2 -> ''  
> Stmt -> if Expr then Stmt else Stmt   
> Stmt -> id assign Expr  
> Stmt -> do Stmt while Expr  
> Expr -> id relop Expr2  
> Expr -> num relop Expr2  
> Expr2 -> id Expr3  
> Expr2 -> num Expr3  
> Expr3 -> relop Expr2  
> Expr3 -> ''  


### Class Structure

The class `Parser` takes a `Lexer` object in input and expose a single method `boolean parse()`, which return `true` if the input file text is accepted by the *grammar* and all the file has been  analyzed, false otherwise.  
The `parse` method maintain a buffer of the token recognized from the `lexer` during the parsing phase and a pointer to the current token being analyzed.

For each nonterminale, a method `boolean productionNONTERMINAL_NAME()` is defined, which return `true` if the token sequence read match a production of that specification nonterminal. Because of the recursive-descend nature of this parser, backtracking could be necessary when an incorrect production is chosen: for this case, each method maintains a `int startingPos` variable pointing to the first token to analyze and, when backtracking is necessary, it sets `pointer` to `startingPos` and try another production.  

If no produciton match the token sequence, each method resets `pointer` to `startingPos` and returns `false`.  

## Authors
- *Luigi Crisci*
- *Alessio Cuccurullo*


