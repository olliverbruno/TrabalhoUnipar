package pessoas

class Fornecedor(
    nome: String,
    cpf: String,
    idade: Int,
    val produtoFornecido: String
) : Pessoa(nome, cpf, idade)
