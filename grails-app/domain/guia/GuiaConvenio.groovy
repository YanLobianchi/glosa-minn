package guia

import grails.compiler.GrailsCompileStatic
import item.ItemConvenio

@GrailsCompileStatic
class GuiaConvenio extends Guia<ItemConvenio> {
	BigDecimal valorPago
	BigDecimal valorGlosa
	BigDecimal valorApresentado

	void addItem(ItemConvenio item) {
		valorPago = valorPago ? valorPago + item.valorPago : item.valorPago
		valorGlosa = valorGlosa ? valorGlosa + item.valorGlosa : item.valorGlosa
		valorApresentado = valorApresentado ? valorApresentado + item.valorTotal : item.valorTotal
		itens = (itens ? itens.add(item) : [item]) as List<ItemConvenio>
	}

}
