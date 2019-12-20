package br.com.zgsolucoes.glosaminn.domain.item

abstract class Item {

	Date dataExecucao
	String horaInicial
	String horafinal

	String codigoTabela
	String codigoItem
	String descricaoItem
	Integer quantidade

	BigDecimal valorUnitario
	BigDecimal valorTotal

}
