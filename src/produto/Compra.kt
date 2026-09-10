package produto

import java.math.BigDecimal
import java.time.LocalDate

//compra de caixas d'agua de um fornecedor - aumenta o estoque
class Compra(
    val fornecedorCpf: String,
    val caixaDaAguaId: Int,
    val quantidade: Int,
    val preco: BigDecimal,
    val dataCompra: LocalDate
)
