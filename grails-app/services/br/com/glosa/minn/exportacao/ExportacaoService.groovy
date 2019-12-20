package br.com.glosa.minn.exportacao

import br.com.glosa.minn.guia.GuiaHospitalService
import com.fasterxml.jackson.databind.ObjectMapper
import grails.gorm.transactions.Transactional
import guia.Guia
import guia.GuiaHospital

import java.util.stream.Collectors
import java.util.stream.Stream

@Transactional
class ExportacaoService {

	GuiaHospitalService guiaHospitalService

	final ObjectMapper objectMapper = new ObjectMapper()

	String json(List<GuiaHospital> guias) {
		return objectMapper.writeValueAsString(guias)
	}

	String csvGuias(guias) {
		final List<Map> guiasMaps = guias.stream().map({ remessa -> guiaHospitalService.extrairMapasDeGuias(guias) })
										 .collect(Collectors.toList()).flatten() as List<Map>
		return toCsv(guiasMaps)
	}

	String csvItens(guias) {
		final List<Map> itens = guias.stream().map({ remessa -> guiaHospitalService.extrairMapasDeItens(guias) })
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
