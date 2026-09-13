package pessoas

import enumeradores.Setor
import enumeradores.Turno
import java.math.BigDecimal

class Funcionario (
    nome : String,
    cpf : String,
    idade : Int,
    val salario : BigDecimal,
    val turno : Turno,
    val setor : Setor
) : Pessoa(
    nome, cpf, idade
) {
    override fun receberConta(dinheiro : BigDecimal) : BigDecimal{
        return -dinheiro //cliente paga empresa = entra dinheiro (+), empresa paga funcionario = sai (-)
    }//Uma função sobrescrita somente altera seu escopo
}