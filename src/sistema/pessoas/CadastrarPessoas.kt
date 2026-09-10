package sistema.pessoas

import enumeradores.Habilidade
import enumeradores.Turno
import pessoas.Auditor
import pessoas.Cliente
import pessoas.Fornecedor
import pessoas.Instalador
import repositorio.JPA
import sistema.lerCnpj
import sistema.lerCpf
import sistema.lerDecimal
import sistema.lerInteiro
import java.math.BigDecimal

//nao tem opção de editar funcionario/cliente no menu - se mudar de setor, teria que mexer direto no banco
fun cadastrarCliente(){
    val cpf = lerCpf("Digite o CPF do cliente (11 números): ")

    println("Digite o nome: ")
    val nome = readln()

    val idade = lerInteiro("Digite a idade: ")

    println("Possui dívidas em aberto? (S/N): ")
    val dividasAbertas = readln().equals("S", ignoreCase = true)

    JPA().salvar(
        Cliente(
            nomeCliente = nome,
            cpfCliente = cpf,
            idadeCliente = idade,
            dividasAbertas = dividasAbertas,
            parcelasAPagar = mutableListOf()
        )
    )
}

//usa a classe Instalador que ja existia
fun cadastrarFuncionario(){
    val cpf = lerCpf("Digite o CPF do funcionário (11 números): ")

    println("Digite o nome: ")
    val nome = readln()

    val idade = lerInteiro("Digite a idade: ")

    var salario = lerDecimal("Digite o salário: ")
    while (salario < BigDecimal.ZERO) {
        println("Salário não pode ser negativo.")
        salario = lerDecimal("Digite o salário: ")
    }

    println("Escolha o turno: ")
    Turno.entries.forEach { println("${it.ordinal} - ${it.name}") }
    var turno: Turno? = null
    while (turno == null) {
        try {
            turno = Turno.entries[lerInteiro("Número do turno: ")]
        } catch (e: IndexOutOfBoundsException) {
            println("Número inválido, tente novamente.")
        }
    }

    //aqui entra a divisao em setor que o pdf pede
    println("Escolha o setor: ")
    Habilidade.entries.forEach { println("${it.ordinal} - ${it.name}") }
    var habilidade: Habilidade? = null
    while (habilidade == null) {
        try {
            habilidade = Habilidade.entries[lerInteiro("Número do setor: ")]
        } catch (e: IndexOutOfBoundsException) {
            println("Número inválido, tente novamente.")
        }
    }

    JPA().salvar(
        Instalador(
            nome = nome,
            cpf = cpf,
            idade = idade,
            salario = salario,
            turno = turno,
            habilidade = habilidade
        )
    )
}

//fornecedor usa cnpj (14 numeros) em vez de cpf, por isso o lerCnpj em vez de lerCpf
fun cadastrarFornecedor(){
    val cnpj = lerCnpj("Digite o CNPJ do fornecedor (14 números): ")

    println("Digite o nome: ")
    val nome = readln()

    val idade = lerInteiro("Digite a idade: ")

    println("Digite o produto fornecido: ")
    val produtoFornecido = readln()

    JPA().salvar(
        Fornecedor(
            nome = nome,
            cpf = cnpj,
            idade = idade,
            produtoFornecido = produtoFornecido
        )
    )
}

fun cadastrarAuditor(){
    val cpf = lerCpf("Digite o CPF do auditor (11 números): ")

    println("Digite o nome: ")
    val nome = readln()

    val idade = lerInteiro("Digite a idade: ")

    println("Digite o registro profissional: ")
    val registroProfissional = readln()

    JPA().salvar(
        Auditor(
            nome = nome,
            cpf = cpf,
            idade = idade,
            registroProfissional = registroProfissional
        )
    )
}
