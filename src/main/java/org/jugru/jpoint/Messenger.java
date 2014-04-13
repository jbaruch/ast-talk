package org.jugru.jpoint;

import org.codehaus.groovy.transform.GroovyASTTransformationClass;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author jbaruch
 * @since 13/04/2014
 */

@Retention(SOURCE)
@Target(TYPE)
@GroovyASTTransformationClass(classes = MessageAdderGroovyASTTransformation.class)
public @interface Messenger {
    boolean shout() default false;
}