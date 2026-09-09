package sistema.servico

import enumeradores.Habilidade
import enumeradores.TipoServico
import enumeradores.Turno
import pessoas.Cliente
import pessoas.Instalador
import produto.Servico
import repositorio.JPA
import sistema.lerCpf
import sistema.lerDecimal
import sistema.lerInteiro
import java.math.BigDecimal
import java.time.LocalDate

//cliente e funcionario tem que existir antes (fk)
fun cadastrarServico(){
    val cpfCliente = lerCpf("Digite o CPF do cliente já cadastrado: ")
    val cpfInstalador = lerCpf("Digite o CPF do funcionário já cadastrado: ")

    val preco = lerDecimal("Digite o preço do serviço: ")

    println("Escolha o tipo de serviço: ")
    TipoServico.entries.forEach { println("${it.ordinal} - ${it.name}") }
    val tipo = TipoServico.entries[lerInteiro("Número do tipo: ")]

    //só precisa do cpf pra salvar, resto fica vazio mesmo
    //isso aqui eu nao gosto muito - criar cliente/instalador vazio só pra carregar o cpf. daria pra ter uma busca no banco pelo cpf em vez disso
    //cadastrar servico e registrar movimentacao (menu 6) sao 2 opções separadas - nao é automatico, tem que fazer os 2
    val servico = Servico()
    servico.cliente = Cliente(
        nomeCliente = "",
        cpfCliente = cpfCliente,
        idadeCliente = 0,
        dividasAbertas = false,
        parcelasAPagar = mutableListOf()
    )
    servico.instalador = Instalador(
        nome = "",
        cpf = cpfInstalador,
        idade = 0,
        salario = BigDecimal.ZERO,
        turno = Turno.NOTURNO,
        habilidade = Habilidade.INSTALACAO
    )
    servico.preco = preco
    servico.dataInstalacao = LocalDate.now()
    servico.tipo = tipo

    JPA().salvar(servico)
}
