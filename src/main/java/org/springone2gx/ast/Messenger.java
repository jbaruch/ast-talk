package org.springone2gx.ast;

import org.codehaus.groovy.transform.GroovyASTTransformationClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jbaruch
 * @since 9/9/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@GroovyASTTransformationClass(classes = MessageAdderGroovyAstTransformation.class)
public @interface Messenger {
    boolean shout() default false;
}
