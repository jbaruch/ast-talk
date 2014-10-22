package org.springone2gx.ast;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

import static org.codehaus.groovy.ast.ClassHelper.STRING_TYPE;
import static org.codehaus.groovy.ast.ClassHelper.VOID_TYPE;
import static org.codehaus.groovy.ast.expr.ArgumentListExpression.EMPTY_ARGUMENTS;
import static org.codehaus.groovy.ast.expr.MethodCallExpression.NO_ARGUMENTS;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class MainAdderAstTransformation extends AbstractASTTransformation {
    @Override
    public void visit(final ASTNode[] nodes, final SourceUnit source) {

        MethodNode annotatedMethod = ((MethodNode) nodes[1]);
        ClassNode declaringClass = annotatedMethod.getDeclaringClass();

        Parameter[] emptyArrayParams = {new Parameter(STRING_TYPE.makeArray(), "args")};

        if (declaringClass.hasMethod("main", emptyArrayParams)) {
            source.addError(new SyntaxException("Can't add another main method!", 999, 999));
        }

        ConstructorCallExpression constructorCall = new ConstructorCallExpression(
                declaringClass, EMPTY_ARGUMENTS);

        ExpressionStatement code = new ExpressionStatement(new MethodCallExpression(constructorCall,
                annotatedMethod.getName(), NO_ARGUMENTS));

        declaringClass.addMethod("main",
                ACC_PUBLIC | ACC_STATIC, VOID_TYPE,
                new Parameter[]{new Parameter(STRING_TYPE.makeArray(), "args")},
                ClassNode.EMPTY_ARRAY, code);

    }
}