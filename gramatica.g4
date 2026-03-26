grammar gramatica;

r: (ID | INT | IF | ELSE | WHILE | BREAK | RETURN | TRUE | FALSE | NUMBER | ASSIGN | EQUAL | NOT_EQUAL | PLUS | MINUS | MUL | DIV | POW | MAYOR | MENOR | MENOREQUAL | MAYOREQUAL | LPAREN | RPAREN | SEMICOLON | WS | LINE_COMMENT | BLOCK_COMMENT )* ;

INT: 'int' ;
IF: 'if' ;
ELSE : 'else' ;
WHILE : 'while' ;
BREAK : 'break' ;
RETURN : 'return' ;
TRUE : 'true' ;
FALSE : 'false' ;


//IDENTIFIERS
ID : [a-zA-Z][a-zA-Z_0-9]+ ;

NUMBER: [0-9]+ ;

//OPERADORES
ASSIGN : '=' ;
EQUAL : '==' ;
NOT_EQUAL : '!=' ;
PLUS : '+' ;
MINUS : '-' ;
MUL : '*' ;
DIV : '/' ;
POW : '^' ;

MAYOR : '>' ;
MENOR : '<' ;
MAYOREQUAL : '>=' ;
MENOREQUAL : '<=' ;  

//SEPARADORES
LPAREN : '(' ;
RPAREN : ')' ;
SEMICOLON : ';' ;

//ESPACIOS
WS : [ \t\r\n]+ -> skip ;
//COMENTARIOS
LINE_COMMENT : '//' ~[r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;