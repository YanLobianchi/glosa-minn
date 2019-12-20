package fonte

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteHospitalSantaHelena
import guia.GuiaHospital

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

	}
}
