package br.com.zgsolucoes.glosaminn.domain.guia

import br.com.zgsolucoes.glosaminn.domain.item.Item
import br.com.zgsolucoes.glosaminn.domain.item.ItemConvenio

class GuiaConvenio extends Guia<ItemConvenio> {
	BigDecimal valorPago
	BigDecimal valorGlosa
	BigDecimal valorApresentado


	void addItem(ItemConvenio item){
		valorPago? valorPago + item.valorPago : item.valorPago
		valorGlosa? valorGlosa + item.valorGlosa : item.valorGlosa
		valorApresentado? valorApresentado + item.valorTotal : item.valorTotal
		itens? itens.add(item) : [item] as List
	}

}
