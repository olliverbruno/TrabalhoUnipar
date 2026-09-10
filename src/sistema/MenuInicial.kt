package sistema

import sistema.caixadeagua.cadastrarCompra
import sistema.caixadeagua.cadastrarNovaCaixa
import sistema.caixadeagua.cadastrarVenda
import sistema.caixadeagua.listarCaixa
import sistema.pessoas.cadastrarAuditor
import sistema.pessoas.cadastrarCliente
import sistema.pessoas.cadastrarFornecedor
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
        println("7 - CADASTRAR FORNECEDOR")
        println("8 - CADASTRAR AUDITOR")
        println("9 - REGISTRAR COMPRA (aumenta estoque)")
        println("10 - REGISTRAR VENDA (diminui estoque)")
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
                "7" -> cadastrarFornecedor()
                "8" -> cadastrarAuditor()
                "9" -> cadastrarCompra()
                "10" -> cadastrarVenda()
                else -> println("OPÇÃO INVÁLIDA")
            }

    } while (true) //fim do doWhile
}//fim da função