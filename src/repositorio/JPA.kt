package repositorio

import pessoas.Cliente
import pessoas.Instalador
import produto.CaixaDaAgua
import produto.Servico
import java.sql.Connection
import java.sql.Date
import java.sql.DriverManager
import java.sql.SQLException


//cada salvar() abre e fecha sua propria conexao - nao tem transacao nem lock,
//entao se a conexao cair no meio ou 2 pessoas salvarem ao mesmo tempo, pode dar inconsistencia
class JPA(
    //porta : 5432
    //user : postgres
    //banco : caixaDaAgua
    //Senha : postgres
    val user: String = "postgres",
    val senha: String = "masterkey",
    val url: String = "jdbc:postgresql://localhost:5432/postgres",
    var c: Connection? = null //nullable pq no começo (antes de conectar) ainda nao existe conexao nenhuma
) {
    fun conectar() {

        try {
            //carregar o driver - se trocasse pra mysql, mudava só isso + a url + o jar do driver
            Class.forName("org.postgresql.Driver")

            //Estabelecer Conexão
            c = DriverManager.getConnection(url, user, senha)
            println("A conexão foi estabelecida!")

        } catch (e: SQLException) {
            println("Cara não deu boa: ${e.printStackTrace()}")
        }
    }

    fun salvar(a: CaixaDaAgua) {
        println("Salvando")
        try {
            conectar()//abre concexao com banco
            val sql = "INSERT INTO CAIXA_DA_AGUA (marca, modelo, dimensao, cor, material, formato, preco, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

            val stnt = c!!.prepareStatement(sql)


            //preparar lista para double precision
            val doublePrecision = c!!.createArrayOf("float8", a.dimensao.toTypedArray()) //array é coisa só do postgres, mysql nao tem isso

            //preparar as variáveis para o banco
            stnt.setString(1, a.marca)
            stnt.setString(2, a.modelo)
            stnt.setArray(3, doublePrecision)
            stnt.setString(4, a.cor.name)
            stnt.setString(5, a.material.name)
            stnt.setString(6, a.formato)
            stnt.setBigDecimal(7, a.preco)
            stnt.setInt(8, a.quantidade) //estoque

            stnt.executeUpdate()
            stnt.close()//encerra o

            c!!.close()//encerra concexao com banco
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

    //salvar cliente, mesmo esquema do de cima (sobrecarga de metodo)
    fun salvar(cliente: Cliente) {
        try {
            conectar()
            val sql = "INSERT INTO cliente (cpf, nome, idade, dividas_abertas) VALUES (?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)

            stnt.setString(1, cliente.cpf)
            stnt.setString(2, cliente.nome)
            stnt.setInt(3, cliente.idade)
            stnt.setBoolean(4, cliente.dividasAbertas)

            stnt.executeUpdate()
            stnt.close()
            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

    //habilidade = setor do funcionario
    fun salvar(funcionario: Instalador) {
        try {
            conectar()
            val sql = "INSERT INTO funcionario (cpf, nome, idade, salario, turno, habilidade) VALUES (?, ?, ?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)

            stnt.setString(1, funcionario.cpf)
            stnt.setString(2, funcionario.nome)
            stnt.setInt(3, funcionario.idade)
            stnt.setBigDecimal(4, funcionario.salario)
            stnt.setString(5, funcionario.turno.name)
            stnt.setString(6, funcionario.habilidade.name)

            stnt.executeUpdate()
            stnt.close()
            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

    //cliente e funcionario tem que existir antes (chave estrangeira)
    fun salvar(servico: Servico) {
        try {
            conectar()
            val sql = "INSERT INTO servico (cliente_cpf, instalador_cpf, preco, data_instalacao, tipo) VALUES (?, ?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)

            stnt.setString(1, servico.cliente.cpf)
            stnt.setString(2, servico.instalador.cpf)
            stnt.setBigDecimal(3, servico.preco)
            stnt.setDate(4, Date.valueOf(servico.dataInstalacao))
            stnt.setString(5, servico.tipo.name)

            stnt.executeUpdate()
            stnt.close()
            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

}