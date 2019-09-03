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

        for ((i: Int, line: String) in readF(path).withIndex()) {
            result.addAll(getTokens(line, i))
        }

        return result
    }

    fun readF(path: String): List <String> {
        var f = File(path)
        return f.readLines()
    }

    fun getTokens(line: String, lineIndex: Int): Array<Token> {
        var back: Int = 0
        var front: Int = 1

        val tokens =  ArrayList<Token>()


        var s = line//.substring(back, front)

        var lastChar = ' '

        val it = s.toList().listIterator()
        var identifierStr: String

        while (true) {
            // Skip any whitespace.
            while (lastChar.isWhitespace())
                lastChar = it.next()

            var tokenType = when (lastChar) {
                '{' -> tokenTypeEnum.startBlock
                '}' -> tokenTypeEnum.endBlock
                '(' -> tokenTypeEnum.openParenthesis
                ')' -> tokenTypeEnum.closeParenthesis
                else -> tokenTypeEnum.TBD
            }

            if (tokenType != tokenTypeEnum.TBD) {
                var t = Token(lineIndex, lastChar.toString(), tokenType)

                tokens.add(t)
                continue
            }

            if (lastChar.isLetter()) { // identifier: [a-zA-Z][a-zA-Z0-9]*
                identifierStr = lastChar.toString()

                lastChar = it.next()
                while (lastChar.isLetterOrDigit()) {
                    identifierStr += lastChar;
                    lastChar = it.next()
                }

                var tokenType = when (identifierStr) {
                    "var" -> tokenTypeEnum.variableDeclaration
                    "if"  -> tokenTypeEnum.ifStmt
                    "while" -> tokenTypeEnum.whileStmt
                    "for" -> tokenTypeEnum.forStmt
                    "printVarTable" -> tokenTypeEnum.printVarTable
                    else -> tokenTypeEnum.variableName
                }

                var t = Token(lineIndex, identifierStr, tokenType)

                tokens.add(t)
                continue
            }

            if (lastChar.isDigit() || lastChar == '.') { // Number: [0-9.]+
                var NumStr: String = ""
                do {
                    NumStr += lastChar;
                    lastChar = it.next()
                } while (lastChar.isDigit()  || lastChar == '.');

                val NumVal =  NumStr.toDouble()
                var t = Token(lineIndex, NumStr, tokenTypeEnum.value)

                tokens.add(t)
                continue
            }

            if (lastChar == '#') {
                // Comment until end of line.
                while (it.hasNext() && lastChar != '\n' && lastChar != '\r')
                    lastChar = it.next()

                if (it.hasNext())
                    continue
                    //return gettok()
            }

            if (lastChar == ':') {
                if (it.hasNext()) {
                    var t = ":" + it.next()
                    var tokenType = when (t) {
                        ":=" -> tokenTypeEnum.assignOP
                        ":+"
                        ":-"
                        ":*"
                        ":/"

                    }
                }
            }

            if (!it.hasNext())
                break
            // Check for end of file.  Don't eat the EOF.

                //return tokenTypeEnum.EOF;

           /* // Otherwise, just return the character as its ascii value.
            var ThisChar = lastChar;
            lastChar = it.next();
            return ThisChar;
            */

        }

        tryMatch(s)

        var t = Token(lineIndex, s, tokenTypeEnum.TBD)

        tokens.add(t)


        return tokens.toTypedArray()
    }

    fun getToken(s: String): tokenTypeEnum {
        var lastChar = ' '

        val it = s.toList().listIterator()

        var identifierStr: String


    }

    fun tryMatch(s: String): tokenTypeEnum {

        return tokenTypeEnum.TBD
    }
}

public enum class tokenTypeEnum {TBD, startBlock, endBlock, openParenthesis, closeParenthesis, value, variableDeclaration, variableName, ifStmt, whileStmt, forStmt,  assignOP, plusOP, minusOP, printVarTable}

public enum class terminals (var s: String) {startBlock("{")}

public data class Token (val line: Int, var text: String, var tokenType: tokenTypeEnum = tokenTypeEnum.TBD){
    val tokenized: Boolean
        get() = tokenType == tokenTypeEnum.TBD
}