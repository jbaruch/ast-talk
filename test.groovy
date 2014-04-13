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
assert out.toString() == 'Hello, world!'
