package org.springone2gx.ast

import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.junit.Test

/**
 *
 * @author jbaruch
 * @since 13/04/2014
 */
@SuppressWarnings("GroovyAccessibility")
class AstTransformationsTests {

    final assertScript = new GroovyTestCase().&assertScript

    @Test
    void 'every class should have AUTHOR field'() {
        assertScript '''
                     class Foo {}
                     assert Foo.$AUTHOR == 'jbaruch'
                '''
    }

    @Test
    void 'Messenger annotation should generate a message method'() {
        assertScript '''
                import org.springone2gx.ast.Messenger

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
    void 'main annotation should generate a main method'() {
        assertScript '''
                    import org.springone2gx.ast.Main

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
                    '''
    }

    @Test(expected = MultipleCompilationErrorsException)
    void 'main method adding should fail if psvm already exists'() {
        Eval.me '''
    import org.springone2gx.ast.Main

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
    void 'using safe method should prevent NPE'(){
        assertScript '''
                    def nullObject = null;
                    assert null == safe(nullObject.hashcode())
                '''
    }
}