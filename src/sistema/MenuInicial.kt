package sistema

import sistema.caixadeagua.cadastrarNovaCaixa
import sistema.caixadeagua.listarCaixa
import sistema.pessoas.cadastrarCliente
import sistema.pessoas.cadastrarFuncionario
import sistema.pagamentos.pagar
import sistema.servico.cadastrarServico

fun menuInicial() {

    do {
        println("0 - SAIR")
        println("1 - CADASTRAR CAIXA DE AGUA")
        println("2 - BUSCAR CAIXA DE AGUA")
        //daqui pra baixo é tudo novo, nao existia antes
        println("3 - CADASTRAR CLIENTE")
        println("4 - CADASTRAR FUNCIONARIO")
        println("5 - CADASTRAR SERVICO")
        println("6 - REGISTRAR MOVIMENTACAO")
        val op = readln()



        //de for um digito será verdadeiro
            when (op) {
                "0" -> {
                    println("Adios friend")
                    break
                }

                "1" -> cadastrarNovaCaixa()
                "2" -> listarCaixa()
                "3" -> cadastrarCliente()
                "4" -> cadastrarFuncionario()
                "5" -> cadastrarServico()
                "6" -> pagar()
                else -> println("OPÇÃO INVÁLIDA")
            }

    } while (true) //fim do doWhile
}//fim da função