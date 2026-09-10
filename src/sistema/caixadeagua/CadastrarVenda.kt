package sistema.caixadeagua

import produto.Venda
import repositorio.JPA
import sistema.lerCpf
import sistema.lerDecimal
import sistema.lerInteiro
import java.time.LocalDate

//venda pra um cliente - diminui o estoque (o JPA confere se tem quantidade suficiente)
fun cadastrarVenda(){
    val cpfCliente = lerCpf("Digite o CPF do cliente já cadastrado: ")

    mostrarCaixasDisponiveis()
    val caixaId = lerInteiro("Digite o ID da caixa d'água vendida: ")

    val quantidade = lerInteiro("Quantas unidades foram vendidas? ")

    val preco = lerDecimal("Digite o preço total da venda: ")

    JPA().salvar(
        Venda(
            clienteCpf = cpfCliente,
            caixaDaAguaId = caixaId,
            quantidade = quantidade,
            preco = preco,
            dataVenda = LocalDate.now()
        )
    )
}
