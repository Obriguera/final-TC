# C-Like Compiler: Lexical, Syntactic & Semantic Analysis

This project is a functional compiler for a C-like programming language, developed as part of the **Compiler Techniques** final examination. It utilizes **ANTLR 4.13.2** to perform a complete analysis pipeline, from raw source code to semantic validation.

## 🚀 Key Features

- **Lexical & Syntactic Analysis**: Full grammar implementation supporting functions, control structures (`if`, `while`, `for`), arrays, and multiple data types.
- **Semantic Analysis**: Implementation of a **Visitor Pattern** to bridge the gap between structure and meaning.
- **Advanced Symbol Table**: 
    - **Scope Management**: Uses a Stack of HashMaps to handle global and local scopes.
    - **Shadowing Support**: Correctly resolves variable names based on the innermost active scope.
    - **Declaration Validation**: Detects duplicate declarations and undeclared variables.
- **Syntax-Directed Translation (SDT)**: Leverages synthesized attributes to propagate information up the parse tree.

## 🛠 Tech Stack

- **Language**: Java
- **Tool**: [ANTLR 4.13.2](https://www.antlr.org/)
- **IDE**: IntelliJ IDEA

## 📂 Project Structure

* `src/gramatica.g4`: The ANTLR grammar definition (Lexer + Parser).
* `src/Main.java`: The entry point that orchestrates the loading, parsing, and visiting of the source code.
* `src/Semantico.java`: Custom Visitor implementation for semantic checks.
* `src/TablaSimbolos.java`: Logic for managing symbol scopes and life cycles.
* `input/`: Directory containing test files:
    * `ejemplo-correcto.txt`: Demonstrates valid language constructs.
    * `ejemplo-multiples-errores.txt`: Used to test the robustness of the semantic error reporting.

## ⚙️ Setup & Execution

### Prerequisites
- Java JDK 11 or higher.
- `antlr-4.13.2-complete.jar` added to the project libraries.

### Running in IntelliJ IDEA
1. Install the **ANTLR v4 grammar plugin**.
2. Right-click `gramatica.g4` and select **Generate ANTLR Recognizer**.
3. Ensure the generated folder (`gen`) is marked as **Generated Sources Root**.
4. Run `Main.java` and provide the path to your input file.

## 📝 Semantic Rules & Validations

The compiler performs the following checks during the semantic phase:
1. **Variable Definition**: Ensures variables are declared before use.
2. **Scope Isolation**: Variables declared inside blocks (like `for` loops or `if` statements) are inaccessible outside.
3. **Function Parameters**: Function parameters are automatically injected into the function's local scope.
4. **Duplicate Prevention**: Prevents multiple declarations of the same identifier within the same scope.

---
**Author:** [Octavio Briguera]  
**Course:** Compiler Techniques (2026)
