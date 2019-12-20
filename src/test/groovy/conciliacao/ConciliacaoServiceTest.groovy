package conciliacao

import br.com.zgsolucoes.glosaminn.domain.guia.GuiaConvenio
import br.com.zgsolucoes.glosaminn.domain.guia.GuiaHospital
import br.com.zgsolucoes.glosaminn.domain.valortotal.ValorTotal
import conciliacao.ConciliacaoService
import spock.lang.Specification

class ConciliacaoServiceTest extends Specification {

	GuiaConvenio guiaConvenio1
	GuiaConvenio guiaConvenio2
	GuiaHospital guiaHospital1
	GuiaHospital guiaHospital2
	GuiaHospital guiaHospital3
	ConciliacaoService conciliacaoService
	List<GuiaHospital> guiasHospital
	List<GuiaConvenio> guiasConvenio

	void setup() {
		conciliacaoService = new ConciliacaoService()

	}

	void cleanup() {
	}

	def "RealizaConciliacaoDasGuias"() {
		given:
		guiaConvenio1 = new GuiaConvenio(valorApresentado: 500, numeroGuiaOperadora: '1234', numeroGuiaPrestador: '12345')
		guiaConvenio2 = new GuiaConvenio(valorApresentado: 1500)
		guiaHospital1 = new GuiaHospital()
		guiaHospital1.valorTotal = new ValorTotal(valorTotalGeral: 1500)
		guiaHospital2 = new GuiaHospital()
		guiaHospital2.valorTotal = new ValorTotal(valorTotalGeral: 200)
		guiaHospital3 = new GuiaHospital(numeroGuiaOperadora: '1234', numeroGuiaPrestador: '12345')
		guiaHospital3.valorTotal = new ValorTotal(valorTotalGeral: 500)
		guiasHospital = [guiaHospital1, guiaHospital2, guiaHospital3]
		guiasConvenio = [guiaConvenio1, guiaConvenio2]


		when:
		conciliacaoService.realizaConciliacao(guiasHospital, guiasConvenio)
		then:
		guiaHospital1.guiaConciliada == guiaConvenio2
		guiaHospital2.guiaConciliada == null
		guiaConvenio1.guiaConciliada == guiaHospital3
	}


	def "RealizaConciliacaoDosItens"() {
		given:
		guiaConvenio1 = new GuiaConvenio(valorApresentado: 500, numeroGuiaOperadora: '1234', numeroGuiaPrestador: '12345')
		guiaConvenio2 = new GuiaConvenio(valorApresentado: 1500)
		guiaHospital1 = new GuiaHospital()
		guiaHospital1.valorTotal = new ValorTotal(valorTotalGeral: 1500)
		guiaHospital2 = new GuiaHospital()
		guiaHospital2.valorTotal = new ValorTotal(valorTotalGeral: 200)
		guiaHospital3 = new GuiaHospital(numeroGuiaOperadora: '1234', numeroGuiaPrestador: '12345')
		guiaHospital3.valorTotal = new ValorTotal(valorTotalGeral: 500)
		guiasHospital = [guiaHospital1, guiaHospital2, guiaHospital3]
		guiasConvenio = [guiaConvenio1, guiaConvenio2]


		when:
		conciliacaoService.realizaConciliacao(guiasHospital, guiasConvenio)
		then:
		guiaHospital1.guiaConciliada == guiaConvenio2
		guiaHospital2.guiaConciliada == null
		guiaConvenio1.guiaConciliada == guiaHospital3
	}
}
