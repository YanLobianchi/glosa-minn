package glosa.minn

import br.com.glosa.minn.exportacao.ExportacaoService
import org.springframework.stereotype.Component

@Component
class ExportacaoController {

	ExportacaoService exportacaoService

	def json() {
		return exportacaoService.json()
	}

	def csvGuias() {
		return exportacaoService.csvGuias()
	}

	def csvItens() {
		return exportacaoService.csvItens()
	}
}
