sumar:
LOAD R1, a
ADD R1, b
STORE t0, R1
MOV resultado, t0
LOAD R1, contadorGlobal
ADD R1, 1
STORE t1, R1
MOV contadorGlobal, t1
MOV EAX, resultado
RET
main:
MOV contadorGlobal, 0
LOAD R2, 0
STORE numeros[R2], 10
LOAD R2, 1
STORE numeros[R2], 20
LOAD R2, 2
STORE numeros[R2], 30
LOAD R2, 0
LOAD R1, numeros[R2]
STORE t2, R1
LOAD R2, 1
LOAD R1, numeros[R2]
STORE t3, R1
LOAD R1, t2
ADD R1, t3
STORE t4, R1
MOV temp, t4
LOAD R1, temp
MUL R1, 2
STORE t5, R1
MOV temp, t5
LOAD R1, temp
DIV R1, 3
STORE t6, R1
MOV temp, t6
LOAD R1, temp
MOD R1, 5
STORE t7, R1
MOV temp, t7
PUSH temp
PUSH 5
CALL sumar
MOV t8, EAX
MOV estado, t8
MOV contadorGlobal, estado
LOAD R1, estado
CMP R1, 0
SETG t9
CMP t9, 0
JE L0
LOAD R1, estado
ADD R1, 10
STORE t10, R1
MOV auxiliar, t10
MOV estado, auxiliar
JMP L1
L0:
L1:
MOV EAX, estado
RET
