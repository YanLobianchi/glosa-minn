package glosa.minn

import br.com.glosa.minn.exportacao.ExportacaoService
import guia.Guia
import guia.GuiaHospital
import org.springframework.stereotype.Component

@Component
class ExportacaoController {

	ExportacaoService exportacaoService

	def json() {
		final List<GuiaHospital> guias = Guia.list() as List<GuiaHospital>
		return exportacaoService.json(guias)
	}

	def csvGuias() {
		final List<GuiaHospital> guias = Guia.list() as List<GuiaHospital>
		return exportacaoService.csvGuias(guias)
	}

	def csvItens() {
		final List<GuiaHospital> guias = Guia.list() as List<GuiaHospital>
		return exportacaoService.csvItens(guias)
	}
}
