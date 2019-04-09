import java.io.File

fun main() {
    var path = "sourceExample"

    val l = Lexer(path)


    var tokens = l.lex()

    val p = Parser(tokens)
    p.lang()
}

class Lexer(var path: String) {

    fun lex(): ArrayList<Token> {
        var result = ArrayList<Token>()

        for (line in readFile(path)) {
            result.addAll(getTokens(line))
        }

        return result
    }

    fun readFile(path: String): List <String> {
        var f = File(path)
        return f.readLines()
    }

    fun getTokens(s: String): Array<Token> {

    }
}

public enum class tokenType {startBlock, endBlock, value, variable}

public enum class terminals (var s: String) {startBlock("{")}

public data class Token (val line: Int, var text: String, var tokenized: Boolean, var token: tokenType)