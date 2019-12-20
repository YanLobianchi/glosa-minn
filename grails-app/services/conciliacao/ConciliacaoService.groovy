package conciliacao

import guia.GuiaConvenio
import guia.GuiaHospital
import item.Item
import item.ItemConvenio
import item.ItemHospital

class ConciliacaoService {

	void realizaConciliacao(List<GuiaHospital> guiasHospital, List<GuiaConvenio> guiasConvenio) {
		for (GuiaHospital guiaHospital : guiasHospital) {
			List<GuiaConvenio> guiasConvenioEquivalente = guiasConvenio.findAll { GuiaConvenio guiaConvenio ->
				guiaConvenio.numeroGuiaOperadora == guiaHospital.numeroGuiaOperadora &&
						guiaConvenio.numeroGuiaPrestador == guiaHospital.numeroGuiaPrestador &&
						guiaConvenio.valorApresentado == guiaHospital.valorTotal.valorTotalGeral &&
						guiaConvenio.senha == guiaHospital.senha &&
						guiaConvenio.nomeConvenio == guiaHospital.nomeConvenio
			}
			if (guiasConvenioEquivalente.size() == 1) {
				GuiaConvenio guiaConvenioEquivalente = guiasConvenioEquivalente.first()
				guiaConvenioEquivalente.guiaConciliada = guiaHospital
				guiaHospital.guiaConciliada = guiaConvenioEquivalente
				realizaEquivalenciaItens(guiaHospital.itens as List<ItemHospital>, guiaConvenioEquivalente.itens as List<ItemConvenio>)
			}
		}
	}

	private static void realizaEquivalenciaItens(List<ItemHospital> itensHospital, List<ItemConvenio> itensConvenio) {
		for (Item itemHospital : itensHospital) {
			Item itemConvenioEquivalente = itensConvenio.find { Item itemConvenio ->
				itemConvenio.quantidade == itemHospital.quantidade &&
						itemConvenio.codigoItem == itemHospital.codigoItem &&
						itemConvenio.valorTotal == itemHospital.valorTotal && 
						itemConvenio.dataExecucao == itemHospital.dataExecucao
			}
			if (itemConvenioEquivalente) {
				itemConvenioEquivalente.itemConciliado = itemHospital
				itemHospital.itemConciliado = itemConvenioEquivalente
			}
		}
	}

}
