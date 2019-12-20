package guia

import item.ItemConvenio

class GuiaConvenio extends Guia<ItemConvenio> {
	BigDecimal valorPago
	BigDecimal valorGlosa
	BigDecimal valorApresentado

	void addItem(ItemConvenio item) {
		valorPago = valorPago ? valorPago + item.valorPago : item.valorPago
		valorGlosa = valorGlosa ? valorGlosa + item.valorGlosa : item.valorGlosa
		valorApresentado = valorApresentado ? valorApresentado + item.valorTotal : item.valorTotal
		itens.add(item)
	}

}