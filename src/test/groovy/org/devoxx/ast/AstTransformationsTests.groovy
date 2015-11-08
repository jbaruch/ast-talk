package org.devoxx.ast

import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.junit.Test

import static groovy.test.GroovyAssert.assertScript

/**
 * @author jbaruch
 * @since 9/28/15
 */
class AstTransformationsTests {

    @Test
    void 'every class should have $AUTHOR field'(){
        assertScript '''
                    class Foo{}
                    assert Foo.$AUTHOR == 'jbaruch'
                 '''
    }

    @Test
    void 'class with @Messenger should have message method'(){
        assertScript '''
                import org.devoxx.ast.Messenger

                def out = new ByteArrayOutputStream()
                System.out = new PrintStream(out)
                def content = new StringWriter()
                binding.out = new PrintWriter(content)

                @Messenger(shout = false)
                class QuietFoo {}

                new QuietFoo().message('hello world!')
                assert out.toString() == 'hello world!\\n'
                out.reset()

                @Messenger(shout = true)
                class LoudFoo {}

                new LoudFoo().message('hello world!')
                assert out.toString() == 'HELLO WORLD!\\n'
                '''
    }

    @Test
    void 'class with @Main should have psvm' (){
        assertScript '''
                import org.devoxx.ast.Main

                def out = new ByteArrayOutputStream()
                System.out = new PrintStream(out)
                def content = new StringWriter()
                binding.out = new PrintWriter(content)

                class Foo {

                    @Main
                    def greet(){
                        println 'hello world'
                    }
                }

                Foo.main(new String[0])
                assert out.toString() == 'hello world\\n'
                '''
    }

    @Test(expected = MultipleCompilationErrorsException)
    void 'class shouldn\'t have two mains'(){
        assertScript '''

                import org.devoxx.ast.Main

                class Foo {
                    public static void main(String[] args){
                      assert false, 'Wrong main!'
                    }

                    @Main
                    def greet() {
                        println 'hello world'
                    }
                }
                '''
    }
}
