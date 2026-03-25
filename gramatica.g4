grammar gramatica;


INT: 'int' ;
IF: 'if' ;
ELSE : 'else' ;
WHILE: 'while' ;


//IDENTIFIERS
ID : [a-zA-Z][a-zA-Z_0-9]+ ;


NUMBER: [0-9]+ ;

//OPERADORES
ASSING : '=' ;
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

//COMENTARIOS EN LINEA
LINE_COMMENT: '//' ~[r\n]* -> skip;

//COMENTARIOS EN BLOQUE
LINE_COMMENT: '/*' .*? '*/' -> skip;