package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.glosa.minn.valortotal.ValorTotal
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import guia.DadosBeneficiario
import guia.GuiaHospital
import item.ItemHospital

import java.text.SimpleDateFormat

@CompileStatic(TypeCheckingMode.SKIP)
class FonteHospitalSantaHelena extends FonteHospital {

	private static final GUIAS = '(?s)<guiaResumoInternacao>.*?</guiaResumoInternacao>'
	private static final CAMPO_ITENS = '(?s)<procedimentosExecutados>.*?</procedimentosExecutados>'
	private static final CAMPO_ITEM = '(?s)<procedimentoExecutado>.*?</procedimentoExecutado>'

	private static final DATA_EXECUCAO = '(?s)(?<=<dataExecucao>).*?(?=</dataExecucao>)'
	private static final CODIGO_TABELA = '(?s)(?<=<codigoTabela>).*?(?=</codigoTabela>)'
	private static final CODIGO_PROCEDIMENTO = '(?s)(?<=<codigoProcedimento>).*?(?=</codigoProcedimento>)'
	private static final QUANTIDADE_EXECUTADA = '(?s)(?<=<quantidadeExecutada>).*?(?=</quantidadeExecutada>)'
	private static final VALOR_UNITARIO = '(?s)(?<=<valorUnitario>).*?(?=</valorUnitario>)'
	private static final VALOR_TOTAL_ITEM = '(?s)(?<=<valorTotal>).*?(?=</valorTotal>)'
	private static final VALOR_TOTAL_GUIA = '(?s)(?<=<valorTotalGeral>).*?(?=</valorTotalGeral>)'
	private static final VALOR_PROCEDIMENTOS = '(?s)(?<=<valorProcedimentos>).*?(?=</valorProcedimentos>)'
	private static final VALOR_DIARIAS = '(?s)(?<=<valorDiarias>).*?(?=</valorDiarias>)'
	private static final VALOR_TAXAS_ALUGUEIS = '(?s)(?<=<valorTaxasAlugueis>).*?(?=</valorTaxasAlugueis>)'
	private static final VALOR_MATERIAIS = '(?s)(?<=<valorMateriais>).*?(?=</valorMateriais>)'
	private static final VALOR_MEDICAMENTOS = '(?s)(?<=<valorMedicamentos>).*?(?=</valorMedicamentos>)'
	private static final VALOR_OPME = '(?s)(?<=<valorOPME>).*?(?=</valorOPME>)'
	private static final VALOR_GASES_MEDICINAIS = '(?s)(?<=<valorGasesMedicinais>).*?(?=</valorGasesMedicinais>)'
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

	List<GuiaHospital> processeConteudoArquivo(String conteudoXml) {
		String numero_lote = conteudoXml.find(NUMERO_LOTE)
		List<String> conteudoGuias = conteudoXml.findAll(GUIAS)
		List<GuiaHospital> guias = []

		for (String conteudoGuia in conteudoGuias) {
			GuiaHospital guia = new GuiaHospital()

			obtenhaInfosGuia(conteudoGuia, guia)
			List<ItemHospital> itensList = obtenhaItensDaGuia(conteudoGuia)

			guia.itens = itensList
			guia.lote = numero_lote
			guia.nomeConvenio = "GlosaMaxx"

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

			String strValorPago = item.find(VALOR_TOTAL_ITEM)
			String strValorUnitario = item.find(VALOR_UNITARIO)

			itemHospital.quantidade = item.find(QUANTIDADE_EXECUTADA).toInteger()
			itemHospital.dataExecucao = obtenhaData(item)
			itemHospital.codigoTabela = item.find(CODIGO_TABELA)
			itemHospital.codigoItem = item.find(CODIGO_PROCEDIMENTO)
			itemHospital.descricaoItem = item.find(DESCRICAO_PROCEDIMENTO)

			BigDecimal valorPago = new BigDecimal(strValorPago)
			BigDecimal valorUnitario = new BigDecimal(strValorUnitario)

			itemHospital.valorTotal = valorPago
			itemHospital.valorUnitario = valorUnitario

			itensList.add(itemHospital)
		}

		return itensList
	}

	private static GuiaHospital obtenhaInfosGuia(String conteudoGuia, GuiaHospital guia) {
		DadosBeneficiario dadosBeneficiario = new DadosBeneficiario()
		guia.dadosBeneficiario = dadosBeneficiario

		guia.dadosBeneficiario.nomeBeneficiario = conteudoGuia.find(NOME_BENEFICIARIO)
		guia.dadosBeneficiario.atendimentoRn = conteudoGuia.find(ATENDIMENTO_RN)
		guia.dadosBeneficiario.numero_guia_internacao = conteudoGuia.find(NUMERO_GUIA_INTERNACAO)
		guia.dadosBeneficiario.matricula = conteudoGuia.find(NUMERO_CARTEIRA)


		guia.numeroGuiaPrestador = conteudoGuia.find(NUMERO_GUIA_PRESTADOR)
		guia.numeroGuiaOperadora = conteudoGuia.find(NUMERO_GUIA_OPERADORA)
		guia.senha = conteudoGuia.find(SENHA)

		ValorTotal valor = new ValorTotal()
		guia.valorTotal = valor

		guia.valorTotal.valorTotalGeral = new BigDecimal(conteudoGuia.find(VALOR_TOTAL_GUIA))
		guia.valorTotal.valorProcedimentos = new BigDecimal(conteudoGuia.find(VALOR_PROCEDIMENTOS))
		guia.valorTotal.valorDiarias = new BigDecimal(conteudoGuia.find(VALOR_DIARIAS))
		guia.valorTotal.valorTaxasAlugueis = new BigDecimal(conteudoGuia.find(VALOR_TAXAS_ALUGUEIS))
		guia.valorTotal.valorMedicamentos= new BigDecimal(conteudoGuia.find(VALOR_MEDICAMENTOS))
		guia.valorTotal.valorOPME = new BigDecimal(conteudoGuia.find(VALOR_OPME))
		guia.valorTotal.valorGasesMedicinais = new BigDecimal(conteudoGuia.find(VALOR_GASES_MEDICINAIS))
		guia.valorTotal.valorMateriais  = new BigDecimal(conteudoGuia.find(VALOR_MATERIAIS))

		return guia
	}

	private static Date obtenhaData(String item) {
		String padrao = "yyyy-MM-dd"
		String dataExtraida = item.find(DATA_EXECUCAO)
		Date data = new SimpleDateFormat(padrao).parse(dataExtraida)

		return data
	}
}
