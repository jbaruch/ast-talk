package org.springone2gx.ast;

import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import ru.trylogic.groovy.macro.transform.Macro;
import ru.trylogic.groovy.macro.transform.MacroContext;

import static org.codehaus.groovy.ast.tools.GeneralUtils.*;

/**
 * @author jbaruch
 * @since 06/09/2014
 */
class SafeAdderMacroExtension {

    @Macro
    public static Expression safe(MacroContext macroContext, MethodCallExpression callExpression) {
        return ternaryX(notNullX(callExpression.getObjectExpression()),
                callExpression,
                constX(null));
    }
}
