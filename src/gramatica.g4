grammar gramatica;

r: (declaracion | funcion)+ EOF ;

declaracion
    : tipo ID (ASSIGN expr)? SEMICOLON                 # DeclVar
    | tipo ID LBRACKET NUMBER RBRACKET SEMICOLON       # DeclArray
    ;

funcion
    : tipo ID LPAREN parametros? RPAREN bloque         # DeclFuncion
    | 'void' ID LPAREN parametros? RPAREN bloque      # DeclFuncionVoid
    ;

parametros: parametro (COMMA parametro)* ;
parametro: tipo ID ;

bloque: LBRACE sentencia* RBRACE ;

sentencia
    : declaracion                                      # DeclStat
    | asignacion SEMICOLON                             # AsignacionStat
    | IF LPAREN expr RPAREN bloque (ELSE bloque)?      # IfStat
    | WHILE LPAREN expr RPAREN bloque                  # WhileStat
    | 'for' LPAREN asignacion SEMICOLON expr SEMICOLON asignacion RPAREN bloque # ForStat
    | RETURN expr? SEMICOLON                           # ReturnStat
    | expr SEMICOLON                                   # ExprStat
    ;

asignacion
    : ID ASSIGN expr                                   # AsignacionSimple
    | ID LBRACKET expr RBRACKET ASSIGN expr            # AsignacionArray
    ;

expr
    : expr op=(MUL|DIV|MOD) expr                       # MulDivExpr
    | expr op=(PLUS|MINUS) expr                        # AddSubExpr
    | expr op=(MAYOREQUAL|MENOREQUAL|MAYOR|MENOR|EQUAL|NOT_EQUAL) expr # CompExpr
    | NUMBER                                           # NumberExpr
    | FLOAT_NUMBER                                     # FloatExpr
    | CHAR_LITERAL                                     # CharExpr
    | ID                                               # IdExpr
    | ID LBRACKET expr RBRACKET                        # ArrayAccessExpr
    | ID LPAREN argumentos? RPAREN                     # CallExpr
    | LPAREN expr RPAREN                               # ParensExpr
    | (TRUE|FALSE)                                     # BoolExpr
    ;

argumentos: expr (COMMA expr)* ;

tipo: INT | FLOAT | DOUBLE | CHAR | BOOL | VOID ;

// (Mantener todos los tokens del Lexer originales aquí)


INT        : 'int' ;
CHAR       : 'char';
BOOL       : 'bool';
FLOAT      : 'float';
DOUBLE     : 'double';
VOID       : 'void';
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