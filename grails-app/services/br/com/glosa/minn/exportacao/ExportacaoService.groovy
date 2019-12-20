package br.com.glosa.minn.exportacao

import br.com.glosa.minn.guia.GuiaHospitalService
import br.com.glosa.minn.remessa.Remessa
import RemessaService
import br.com.glosa.minn.remessa.RemessaService
import com.fasterxml.jackson.databind.ObjectMapper
import grails.gorm.transactions.Transactional
import guia.Guia

import java.util.stream.Collectors
import java.util.stream.Stream

@Transactional
class ExportacaoService {

	GuiaHospitalService guiaHospitalService

	final ObjectMapper objectMapper = new ObjectMapper()

	String json() {
		final List<Guia> guias = Guia.list()
		return objectMapper.writeValueAsString(guias)
	}

	String csvGuias() {
		final List<Guia> guias = Guia.list()
		final List<Map> guias = remessas.stream().map({ remessa -> remessaService.extrairMapasDeGuias(remessa) })
										.collect(Collectors.toList()).flatten() as List<Map>
		return toCsv(guias)
	}

	String csvItens() {
		final List<Remessa> remessas = remessaService.list()
		final List<Map> itens = remessas.stream().map({ remessa -> remessaService.extrairMapasDeItens(remessa) })
										.collect(Collectors.toList()).flatten() as List<Map>
		return toCsv(itens)
	}

	String toCsv(final List<Map> mapas) {
		List<String> headers = mapas.stream().flatMap({ map -> map?.keySet()?.stream() ?: Stream.of([]) })
									.distinct().collect(Collectors.toList())

		final StringBuffer sb = new StringBuffer()
		for (int i = 0; i < headers.size(); i++) {
			sb.append(headers.get(i))
			sb.append(i == headers.size() - 1 ? "\n" : ";")
		}
		for (Map<String, Object> map : mapas) {
			for (int i = 0; i < headers.size(); i++) {
				sb.append(map.get(headers.get(i)))
				sb.append(i == headers.size() - 1 ? "\n" : ";")
			}
		}
		return sb.toString()
	}

}
