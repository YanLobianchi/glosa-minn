package item

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

}
