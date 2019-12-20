package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.domain.dto.DtoGuiaGenerica
import br.com.zgsolucoes.glosaminn.domain.dto.DtoItemGenerico

class FonteHospitalSantaHelena extends FonteHospital {

	private static final GUIAS = '(?s)<guiaResumoInternacao>.*?</guiaResumoInternacao>'
	private static final CAMPO_ITENS = '(?s)<procedimentosExecutados>.*?</procedimentosExecutados>'
	private static final CAMPO_ITEM = '(?s)<procedimentoExecutado>.*?</procedimentoExecutado>'

	private static final DATA_EXECUCAO = '(?s)(?<=<dataExecucao>).*?(?=</dataExecucao>)'
	private static final CODIGO_TABELA = '(?s)(?<=<codigoTabela>).*?(?=</codigoTabela>)'
	private static final CODIGO_PROCEDIMENTO = '(?s)(?<=<codigoProcedimento>).*?(?=</codigoProcedimento>)'
	private static final QUANTIDADE_EXECUTADA = '(?s)(?<=<quantidadeExecutada>).*?(?=</quantidadeExecutada>)'
	private static final REDUCAO_ACRESCIMO = '(?s)(?<=<reducaoAcrescimo>).*?(?=</reducaoAcrescimo>)'
	private static final VALOR_UNITARIO = '(?s)(?<=<valorUnitario>).*?(?=</valorUnitario>)'
	private static final VALOR_TOTAL = '(?s)(?<=<valorTotal>).*?(?=</valorTotal>)'
	private static final DESCRICAO_PROCEDIMENTO = '(?s)(?<=<descricaoProcedimento>).*?(?=</descricaoProcedimento>)'

	private static final NUMERO_GUIA_PRESTADOR = '(?s)(?<=<numeroGuiaPrestador>).*?(?=</numeroGuiaPrestador>)'
	private static final NUMERO_GUIA_INTERNACAO = '(?s)(?<=<numeroGuiaSolicitacaoInternacao>).*?(?=</numeroGuiaSolicitacaoInternacao>)'
	private static final NUMERO_GUIA_OPERADORA = '(?s)(?<=<numeroGuiaOperadora>).*?(?=</numeroGuiaOperadora>)'
	private static final SENHA = '(?s)(?<=<senha>).*?(?=</senha>)'
	private static final NUMERO_CARTEIRA = '(?s)(?<=<numeroCarteira>).*?(?=</numeroCarteira>)'
	private static final ATENDIMENTO_RN = '(?s)(?<=<atendimentoRN>).*?(?=</atendimentoRN>)'
	private static final NOME_BENEFICIARIO = '(?s)(?<=<nomeBeneficiario>).*?(?=</nomeBeneficiario>)'
	private static final NUMERO_LOTE = '(?s)(?<=<numeroLote>).*?(?=</numeroLote>)'

	void preProcesseConteudoArquivo(String caminhoArquivo) {

		String conteudoXml = new File(caminhoArquivo).text

		processeConteudoArquivo(conteudoXml)
	}

	private static List<DtoGuiaGenerica> processeConteudoArquivo(String conteudoXml) {
		String numero_lote = conteudoXml.find(NUMERO_LOTE)
		List<String> conteudoGuias = conteudoXml.findAll(GUIAS)
		List<DtoGuiaGenerica> guias = []

		for (String conteudoGuia in conteudoGuias) {
			DtoGuiaGenerica guia = new DtoGuiaGenerica()

			obtenhaInfosGuia(conteudoGuia, guia)
			List<DtoItemGenerico> itensList = obtenhaItensDaGuia(conteudoGuia)

			guia.itens = itensList
			guia.lote = numero_lote

			guias.add(guia)
		}

		return guias
	}

	private static List<DtoItemGenerico> obtenhaItensDaGuia(String conteudoGuia) {
		String conteudoItens = conteudoGuia.find(CAMPO_ITENS)
		List<String> itens = conteudoItens.findAll(CAMPO_ITEM)
		List<DtoItemGenerico> itensList = []

		for (String item in itens) {
			DtoItemGenerico dtoItem = new DtoItemGenerico()

			dtoItem.dataExecucao = item.find(DATA_EXECUCAO)
			dtoItem.codigoTabela = item.find(CODIGO_TABELA)
			dtoItem.codigoProcedimento = item.find(CODIGO_PROCEDIMENTO)
			dtoItem.reducaoAcrescimo = item.find(REDUCAO_ACRESCIMO)
			dtoItem.valorUnitario = item.find(VALOR_UNITARIO)
			dtoItem.valorPago = item.find(VALOR_TOTAL)
			dtoItem.descricaoProcedimento = item.find(DESCRICAO_PROCEDIMENTO)
			dtoItem.quantidade = item.find(QUANTIDADE_EXECUTADA)

			itensList.add(dtoItem)
		}

		return itensList
	}

	private static DtoGuiaGenerica obtenhaInfosGuia(String conteudoGuia, DtoGuiaGenerica guia) {
		guia.nomeGuiaPrestador = conteudoGuia.find(NOME_BENEFICIARIO)
		guia.numeroGuiaPrestador = conteudoGuia.find(NUMERO_GUIA_PRESTADOR)
		guia.numeroGuiaOperadora = conteudoGuia.find(NUMERO_GUIA_OPERADORA)
		guia.matricula = conteudoGuia.find(NUMERO_CARTEIRA)
		guia.senha = conteudoGuia.find(SENHA)
		guia.atendimentoRN = conteudoGuia.find(ATENDIMENTO_RN)
		guia.numero = conteudoGuia.find(NUMERO_GUIA_INTERNACAO)

		return guia
	}

}
