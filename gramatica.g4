grammar gramatica;

// Regla de entrada minima para consumir una secuencia de tokens.
r: token* EOF;

token
	: INT
	| IF
	| ELSE
	| WHILE
	| BREAK
	| RETURN
	| TRUE
	| FALSE
	| ID
	| NUMBER
	| EQUAL
	| ASSIGN
	| NOT_EQUAL
	| PLUS
	| MINUS
	| MUL
	| DIV
	| POW
	| MAYOREQUAL
	| MENOREQUAL
	| MAYOR
	| MENOR
	| LPAREN
	| RPAREN
	| SEMICOLON
	;

INT        : 'int' ;
IF         : 'if' ;
ELSE       : 'else' ;
WHILE      : 'while' ;
BREAK      : 'break' ;
RETURN     : 'return' ;
TRUE       : 'true' ;
FALSE      : 'false' ;

// IDENTIFICADORES
ID         : [a-zA-Z] [a-zA-Z_0-9]* ;
NUMBER     : [0-9]+ ;

// OPERADORES
EQUAL      : '==' ;
ASSIGN     : '=' ;
NOT_EQUAL  : '!=' ;
PLUS       : '+' ;
MINUS      : '-' ;
MUL        : '*' ;
DIV        : '/' ;
POW        : '^' ;
MAYOREQUAL : '>=' ;
MENOREQUAL : '<=' ;
MAYOR      : '>' ;
MENOR      : '<' ;

// SEPARADORES
LPAREN     : '(' ;
RPAREN     : ')' ;
SEMICOLON  : ';' ;

// ESPACIOS Y COMENTARIOS
WS           : [ \t\r\n]+ -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT: '/*' .*? '*/' -> skip ;

// Captura cualquier caracter no reconocido para reportar errores lexicos.
ERROR_CHAR : . ;