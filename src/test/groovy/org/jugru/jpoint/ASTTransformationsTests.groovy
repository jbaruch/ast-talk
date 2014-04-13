package org.jugru.jpoint

import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.junit.Test

/**
 *
 * @author jbaruch
 * @since 13/04/2014
 */
@SuppressWarnings("GroovyAccessibility")
class ASTTransformationsTests {

    final assertScript = new GroovyTestCase().&assertScript

    @Test
    void '$AUTHOR field should exist'(){
        assertScript '''
             class Foo {}
             assert Foo.$AUTHOR == 'jbaruch'
        '''

    }

    @Test
    void 'message method with shout param should exist'(){
        assertScript '''
        import org.jugru.jpoint.Messenger

        def out = new ByteArrayOutputStream()
        System.out = new PrintStream(out)
        def content = new StringWriter()
        binding.out = new PrintWriter(content)

        @Messenger(shout = false)
        class QuietFoo { }

        new QuietFoo().message('Hello, world!')
        assert out.toString() == 'Hello, world!\\r\\n'
        out.reset()

        @Messenger(shout = true)
        class LoudFoo { }

        new LoudFoo().message('Hello, world!')
        assert out.toString() == 'HELLO, WORLD!\\r\\n'
        '''
    }

    @Test
    void 'main method should exist'(){
        assertScript '''
            import org.jugru.jpoint.Main

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
            assert out.toString() == 'Hello, world!\\r\\n'
            '''
    }

    @Test(expected = MultipleCompilationErrorsException)
    void 'compilation should fail if main already exists'(){
        Eval.me '''
        import org.jugru.jpoint.Main

        class Foo {
            public static void main(String[] args) {}

            @Main
            def greet() {
                println 'Hello, world!\'
            }
        }
        '''

    }
}
