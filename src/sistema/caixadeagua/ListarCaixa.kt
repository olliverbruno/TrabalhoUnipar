package sistema.caixadeagua

import repositorio.JPA
import java.sql.SQLException
import java.text.NumberFormat
import java.util.Locale

//antes essa funcao tava vazia, agora busca de verdade
fun listarCaixa(){
    val formatador = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"))
    val jpa = JPA()

    try {
        jpa.conectar()
        val sql = "SELECT marca, modelo, cor, material, formato, preco, quantidade FROM caixa_da_agua"
        val stnt = jpa.c!!.prepareStatement(sql)
        val resultado = stnt.executeQuery()

        while (resultado.next()) { //percorre linha por linha
            println(
                "${resultado.getString("marca")} ${resultado.getString("modelo")} - " +
                "${resultado.getString("cor")} - ${resultado.getString("material")} - " +
                "${resultado.getString("formato")} - ${formatador.format(resultado.getBigDecimal("preco"))} - " +
                "Estoque: ${resultado.getInt("quantidade")}"
            )
        }

        resultado.close()
        stnt.close()
        jpa.c!!.close()
    } catch (e: SQLException) {
        println("Não foi possível buscar: ${e.printStackTrace()}")
    }
}
