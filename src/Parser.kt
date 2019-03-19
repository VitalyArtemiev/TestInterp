class Parser (val TokenList: Array<Token>) {
    var currentToken: Token = TokenList.first()

    fun match() {

    }

    fun lang() {
        while (true) {
            expr()
        }
    }

    fun expr() {
        var()
        assign_op()
        val_stmt()
    }

    fun var() {
        match()
        if currentToken.getLexer() {

        }
    }
}

class AbstractSyntaxTree {

    val root: ASTNode;


}

data class ASTNode(val Name: String, val Line: Int) {

}