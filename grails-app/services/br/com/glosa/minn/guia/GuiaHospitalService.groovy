package br.com.glosa.minn.guia

import guia.GuiaHospital
import remessa.Remessa

class GuiaHospitalService extends AbstractGuiaService<GuiaHospital> {

	@Override
	void amarraRelacionamentosGuia(final GuiaHospital guiaNoBanco, final GuiaHospital guiaEditada) {
		amarraRelacionamentosGuiaAbstrata(guiaNoBanco, guiaEditada)
		guiaNoBanco.valorTotal = guiaEditada.valorTotal
	}

	List<Map<String, ?>> extrairMapasDeGuias(final List<GuiaHospital> guias) {
		final List<Map> guiasMaps = guias.findResults { it.guiaConciliada != null ? it.toMap() : null }
		guiasMaps*.remove('itens')
		return guiasMaps
	}

	List<Map<String, ?>> extrairMapasDeItens(final List<GuiaHospital> guias) {
		final List<Map> itensMaps = guias.findResults { final GuiaHospital guia ->
			if (guia.guiaConciliada == null) {
				return null
			}

			final List<Map> itens = guia.itens*.toMap()
			itens.forEach({ final Map item ->
				Map guiaMap = guia.toMap()
				guiaMap.remove('itens')
				item.putAll(guiaMap)
			})

			return itens
		}.flatten() as List<Map>

		return itensMaps
	}

}
