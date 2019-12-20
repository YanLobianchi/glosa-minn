package conciliacao

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteHospitalSantaHelena
import br.com.zgsolucoes.glosaminn.robot.ExecutorRobot
import guia.GuiaConvenio
import guia.GuiaHospital
import item.Item
import item.ItemConvenio
import item.ItemHospital

class ConciliacaoService {

	void realizaImportacaoEConciliacao() {
		File pastaPrestador = new File("resources/Arquivos Prestador")
		File[] arquivosPrestador = pastaPrestador.listFiles()
		FonteHospitalSantaHelena fonte = new FonteHospitalSantaHelena()
		List<GuiaHospital> guiasHospital = []
		for (File arquivo : arquivosPrestador) {
			String conteudoXml = arquivo.text
			guiasHospital.addAll(fonte.processeConteudoArquivo(conteudoXml))
		}

		File pastaConvenio = new File("resources/Arquivos Prestador")
		File[] arquivosConvenio = pastaPrestador.listFiles()
//		FonteHospitalSantaHelena fonte = new FonteHospitalSantaHelena()
		List<GuiaHospital> guiasConvenio = []
//		for(File arquivo : arquivosPrestador) {
//			String conteudoXml = arquivo.text
//			guiasConvenio.addAll(fonte.processeConteudoArquivo(conteudoXml))
//		}

		realizaConciliacao(guiasHospital as List<GuiaHospital>, guiasConvenio as List<GuiaConvenio>)

	}

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
//				guiaConvenioEquivalente.save()
//				guiaHospital.save()
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
//				itemHospital.save()
//				itemConvenioEquivalente.save(flush: true)
			}
		}
	}

}
