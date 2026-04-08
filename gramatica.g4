grammar gramatica;

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
	| FLOAT_NUMBER
	| NUMBER
	| CHAR_LITERAL
	| EQUAL
	| ASSIGN
	| NOT_EQUAL
	| PLUS
	| MINUS
	| MUL
	| DIV
	| MOD
	| POW
	| MAYOREQUAL
	| MENOREQUAL
	| MAYOR
	| MENOR
	| DOT
	| LPAREN
	| RPAREN
	| SEMICOLON
    | LBRACKET   
    | RBRACKET   
    | LBRACE     
    | RBRACE
    | COMMA
	;

INT        : 'int' ;
IF         : 'if' ;
ELSE       : 'else' ;
WHILE      : 'while' ;
BREAK      : 'break' ;
RETURN     : 'return' ;
TRUE       : 'true' ;
FALSE      : 'false' ;

//IDENTIFICADORES
ID         : [a-zA-Z_] [a-zA-Z_0-9]* ;
FLOAT_NUMBER : [0-9]+ '.' [0-9]+ ;
NUMBER     : [0-9]+ ;
CHAR_LITERAL : '\'' ( '\\' . | ~['\\\r\n] ) '\'' ;

//OPERADORES
EQUAL      : '==' ;
ASSIGN     : '=' ;
NOT_EQUAL  : '!=' ;
PLUS       : '+' ;
MINUS      : '-' ;
MUL        : '*' ;
DIV        : '/' ;
MOD        : '%' ;
POW        : '^' ;
MAYOREQUAL : '>=' ;
MENOREQUAL : '<=' ;
MAYOR      : '>' ;
MENOR      : '<' ;
DOT        : '.' ;

//SEPARADORES
LPAREN     : '(' ;
RPAREN     : ')' ;
LBRACKET   : '[' ;
RBRACKET   : ']' ;
LBRACE     : '{' ;
RBRACE     : '}' ;
SEMICOLON  : ';' ;
COMMA      : ',' ;

//ESPACIOS Y COMENTARIOS
WS           : [ \t\r\n]+ -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT: '/*' .*? '*/' -> skip ;

//MANEJO DE ERRORES
ERROR_CHAR : . ;