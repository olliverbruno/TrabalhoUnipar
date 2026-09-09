package financeiro

import java.math.BigDecimal
import java.time.LocalDateTime

//o que o pdf pede pra salvar: valor, pagador, recebedor, data/hora, motivo, responsavel
class Movimentacao (
    val valor: BigDecimal,
    val contaMovimentacao : LocalDateTime, //LocalDateTime pra pegar a hora tb
    val descricao: String,
    val pagador: String,
    val recebedor: String,
    val responsavel: String
)
