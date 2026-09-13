package sistema.servico

import enumeradores.Setor
import enumeradores.TipoServico
import enumeradores.Turno
import pessoas.Cliente
import pessoas.Funcionario
import produto.Servico
import repositorio.JPA
import sistema.lerCpf
import sistema.lerDecimal
import sistema.lerInteiro
import java.math.BigDecimal
import java.time.LocalDate

fun cadastrarServico(){
    val cpfCliente = lerCpf("Digite o CPF do cliente já cadastrado: ")
    val cpfInstalador = lerCpf("Digite o CPF do funcionário já cadastrado: ")

    val preco = lerDecimal("Digite o preço do serviço: ")

    println("Escolha o tipo de serviço: ")
    TipoServico.entries.forEach { println("${it.ordinal} - ${it.name}") }
    var tipo: TipoServico? = null
    while (tipo == null) {
        try {
            tipo = TipoServico.entries[lerInteiro("Número do tipo: ")]
        } catch (e: IndexOutOfBoundsException) {
            println("Número inválido, tente novamente.")
        }
    }


    val servico = Servico()
    servico.cliente = Cliente(
        nomeCliente = "",
        cpfCliente = cpfCliente,
        idadeCliente = 0,
        dividasAbertas = false,
        parcelasAPagar = mutableListOf()
    )
    servico.funcionario = Funcionario(
        nome = "",
        cpf = cpfInstalador,
        idade = 0,
        salario = BigDecimal.ZERO,
        turno = Turno.NOTURNO,
        setor = Setor.INSTALACAO
    )
    servico.preco = preco
    servico.dataInstalacao = LocalDate.now()
    servico.tipo = tipo

    JPA().salvar(servico)
}
