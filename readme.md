
** Getting practical with AST Transformations **
------------------------------------------------

 - Why?
     - Getter and setter example getters/setters
 - How?
    - Abstract Syntax Tree
        - Oxford comma
        - Assert
            - AST Browser
 - Adding author field
     - Global transformations vs. local transformations and more!
     - Testing transformations
        - org.jetconf.ast.AstTransformationsTests
     - Writing global transformation
        - org.jetconf.ast.AuthorAdderAstTransformation
        - Compiler phases
        - META-INF\services\org.codehaus.groovy.transform.ASTTransformation
 - Adding message method
    - Writing local transformations
        - Annotation org.jetconf.ast.Messenger
        - org.jetconf.ast.MessageAdderAstTransformation
            - Achievement unlocked: [x] Hell of AST!
 - Adding main method
    - Another local transformation
        - Annotation org.jetconf.ast.Main
        - org.jetconf.ast.MainAdderAstTransformation
        - Writing defensive code
              - Achievement unlocked: [x] Compiler emits your messages!
- AST for humans
    - Less hell - Using GeneralUtils
        - Simplifying MessageAdder with GeneralUtils
    - Even less hell (Hello, Groovy!)
        - org.jetconf.ast.AuthorAdderGroovyAstTransformation
    - Almost no hell (More Groovy - Macro extensions!)
        - org.jetconf.ast.MessageAdderGroovyAstTransformation
- Performant AST
    - Welcome back to Java? Not so fast!
 - Questions from survivors
