# Hand-coded Parser

A simple Parser in pure Java

## Grammar

> Progam -> Stmt; Program2   
> Program2 -> Stmt Program2  
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
