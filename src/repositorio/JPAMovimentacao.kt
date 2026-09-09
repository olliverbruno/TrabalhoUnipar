package repositorio

import financeiro.Movimentacao
import java.sql.SQLException
import java.sql.Timestamp

//salva todos os campos que o pdf pede
fun salvarMovimentacao(movimentacao: Movimentacao) {
    val jpa = JPA()

    try {
        jpa.conectar()//abre concexao com banco
        val sql = "INSERT INTO movimentacao (valor, data_movimentacao, descricao, pagador, recebedor, responsavel) VALUES (?, ?, ?, ?, ?, ?)"

        val stnt = jpa.c!!.prepareStatement(sql)

        //preparar as variáveis para o banco
        stnt.setBigDecimal(1, movimentacao.valor)
        stnt.setTimestamp(2, Timestamp.valueOf(movimentacao.contaMovimentacao)) //Timestamp = data e hora
        stnt.setString(3, movimentacao.descricao)
        stnt.setString(4, movimentacao.pagador)
        stnt.setString(5, movimentacao.recebedor)
        stnt.setString(6, movimentacao.responsavel)

        stnt.executeUpdate()
        stnt.close()//encerra o

        jpa.c!!.close()//encerra concexao com banco
    } catch (e: SQLException) {
        println("Não  salvou: ${e.printStackTrace()}")
    }
}
