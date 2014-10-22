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
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@GroovyASTTransformationClass(classes = MainAdderAstTransformation.class)
public @interface Main {

}
