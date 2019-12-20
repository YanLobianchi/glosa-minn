package fonte

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteHospitalSantaHelena
import guia.GuiaHospital
import item.ItemHospital

class FonteHospitalSantaHelenaTests extends GroovyTestCase {

	FonteHospitalSantaHelena fonte

	void setUp() {
		fonte = new FonteHospitalSantaHelena()
	}

	void testaImportacaoArquivo_GuiaHospital_corretamente() {

		String caminhoArquivo = 'resources/hospital/hospitalSantaHelena/faturamento_glosamaxx_2016-02-01_161526_3.02.00.xml'
		String conteudoXml = new File(caminhoArquivo).text

		List<GuiaHospital> guiasList = fonte.processeConteudoArquivo(conteudoXml)

		assertEquals 4, guiasList.size()

		GuiaHospital primeiraGuia = guiasList.first()

		assertEquals "EVANDRO LUIZ SANTIAGO VIDIGAL", primeiraGuia.dadosBeneficiario.nomeBeneficiario
		assertEquals "100222064", primeiraGuia.dadosBeneficiario.matricula
		assertEquals "N", primeiraGuia.dadosBeneficiario.atendimentoRn
		assertEquals "201600024210", primeiraGuia.dadosBeneficiario.numero_guia_internacao
		assertEquals "8551", primeiraGuia.lote
		assertEquals "201600024210", primeiraGuia.senha
		assertEquals 567.80, primeiraGuia.valorTotal.valorTotalGeral

		assertEquals 11, primeiraGuia.itens.size()

		ItemHospital itemExemplar = primeiraGuia.itens.first()

		assertEquals "22", itemExemplar.codigoTabela
		assertEquals "40805026", itemExemplar.codigoItem
		assertEquals "RX - Torax - 2 incidencias", itemExemplar.descricaoItem
		assertEquals 26.06, itemExemplar.valorUnitario
		assertEquals 26.06, itemExemplar.valorTotal

		GuiaHospital ultimaGuia = guiasList.last()

		assertEquals "SIDNEY DE JESUS VALENTIM", ultimaGuia.dadosBeneficiario.nomeBeneficiario
		assertEquals "105425613", ultimaGuia.dadosBeneficiario.matricula
		assertEquals "N", ultimaGuia.dadosBeneficiario.atendimentoRn
		assertEquals "201600001396", ultimaGuia.dadosBeneficiario.numero_guia_internacao
		assertEquals "8551", ultimaGuia.lote
		assertEquals "201600001396", ultimaGuia.senha
		assertEquals 896.13, ultimaGuia.valorTotal.valorTotalGeral
		assertEquals 276.60, ultimaGuia.valorTotal.valorProcedimentos
		assertEquals 273.52, ultimaGuia.valorTotal.valorDiarias

	}
}
