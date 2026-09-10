package sistema.pagamentos

import financeiro.Movimentacao
import financeiro.despesa
import financeiro.receita
import repositorio.JPA
import repositorio.salvarMovimentacao
import sistema.lerDecimal
import java.math.BigDecimal
import java.time.LocalDateTime

fun pagar(){
    println("Digite a descrição: ")
    val contexto = readln()

    val valorDigitado = lerDecimal("Digite o valor: ")

    //pergunta se entra ou sai dinheiro do caixa
    println("É uma receita (1) ou uma despesa (2)? ")
    val tipoMovimentacao = readln()

    if (tipoMovimentacao == "2") {
        val saldoAtual = consultarSaldoAtual()
        if (saldoAtual.subtract(valorDigitado) < BigDecimal.ZERO) {
            println("Saldo insuficiente! Saldo atual: $saldoAtual")
            return
        }
    }

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

//soma todas as movimentacoes (receita + despesa) pra saber o saldo atual do caixa
fun consultarSaldoAtual(): BigDecimal {
    val jpa = JPA()
    jpa.conectar()
    val stnt = jpa.c!!.prepareStatement("SELECT COALESCE(SUM(valor), 0) AS saldo FROM movimentacao")
    val resultado = stnt.executeQuery()
    resultado.next()
    val saldo = resultado.getBigDecimal("saldo")
    resultado.close()
    stnt.close()
    jpa.c!!.close()
    return saldo
}
