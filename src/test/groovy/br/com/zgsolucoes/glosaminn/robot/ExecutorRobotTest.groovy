package br.com.zgsolucoes.glosaminn.robot

import br.com.zgsolucoes.glosaminn.utils.CSVReader
import spock.lang.Specification

class ExecutorRobotTest extends Specification {

	def setup(){

	}

	void cleanup() {
	}

	def "Executar"() {
		when:
		ExecutorRobot.executar()
		String pathOriginal = '/home/igor/Documents/projeto/glosa-minn/src/test/resources/glosaMaxx/pagamento_glosamax_1.csv'
		String pathBaixado = '/home/igor//Documents/projeto/glosa-minn/src/main/resources/robot/glosaMaxx/12-2019/pagamento_glosamax_1.csv'
		File arquivoOriginal = new File(pathOriginal)
		File arquivoBaixado = new File(pathBaixado)

		then:
		arquivoOriginal.text == arquivoBaixado.text
	}
}
