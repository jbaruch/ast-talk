package org.jugru.jpoint

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.control.CompilePhase.SEMANTIC_ANALYSIS

@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class MessageAdderGroovyASTTransformation extends AbstractASTTransformation {
    @Override
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        AnnotationNode greeterAnnotation = ((AnnotationNode) nodes[0]);
        ConstantExpression shout = (ConstantExpression) greeterAnnotation.getMember("shout");
        ClassNode annotatedClass = (ClassNode) nodes[1];

        ExpressionStatement stmt = macro {
            println (shout.value as boolean) ? $v {
                macro { message.toUpperCase() };
            } : message
        }
        annotatedClass.addMethod("sayHello",
                ACC_PUBLIC, ClassHelper.VOID_TYPE,
                [new Parameter(ClassHelper.STRING_TYPE, "message")] as Parameter[],
                ClassNode.EMPTY_ARRAY, stmt)
    }

}
