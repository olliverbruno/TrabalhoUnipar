package pessoas

class Auditor(
    nome: String,
    cpf: String,
    idade: Int,
    val registroProfissional: String
) : Pessoa(nome, cpf, idade)
