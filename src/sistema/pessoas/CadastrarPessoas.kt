package sistema.pessoas

import enumeradores.Setor
import enumeradores.Turno
import pessoas.Auditor
import pessoas.Cliente
import pessoas.Fornecedor
import pessoas.Funcionario
import repositorio.JPA
import sistema.lerCnpj
import sistema.lerCpf
import sistema.lerDecimal
import sistema.lerInteiro
import java.math.BigDecimal

fun cadastrarCliente(){
    val cpf = lerCpf("Digite o CPF do cliente (11 números): ")

    println("Digite o nome: ")
    val nome = readln()

    val idade = lerInteiro("Digite a idade: ")

    var dividas : String
    do {
        print("Possui dividas em aberto? (S/N): ")
        dividas = readln().trim().lowercase()
    } while (dividas != "s" && dividas != "n")

    val possuiDividas = (dividas == "s")

    JPA().salvar(
        Cliente(
            nomeCliente = nome,
            cpfCliente = cpf,
            idadeCliente = idade,
            dividasAbertas = possuiDividas, // Passando o Boolean correto aqui
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

    println("Escolha o setor: ")
    Setor.entries.forEach { println("${it.ordinal} - ${it.name}") }
    var setor: Setor? = null
    while (setor == null) {
        try {
            setor = Setor.entries[lerInteiro("Número do setor: ")]
        } catch (e: IndexOutOfBoundsException) {
            println("Número inválido, tente novamente.")
        }
    }

    JPA().salvar(
        Funcionario(
            nome = nome,
            cpf = cpf,
            idade = idade,
            salario = salario,
            turno = turno,
            setor = setor
        )
    )
}

fun cadastrarFornecedor(){
    val cnpj = lerCnpj("Digite o CNPJ do fornecedor (14 números): ")

    var nome: String
    do{
        print("Digite o nome: ")
        nome = readln().toString()
    } while (nome.isEmpty())

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
