package pessoas

import java.math.BigDecimal

//classe mae - cliente e instalador herdam daqui, por isso é open (sem open ninguem herda)
open class Pessoa (
    val nome: String,
    val cpf: String,
    val idade: Int
)//Criação da classe-mãe via Construtor
{
    open fun receberConta(dinheiro : BigDecimal) : BigDecimal{
        return dinheiro //aqui é o polimorfismo - instalador sobrescreve isso embaixo
    }
}