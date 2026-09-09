package sistema

import java.math.BigDecimal


fun validarCpf(cpf: String): Boolean = Regex("^\\d{11}$").matches(cpf) //só aceita 11 números, sem ponto nem traço

fun lerCpf(mensagem: String): String {
    println(mensagem)
    var cpf = readln()
    while (!validarCpf(cpf)) {
        println("CPF inválido, digite 11 números sem pontos ou traços.")
        cpf = readln()
    }
    return cpf
}

fun lerInteiro(mensagem: String): Int {
    println(mensagem)
    var valor = readln().toIntOrNull() //devolve null se nao for numero
    while (valor == null) {
        println("Valor inválido, digite um número inteiro.")
        valor = readln().toIntOrNull()
    }
    return valor
}

fun lerDecimal(mensagem: String): BigDecimal {
    println(mensagem)
    var valor = readln().toBigDecimalOrNull()
    while (valor == null) {
        println("Valor inválido, digite um número (ex: 10.50).")
        valor = readln().toBigDecimalOrNull()
    }
    return valor
}
