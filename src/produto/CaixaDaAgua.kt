package produto

import enumeradores.Cor
import enumeradores.Material
import java.math.BigDecimal

class CaixaDaAgua(
    /**
     * Marca, Modelo, Dimensão(altura, largura, profundidade), Cor, Material, Formato, Preço, Quantidade
     * */
    val marca : String,
    val modelo : String,
    val dimensao : MutableList<Double>,
    val cor : Cor,
    val material : Material,
    val formato : String,
    val preco : BigDecimal,
    val quantidade : Int = 1 //estoque
) {

}