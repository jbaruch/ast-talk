package org.springone2gx.ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.ClassHelper.STRING_TYPE
import static org.codehaus.groovy.ast.ClassHelper.VOID_TYPE
import static org.codehaus.groovy.ast.ClassNode.EMPTY_ARRAY
import static org.codehaus.groovy.ast.tools.GeneralUtils.param
import static org.codehaus.groovy.ast.tools.GeneralUtils.params
import static org.codehaus.groovy.control.CompilePhase.SEMANTIC_ANALYSIS

@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
class MessageAdderGroovyAstTransformation extends AbstractASTTransformation {
    @Override
    void visit(final ASTNode[] nodes, final SourceUnit source) {
        Expression member = (nodes[0] as AnnotationNode).getMember('shout')
        (nodes[1] as ClassNode).addMethod('message',
                ACC_PUBLIC, VOID_TYPE, params(param(STRING_TYPE, 'message')),
                EMPTY_ARRAY, macro(true) {
            println $v { member } ? message.toUpperCase() : message
        } as Statement)
    }
}

