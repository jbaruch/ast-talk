package org.jetconf.ast;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.reflection.ParameterTypes;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

/**
 * @author jbaruch
 * @since 9/28/15
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class MessageAdderAstTransformation extends AbstractASTTransformation {

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        AnnotationNode messageAnnotation = (AnnotationNode) nodes[0];
        ConstantExpression shout = (ConstantExpression) messageAnnotation.getMember("shout");
        ClassNode annotatedClass = (ClassNode) nodes[1];

        VariableExpression message = new VariableExpression("message");

        MethodCallExpression toUpperCaseCall = new MethodCallExpression(message, "toUpperCase",
                ArgumentListExpression.EMPTY_ARGUMENTS);

        ExpressionStatement code = new ExpressionStatement(
                new MethodCallExpression(new VariableExpression("this"), "println",
                        new TernaryExpression(new BooleanExpression(shout), toUpperCaseCall, message))
        );

        annotatedClass.addMethod("message",
                ACC_PUBLIC, ClassHelper.VOID_TYPE,
                new Parameter[]{new Parameter(ClassHelper.STRING_TYPE, "message")},
                ClassNode.EMPTY_ARRAY,
                code);
    }
}
