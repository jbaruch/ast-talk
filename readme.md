

# Getting practical with AST Transformations

## Baruch Sadogursky, Dev Advocate, JFrog
## @jbaruch

![Devoxx BG](http://devoxxbelgium.s3-eu-west-1.amazonaws.com/wp-content/uploads/2015/06/08154520/Devoxx2015Thema.jpg)

 - Why?
     - Getter and setter example getters/setters: org.devoxx.Person
 - How?
    - Abstract Syntax Tree
        - Oxford comma
        - Assert
            - AST Browser
 - Adding author field
     - Global transformations vs. local transformations and more!
     - Testing transformations
        - org.devoxx.ast.AstTransformationsTests
     - Writing global transformation
        - org.devoxx.ast.AuthorAdderAstTransformation
        - Compiler phases
        - META-INF\services\org.codehaus.groovy.transform.ASTTransformation
 - Adding message method
    - Writing local transformations
        - Annotation org.devoxx.ast.Messenger
        - org.devoxx.ast.MessageAdderAstTransformation
            - Achievement unlocked: [x] Hell of AST!
 - Adding main method
    - Another local transformation
        - Annotation org.devoxx.ast.Main
        - MainAdderAstTransformation
        - Writing defensive code
              - Achievement unlocked: [x] Compiler emits your messages!
- AST for humans
    - Less hell - Using GeneralUtils
        - Simplifying MessageAdder with GeneralUtils
    - Even less hell (Hello, Groovy!)
        - org.devoxx.ast.AuthorAdderGroovyAstTransformation
- Performant AST
    - Welcome back to Java? Not so fast (Cause Groovy is fast)!
 - Questions from survivors
