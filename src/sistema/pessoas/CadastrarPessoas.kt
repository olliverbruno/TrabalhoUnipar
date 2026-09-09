package sistema.pessoas

import enumeradores.Habilidade
import enumeradores.Turno
import pessoas.Cliente
import pessoas.Instalador
import repositorio.JPA
import sistema.lerCpf
import sistema.lerDecimal
import sistema.lerInteiro

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

    val salario = lerDecimal("Digite o salário: ") //só confere se é numero, nao confere se é negativo

    println("Escolha o turno: ")
    Turno.entries.forEach { println("${it.ordinal} - ${it.name}") }
    val turno = Turno.entries[lerInteiro("Número do turno: ")] //aqui ainda quebra se digitar numero fora do intervalo (só protegi cor/material)

    //aqui entra a divisao em setor que o pdf pede
    println("Escolha o setor: ")
    Habilidade.entries.forEach { println("${it.ordinal} - ${it.name}") }
    val habilidade = Habilidade.entries[lerInteiro("Número do setor: ")]

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
