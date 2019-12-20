package br.com.zgsolucoes.glosaminn.domain.conciliacao

import br.com.zgsolucoes.glosaminn.domain.guia.GuiaConvenio
import br.com.zgsolucoes.glosaminn.domain.guia.GuiaHospital

class Conciliacao {
	GuiaConvenio guiaConvenio
	GuiaHospital guiaHospital
	Date dataConciliacao
	Boolean todosItensConciliados
}
