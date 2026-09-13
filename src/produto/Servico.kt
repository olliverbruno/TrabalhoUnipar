package produto

import enumeradores.Setor
import enumeradores.TipoServico
import enumeradores.Turno
import pessoas.Cliente
import pessoas.Funcionario
import java.math.BigDecimal
import java.time.LocalDate

//liga 1 cliente com 1 funcionario - nao tem relação N:N em lugar nenhum do projeto
class Servico {
    var funcionario : Funcionario = Funcionario(
        nome = "",
        cpf = "",
        idade = 0,
        salario = BigDecimal.ZERO,
        turno = Turno.NOTURNO,
        setor = Setor.INSTALACAO
    )
    var preco : BigDecimal = BigDecimal.ZERO
    var dataInstalacao : LocalDate = LocalDate.of(1970, 7, 4)
    var cliente : Cliente = Cliente(
        nomeCliente = "",
        cpfCliente = "",
        idadeCliente = 0,
        dividasAbertas = false,
        parcelasAPagar = mutableListOf()
    )
    var tipo : TipoServico = TipoServico.MONTAGEM //venda, manutenção ou montagem
}