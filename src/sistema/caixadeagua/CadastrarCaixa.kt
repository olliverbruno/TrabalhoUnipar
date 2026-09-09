package sistema.caixadeagua

import enumeradores.Cor
import enumeradores.Material
import produto.CaixaDaAgua
import repositorio.JPA
import sistema.lerDecimal //lerDecimal/lerInteiro leem número validado, sem quebrar se digitar letra
import sistema.lerInteiro

fun cadastrarNovaCaixa(){
    println ("Digite a marca: ") //nao validei isso, se apertar enter sem digitar vai vazio mesmo pro banco
    val marca = readln()

    println ("Digite a modelo: ")
    val modelo = readln()

    val largura = lerDecimal("Digite a largura: ").toDouble()

    val altura = lerDecimal("Digite a altura: ").toDouble()

    val profundidade = lerDecimal("Digite a profundidade: ").toDouble()

    val dimensao = mutableListOf<Double>(largura,altura,profundidade)

    println ("Escolha a cor: ")

    Cor.entries.forEach{ cor ->
        println("${cor.ordinal} - ${cor.name}")
    }
    var cor: Cor? = null
    while (cor == null) {
        try {
            cor = Cor.entries[lerInteiro("Número da cor: ")]
        } catch (e: IndexOutOfBoundsException) {
            println("Número inválido, tente novamente.")
        }
    }

    println ("Escolha o material: ")
    Material.entries.forEach{ material ->
        println("${material.ordinal} - ${material.name}")
    }
    var material: Material? = null
    while (material == null) {
        try {
            material = Material.entries[lerInteiro("Digite o numero do material: ")]
        } catch (e: IndexOutOfBoundsException) {
            println("Número inválido, tente novamente.")
        }
    }

    println ("Digite a formato: ")
    val formato = readln()

    val preco = lerDecimal("Digite a preço: ")

    val quantidade = lerInteiro("Digite a quantidade em estoque: ") //quantidade = estoque, requisito do PDF

    val conexao: JPA = JPA()
    conexao.salvar(
        CaixaDaAgua(
            marca = marca,
            modelo =  modelo,
            dimensao = dimensao,
            cor = cor,
            material = material,
            formato = formato,
            preco = preco,
            quantidade = quantidade
        )
    )
}