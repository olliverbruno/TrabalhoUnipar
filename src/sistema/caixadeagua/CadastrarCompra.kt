package sistema.caixadeagua

import produto.Compra
import repositorio.JPA
import sistema.lerCnpj
import sistema.lerDecimal
import sistema.lerInteiro
import java.sql.SQLException
import java.time.LocalDate

//compra de um fornecedor - aumenta o estoque da caixa escolhida
fun cadastrarCompra(){
    val cnpjFornecedor = lerCnpj("Digite o CNPJ do fornecedor já cadastrado: ")

    mostrarCaixasDisponiveis()
    val caixaId = lerInteiro("Digite o ID da caixa d'água comprada: ")

    var quantidade = lerInteiro("Quantas unidades foram compradas? ")
    while (quantidade <= 0) {
        println("Quantidade tem que ser maior que zero.")
        quantidade = lerInteiro("Quantas unidades foram compradas? ")
    }

    val preco = lerDecimal("Digite o preço total da compra: ")

    JPA().salvar(
        Compra(
            fornecedorCpf = cnpjFornecedor,
            caixaDaAguaId = caixaId,
            quantidade = quantidade,
            preco = preco,
            dataCompra = LocalDate.now()
        )
    )
    println("Compra registrada! Estoque atualizado.")
}

//lista id, marca, modelo e estoque atual - pra ajudar a escolher o ID certo
fun mostrarCaixasDisponiveis(){
    val jpa = JPA()
    try {
        jpa.conectar()
        val sql = "SELECT id, marca, modelo, quantidade FROM caixa_da_agua"
        val stnt = jpa.c!!.prepareStatement(sql)
        val resultado = stnt.executeQuery()

        while (resultado.next()) {
            println("ID ${resultado.getInt("id")} - ${resultado.getString("marca")} ${resultado.getString("modelo")} - Estoque: ${resultado.getInt("quantidade")}")
        }

        resultado.close()
        stnt.close()
        jpa.c!!.close()
    } catch (e: SQLException) {
        println("Não foi possível buscar: ${e.printStackTrace()}")
    }
}
