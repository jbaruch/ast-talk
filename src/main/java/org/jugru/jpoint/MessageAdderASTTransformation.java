package org.jugru.jpoint;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

import static org.codehaus.groovy.ast.ClassHelper.*;

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class MessageAdderASTTransformation extends AbstractASTTransformation {
    @Override
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        AnnotationNode greeterAnnotation = ((AnnotationNode) nodes[0]);
        ConstantExpression shout = (ConstantExpression) greeterAnnotation.getMember("shout");
        ClassNode annotatedClass = (ClassNode) nodes[1];

        VariableExpression message = new VariableExpression("message");

        MethodCallExpression toUpperCaseCall = new MethodCallExpression(message,
                "toUpperCase", ArgumentListExpression.EMPTY_ARGUMENTS);

        ExpressionStatement code = new ExpressionStatement(
                new MethodCallExpression(new VariableExpression("this"), "println",
                        new TernaryExpression(new BooleanExpression(shout), toUpperCaseCall, message))
        );

        annotatedClass.addMethod("message",
                ACC_PUBLIC, VOID_TYPE,
                new Parameter[]{new Parameter(STRING_TYPE, "message")},
                ClassNode.EMPTY_ARRAY,
                code);
    }
}