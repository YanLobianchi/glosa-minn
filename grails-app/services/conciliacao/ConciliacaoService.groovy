package conciliacao

import guia.GuiaConvenio
import guia.GuiaHospital
import item.Item

class ConciliacaoService {

	void realizaConciliacao(List<GuiaHospital> guiasHospital, List<GuiaConvenio> guiasConvenio) {
		for (GuiaHospital guiaHospital : guiasHospital) {
			GuiaConvenio guiaConvenioEquivalente = guiasConvenio.find { GuiaConvenio guiaConvenio ->
				guiaConvenio.numeroGuiaOperadora == guiaHospital.numeroGuiaOperadora &&
						guiaConvenio.numeroGuiaPrestador == guiaHospital.numeroGuiaPrestador &&
						guiaConvenio.valorApresentado == guiaHospital.valorTotal.valorTotalGeral
			}
			if (guiaConvenioEquivalente) {
				guiaConvenioEquivalente.guiaConciliada = guiaHospital
				guiaHospital.guiaConciliada = guiaConvenioEquivalente
				realizaEquivalenciaItens(guiaHospital.itens, guiaConvenioEquivalente.itens)
			}
		}
	}

	private static void realizaEquivalenciaItens(List<Item> itensHospital, List<Item> itensConvenio) {
		for (Item itemHospital : itensHospital) {
			Item itemConvenioEquivalente = itensConvenio.find { Item itemConvenio ->
				itemConvenio.quantidade == itemHospital.quantidade &&
						itemConvenio.codigoItem == itemHospital.codigoItem &&
						itemConvenio.valorTotal == itemHospital.valorTotal
			}
			if (itemConvenioEquivalente) {
				itemConvenioEquivalente.itemConciliado = itemHospital.itemConciliado
				itemHospital.itemConciliado = itemConvenioEquivalente.itemConciliado
			}
		}
	}

}
