package sistema.pagamentos

import financeiro.Movimentacao
import financeiro.despesa
import financeiro.receita
import repositorio.salvarMovimentacao
import sistema.lerDecimal
import java.time.LocalDateTime

fun pagar(){
    println("Digite a descrição: ")
    val contexto = readln()

    val valorDigitado = lerDecimal("Digite o valor: ")

    //pergunta se entra ou sai dinheiro do caixa
    println("É uma receita (1) ou uma despesa (2)? ")
    val tipoMovimentacao = readln()
    val valor = if (tipoMovimentacao == "2") despesa(valorDigitado) else receita(valorDigitado) //despesa deixa negativo, receita deixa positivo

    println("Digite quem pagou: ")
    val pagador = readln()

    println("Digite quem recebeu: ")
    val recebedor = readln()

    println("Digite o responsável pela transação: ")
    val responsavel = readln()

    salvarMovimentacao(
        Movimentacao(
            valor = valor,
            contaMovimentacao = LocalDateTime.now(),
            descricao = contexto,
            pagador = pagador,
            recebedor = recebedor,
            responsavel = responsavel
        )
    )
}
