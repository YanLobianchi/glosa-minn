package br.com.zgsolucoes.glosaminn.domain.fonte

import guia.DadosBeneficiario
import guia.GuiaHospital
import item.ItemHospital

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

	private static List<GuiaHospital> processeConteudoArquivo(String conteudoXml) {
		String numero_lote = conteudoXml.find(NUMERO_LOTE)
		List<String> conteudoGuias = conteudoXml.findAll(GUIAS)
		List<GuiaHospital> guias = []

		for (String conteudoGuia in conteudoGuias) {
			GuiaHospital guia = new GuiaHospital()

			obtenhaInfosGuia(conteudoGuia, guia)
			List<ItemHospital> itensList = obtenhaItensDaGuia(conteudoGuia)

			guia.itens = itensList
			guia.lote = numero_lote

			guias.add(guia)
		}

		return guias
	}

	private static List<ItemHospital> obtenhaItensDaGuia(String conteudoGuia) {
		String conteudoItens = conteudoGuia.find(CAMPO_ITENS)
		List<String> itens = conteudoItens.findAll(CAMPO_ITEM)
		List<ItemHospital> itensList = []

		for (String item in itens) {
			ItemHospital itemHospital = new ItemHospital()

			itemHospital.codigoTabela = item.find(CODIGO_TABELA)
			itemHospital.codigoItem = item.find(CODIGO_PROCEDIMENTO)
			itemHospital.descricaoItem = item.find(DESCRICAO_PROCEDIMENTO)

			itensList.add(itemHospital)
		}

		return itensList
	}

	private static GuiaHospital obtenhaInfosGuia(String conteudoGuia, GuiaHospital guia) {
		DadosBeneficiario dadosBeneficiario = new DadosBeneficiario()
		guia.dadosBeneficiario = dadosBeneficiario

		guia.dadosBeneficiario.nomeBeneficiario = conteudoGuia.find(NOME_BENEFICIARIO)
		guia.numeroGuiaPrestador = conteudoGuia.find(NUMERO_GUIA_PRESTADOR)
		guia.numeroGuiaOperadora = conteudoGuia.find(NUMERO_GUIA_OPERADORA)
		guia.dadosBeneficiario.matricula = conteudoGuia.find(NUMERO_CARTEIRA)
		guia.senha = conteudoGuia.find(SENHA)
		guia.dadosBeneficiario.atendimentoRn = conteudoGuia.find(ATENDIMENTO_RN)
		guia.dadosBeneficiario.numero_guia_internacao = conteudoGuia.find(NUMERO_GUIA_INTERNACAO)

		return guia
	}

}
