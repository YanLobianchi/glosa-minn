package br.com.zgsolucoes.glosaminn.service.conciliacao

import br.com.zgsolucoes.glosaminn.domain.conciliacao.Conciliacao
import br.com.zgsolucoes.glosaminn.domain.guia.GuiaConvenio
import br.com.zgsolucoes.glosaminn.domain.guia.GuiaHospital
import spock.lang.Specification

class ConciliacaoServiceTest extends Specification {

	GuiaConvenio guiaConvenio1
	GuiaHospital guiaHospital1
	GuiaConvenio guiaConvenio2
	GuiaHospital guiaHospital2
	ConciliacaoService conciliacaoService
	List<GuiaHospital> guiasHospital
	List<GuiaConvenio> guiasConvenio
	List<Conciliacao> conciliados

	void setup() {

	}

	void cleanup() {
	}

	def "RealizaConciliacao"() {
		Setup:
		guiaConvenio1 = new GuiaConvenio()
		guiaConvenio2 = new GuiaConvenio()
		guiaHospital1 = new GuiaHospital()
		guiaHospital2 = new GuiaHospital()
		guiasHospital = [guiaHospital1, guiaHospital2]
		guiasConvenio = [guiaConvenio1, guiaConvenio2]
		
		
		when:
		conciliacaoService.realizaConciliacao(guiasHospital, guiasConvenio) == conciliados
	}
}
