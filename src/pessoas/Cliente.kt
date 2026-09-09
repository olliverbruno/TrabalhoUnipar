package pessoas

import java.math.BigDecimal

//so tem esses 2 campos a mais (dividasAbertas, parcelasAPagar), nome/cpf/idade vem herdado de Pessoa
//fornecedor e auditor tinham tabela no banco mas cortei a classe, ficou só cliente e funcionario mesmo
class Cliente(
    nomeCliente: String,
    cpfCliente: String,
    idadeCliente: Int,
    val dividasAbertas: Boolean,
    val parcelasAPagar : MutableList<BigDecimal>
) : Pessoa(
    nome = nomeCliente,
    cpf = cpfCliente,
    idade = idadeCliente){
}