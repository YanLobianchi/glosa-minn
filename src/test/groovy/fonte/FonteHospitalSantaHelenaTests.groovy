package fonte

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteHospitalSantaHelena

class FonteHospitalSantaHelenaTests extends GroovyTestCase {

	FonteHospitalSantaHelena fonte

	void setUp() {
		fonte = new FonteHospitalSantaHelena()
	}

	void testaImportacaoArquivo() {

		String caminhoArquivo = 'resources/hospital/hospitalSantaHelena/faturamento_glosamaxx_2016-02-01_161526_3.02.00.xml'

		fonte.preProcesseConteudoArquivo(caminhoArquivo)

	}
}
