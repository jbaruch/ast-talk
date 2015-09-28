package org.jetconf.ast;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformationClass;

/**
 * @author jbaruch
 * @since 9/28/15
 */
@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class MainAdderAstTransformation extends AbstractASTTransformation {
    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        super.init(nodes, source);
        MethodNode annotatedMethod = (MethodNode) nodes[1];
        ClassNode declaringClass = annotatedMethod.getDeclaringClass();

        Parameter[] emptyArrayParams = {new Parameter(ClassHelper.STRING_TYPE.makeArray(), "args")};

        if(declaringClass.hasMethod("main", emptyArrayParams)) {
            addError("The class already contains main method", declaringClass.getMethod("main", emptyArrayParams));
        }

        ConstructorCallExpression constructorCall =
                new ConstructorCallExpression(declaringClass, ArgumentListExpression.EMPTY_ARGUMENTS);

        ExpressionStatement code = new ExpressionStatement(new MethodCallExpression(constructorCall,
                annotatedMethod.getName(), MethodCallExpression.NO_ARGUMENTS));

        declaringClass.addMethod("main", ACC_PUBLIC | ACC_STATIC, ClassHelper.VOID_TYPE,
                emptyArrayParams, ClassNode.EMPTY_ARRAY, code);
    }
}
