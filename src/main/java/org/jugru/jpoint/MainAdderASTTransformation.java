package org.jugru.jpoint;

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
public class MainAdderASTTransformation extends AbstractASTTransformation {
    @Override
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        //TODO проверить, что есть ноуды, что их две, что они нужых типов
        //TODO проверить, что есть паблик конструктор без параметров

        MethodNode annotatedMethod = ((MethodNode) nodes[1]);
        ClassNode declaringClass = annotatedMethod.getDeclaringClass();

        Parameter[] stringArrayParam = {new Parameter(STRING_TYPE.makeArray(), "args")};

        if (declaringClass.hasDeclaredMethod("main", stringArrayParam)) {
            source.addError(new SyntaxException("Yo, man, I own the compiler! Can't add main method, class " + declaringClass.getName() + " already has main method",
                    999, 999));
        }

        ConstructorCallExpression constructorCall = new ConstructorCallExpression(
                declaringClass, EMPTY_ARGUMENTS);

        ExpressionStatement code = new ExpressionStatement(new MethodCallExpression(constructorCall,
                annotatedMethod.getName(), NO_ARGUMENTS));


        declaringClass.addMethod("main",
                ACC_PUBLIC | ACC_STATIC, VOID_TYPE,
                stringArrayParam,
                ClassNode.EMPTY_ARRAY, code);

    }
}