# C-Like Compiler with ML-Driven Optimization

This project is a complete, end-to-end compiler for a C-like programming language, developed as part of the **Compiler Techniques** final exam at Universidad Blas Pascal. It utilizes **ANTLR 4.13.2** for the frontend pipeline and integrates a **Machine Learning Agent** (using the Smile library) to perform predictive code optimization before generating x86 pseudo-assembly.

## 🥽 Key Features

- **Robust Frontend**: Lexical, syntactic, and semantic analysis with custom error interception to prevent AST corruption.
- **Advanced Symbol Table**: 
    - **Scope Management**: Uses a Stack of HashMaps to handle global and local scopes seamlessly.
    - **Shadowing & Validation**: Resolves variable names based on the innermost active scope, prevents duplicate declarations, and warns about unused variables.
- **Intermediate Representation (TAC)**: Translates the complex Abstract Syntax Tree (AST) into linear Three-Address Code, utilizing temporary variables (`tX`) and jump labels (`Lxx`) to flatten control flow.
- **Dual-Pass Optimization Engine**:
    - **Algorithmic**: Implements static *Data Flow Analysis* for Constant Folding, Constant Propagation, and Algebraic Simplification.
    - **Predictive (AI)**: Integrates a **Decision Tree Classifier (CART)**. The optimizer acts as a feature extractor (counting assignments and reads), and the AI infers whether a variable is "dead code", intelligently pruning the TAC list to reduce binary size.
- **Backend ASM Generation**: Translates the optimized TAC into a register-memory pseudo-assembly architecture (`output.asm`), strictly handling calling conventions (`PUSH`, `CALL`, `EAX` for returns) and array offset memory management.

## 🛠 Tech Stack

- **Language**: Java (JDK 21+)
- **Parser Generator**: [ANTLR 4.13.2](https://www.antlr.org/)
- **Machine Learning**: [Smile (Statistical Machine Intelligence and Learning Engine) v2.6.0](https://haifengl.github.io/)
- **IDE**: IntelliJ IDEA

## 📂 Project Architecture

* `gramatica.g4`: ANTLR grammar definition (Lexer + Parser).
* `Main.java`: The orchestrator that connects the pipeline phases.
* `ManejadorErrores.java`: Custom error listener to halt compilation on syntax faults.
* `Semantico.java` & `TablaSimbolos.java`: Semantic validation, scope tracking, and strict type-checking via the Visitor pattern.
* `GeneradorTAC.java`: Flattens the AST into Three-Address Code instructions.
* `Optimizador.java` & `AgenteIA.java`: The optimization core featuring algebraic reduction and the Machine Learning classifier.
* `GeneradorEnsamblador.java`: The backend translator that outputs the final `.asm` file.

## ⚙️ Setup & Execution

### Prerequisites
- Java JDK 21 or higher.
- `antlr-4.13.2-complete.jar` added to the classpath.
- Smile ML libraries (`smile-core-2.6.0.jar`, `smile-data-2.6.0.jar`, `smile-math-2.6.0.jar`) added to the classpath.