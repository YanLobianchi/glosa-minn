package item

import java.text.DateFormat

abstract class Item {
	Item itemConciliado
	Date dataExecucao
	String horaInicial
	String horaFinal

	String codigoTabela
	String codigoItem
	String descricaoItem
	Integer quantidade

	BigDecimal valorUnitario
	BigDecimal valorTotal

	Map toMapAbstract() {
		return [
				dataExecucao : DateFormat.instance.format(this.dataExecucao),
				horaInicial  : this.horaInicial,
				horaFinal    : this.horaFinal,
				codigoTabela : this.codigoTabela,
				codigoItem   : this.codigoItem,
				descricaoItem: this.descricaoItem,
				quantidade   : this.quantidade,
				valorUnitario: this.valorUnitario,
				valorTotal   : this.valorTotal,
		]
	}

	abstract Map toMap()

}
