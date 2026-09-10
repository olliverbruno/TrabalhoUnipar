package repositorio

import pessoas.Auditor
import pessoas.Cliente
import pessoas.Fornecedor
import pessoas.Instalador
import produto.CaixaDaAgua
import produto.Compra
import produto.Servico
import produto.Venda
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

    fun salvar(fornecedor: Fornecedor) {
        try {
            conectar()
            val sql = "INSERT INTO fornecedor (cpf, nome, idade, produto_fornecido) VALUES (?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)

            stnt.setString(1, fornecedor.cpf)
            stnt.setString(2, fornecedor.nome)
            stnt.setInt(3, fornecedor.idade)
            stnt.setString(4, fornecedor.produtoFornecido)

            stnt.executeUpdate()
            stnt.close()
            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

    fun salvar(auditor: Auditor) {
        try {
            conectar()
            val sql = "INSERT INTO auditor (cpf, nome, idade, registro_profissional) VALUES (?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)

            stnt.setString(1, auditor.cpf)
            stnt.setString(2, auditor.nome)
            stnt.setInt(3, auditor.idade)
            stnt.setString(4, auditor.registroProfissional)

            stnt.executeUpdate()
            stnt.close()
            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

    //compra aumenta o estoque da caixa comprada
    fun salvar(compra: Compra) {
        try {
            conectar()
            val sql = "INSERT INTO compra (fornecedor_cpf, caixa_da_agua_id, quantidade, preco, data_compra) VALUES (?, ?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)

            stnt.setString(1, compra.fornecedorCpf)
            stnt.setInt(2, compra.caixaDaAguaId)
            stnt.setInt(3, compra.quantidade)
            stnt.setBigDecimal(4, compra.preco)
            stnt.setDate(5, Date.valueOf(compra.dataCompra))

            stnt.executeUpdate()
            stnt.close()

            val sqlEstoque = "UPDATE caixa_da_agua SET quantidade = quantidade + ? WHERE id = ?"
            val stntEstoque = c!!.prepareStatement(sqlEstoque)
            stntEstoque.setInt(1, compra.quantidade)
            stntEstoque.setInt(2, compra.caixaDaAguaId)
            stntEstoque.executeUpdate()
            stntEstoque.close()

            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

    //venda diminui o estoque - confere se tem quantidade suficiente antes de aceitar
    fun salvar(venda: Venda) {
        try {
            conectar()

            val sqlEstoque = "SELECT quantidade FROM caixa_da_agua WHERE id = ?"
            val stntEstoque = c!!.prepareStatement(sqlEstoque)
            stntEstoque.setInt(1, venda.caixaDaAguaId)
            val resultado = stntEstoque.executeQuery()
            resultado.next()
            val estoqueAtual = resultado.getInt("quantidade")
            resultado.close()
            stntEstoque.close()

            if (estoqueAtual < venda.quantidade) {
                println("Estoque insuficiente! Só tem $estoqueAtual em estoque.")
                c!!.close()
                return
            }

            val sql = "INSERT INTO venda (cliente_cpf, caixa_da_agua_id, quantidade, preco, data_venda) VALUES (?, ?, ?, ?, ?)"
            val stnt = c!!.prepareStatement(sql)
            stnt.setString(1, venda.clienteCpf)
            stnt.setInt(2, venda.caixaDaAguaId)
            stnt.setInt(3, venda.quantidade)
            stnt.setBigDecimal(4, venda.preco)
            stnt.setDate(5, Date.valueOf(venda.dataVenda))
            stnt.executeUpdate()
            stnt.close()

            val sqlAtualizarEstoque = "UPDATE caixa_da_agua SET quantidade = quantidade - ? WHERE id = ?"
            val stntAtualizar = c!!.prepareStatement(sqlAtualizarEstoque)
            stntAtualizar.setInt(1, venda.quantidade)
            stntAtualizar.setInt(2, venda.caixaDaAguaId)
            stntAtualizar.executeUpdate()
            stntAtualizar.close()

            c!!.close()
        } catch (e: SQLException) {
            println("Não  salvou: ${e.printStackTrace()}")
        }
    }

}