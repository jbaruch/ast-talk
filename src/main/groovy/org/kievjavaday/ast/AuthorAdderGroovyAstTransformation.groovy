package org.kievjavaday.ast

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class AuthorAdderGroovyAstTransformation extends AbstractASTTransformation {
    @Override
    void visit(final ASTNode[] nodes, final SourceUnit source) {
        source.AST.classes.each { node ->
            node.addField new AstBuilder().buildFromSpec {
                fieldNode '$AUTHOR', ACC_PUBLIC | ACC_FINAL | ACC_STATIC,
                        String, this.class, { constant 'jbaruch' }
            }[0] as FieldNode
        }

    }
}