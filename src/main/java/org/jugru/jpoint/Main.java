package org.jugru.jpoint;

import org.codehaus.groovy.transform.GroovyASTTransformationClass;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author jbaruch
 * @since 13/04/2014
 */
@Retention(SOURCE)
@Target(METHOD)
@GroovyASTTransformationClass(classes = MainAdderASTTransformation.class)
public @interface Main {
}
