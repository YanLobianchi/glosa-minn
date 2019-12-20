package guia

class GuiaConvenioService extends AbstractGuiaService<GuiaConvenio> {

	@Override
	void amarraRelacionamentosGuia(final GuiaConvenio guiaNoBanco, final GuiaConvenio guiaEditada) {
		amarraRelacionamentosGuiaAbstrata(guiaNoBanco, guiaEditada)
		guiaNoBanco.valorPago = guiaEditada.valorPago
		guiaNoBanco.valorGlosa = guiaEditada.valorGlosa
		guiaNoBanco.valorApresentado = guiaEditada.valorApresentado
	}

}
