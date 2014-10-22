package org.springone2gx.ast;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

import static org.codehaus.groovy.ast.ClassNode.EMPTY_ARRAY;
import static org.codehaus.groovy.ast.tools.GeneralUtils.*;

import static org.codehaus.groovy.ast.ClassHelper.*;

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class MessageAdderAstTransformation extends AbstractASTTransformation {
    @Override
    public void visit(final ASTNode[] nodes, final SourceUnit source) {

        VariableExpression message = new VariableExpression("message");

        ((ClassNode) nodes[1]).addMethod("message",
                ACC_PUBLIC, VOID_TYPE,
                params(param(STRING_TYPE, "message")),
                EMPTY_ARRAY,
                stmt(callThisX("println",
                        ternaryX(((AnnotationNode) nodes[0]).getMember("shout"),
                                callX(message, "toUpperCase"), message))));
    }
}