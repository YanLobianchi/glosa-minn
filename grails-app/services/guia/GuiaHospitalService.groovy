package guia

class GuiaHospitalService extends AbstractGuiaService<GuiaHospital> {

	@Override
	void amarraRelacionamentosGuia(final GuiaHospital guiaNoBanco, final GuiaHospital guiaEditada) {
		amarraRelacionamentosGuiaAbstrata(guiaNoBanco, guiaEditada)
		guiaNoBanco.valorTotal = guiaEditada.valorTotal
	}

}
