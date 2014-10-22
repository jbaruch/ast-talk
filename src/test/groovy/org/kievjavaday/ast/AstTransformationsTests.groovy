package org.kievjavaday.ast

import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.junit.Test

/**
 *
 * @author jbaruch
 * @since 13/04/2014
 */
@SuppressWarnings("GroovyAccessibility")
class AstTransformationsTests {

    final Closure assertScript = new GroovyTestCase().&assertScript

    @Test
    void 'every class should have $AUTHOR field'(){
        assertScript '''
        class Foo {}
        new Foo().$AUTHOR == 'jbaruch'
'''
    }

    @Test
    void 'classes with @Messsage annotation should have message() method'(){
        assertScript '''
                import org.kievjavaday.ast.Messenger

                def out = new ByteArrayOutputStream()
                System.out = new PrintStream(out)
                def content = new StringWriter()
                binding.out = new PrintWriter(content)

                @Messenger(shout = false)
                class QuietFoo { }

                new QuietFoo().message('Hello, world!')
                assert out.toString() == 'Hello, world!\\n'
                out.reset()

                @Messenger(shout = true)
                class LoudFoo { }

                new LoudFoo().message('Hello, world!')
                assert out.toString() == 'HELLO, WORLD!\\n'
                '''

    }

    @Test
    void 'method with @Main should be called from generated psvm'(){
        assertScript """
                    import org.kievjavaday.ast.Main

                    def out = new ByteArrayOutputStream()
                    System.out = new PrintStream(out)
                    def content = new StringWriter()
                    binding.out = new PrintWriter(content)

                    class Foo {
                        @Main
                        def greet() {
                            println 'Hello, world!'
                        }
                    }

                    Foo.main(new String[0])
                    assert out.toString() == 'Hello, world!\\n'
                    """

    }

    @Test(expected = MultipleCompilationErrorsException)
    void 'class should not have 2 main methods'(){
        Eval.me '''
            import org.kievjavaday.ast.Main

            class Foo {
                public static void main(String[] args) {
                    assert false, 'Wow, wrong main, dude!'
                }

                @Main
                def greet() {
                    println 'Hello, world!'
                }
            }
            Foo.main(new String[0])
            '''
    }

    @Test
    void 'safe should prevent NPE'(){
        assertScript '''
                    def nullObject = null;
                    assert null == safe(nullObject.hashcode())
                '''
    }
}
