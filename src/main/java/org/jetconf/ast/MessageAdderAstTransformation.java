package org.jetconf.ast;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

import static org.codehaus.groovy.ast.ClassHelper.*;
import static org.codehaus.groovy.ast.ClassNode.EMPTY_ARRAY;
import static org.codehaus.groovy.ast.tools.GeneralUtils.*;

/**
 * @author jbaruch
 * @since 9/28/15
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class MessageAdderAstTransformation extends AbstractASTTransformation {

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        ((ClassNode) nodes[1]).addMethod("message",
                ACC_PUBLIC, VOID_TYPE,
                params(param(STRING_TYPE, "message")),
                EMPTY_ARRAY,
                stmt(callThisX("println",
                        ((Boolean) getMemberValue((AnnotationNode) nodes[0],
                                "shout")) ?
                                callX(varX("message"), "toUpperCase") :
                                varX("message"))));

    }
}
