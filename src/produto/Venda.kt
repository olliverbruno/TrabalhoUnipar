package produto

import java.math.BigDecimal
import java.time.LocalDate

//venda de uma caixa d'agua pra um cliente - diminui o estoque
class Venda(
    val clienteCpf: String,
    val caixaDaAguaId: Int,
    val quantidade: Int,
    val preco: BigDecimal,
    val dataVenda: LocalDate
)
