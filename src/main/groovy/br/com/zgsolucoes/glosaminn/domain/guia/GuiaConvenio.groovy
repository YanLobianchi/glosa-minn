package br.com.zgsolucoes.glosaminn.domain.guia


import br.com.zgsolucoes.glosaminn.domain.item.ItemConvenio
import br.com.zgsolucoes.glosaminn.dto.DtoGuiaGenerica
import groovy.transform.CompileStatic

@CompileStatic
class GuiaConvenio extends Guia<ItemConvenio> {
	BigDecimal valorPago
	BigDecimal valorGlosa
	BigDecimal valorApresentado

	void addItem(ItemConvenio item) {
		valorPago = valorPago ? valorPago + item.valorPago : item.valorPago
		valorGlosa = valorGlosa ? valorGlosa + item.valorGlosa : item.valorGlosa
		valorApresentado = valorApresentado ? valorApresentado + item.valorTotal : item.valorTotal
		if (itens) {
			itens.add(item)
		} else {
			itens = [item]
		}
	}

}
