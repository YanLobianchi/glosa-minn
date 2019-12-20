package item

class ItemConvenio extends Item {

	BigDecimal valorPago
	BigDecimal valorGlosa
	String motivoGlosa
	String codigoGlosa
	String lote

	String numeroGuiaPrestador
	String numeroSolicitanteInternacao
	String numeroGuiaOperadora
	String senha
	String matricula
	String rn
	String nomeGuiaPrestador

	Map toMap() {
		this.toMapAbstract() + [
				valorPago  : this.valorPago,
				valorGlosa : this.valorGlosa,
				motivoGlosa: this.motivoGlosa,
				codigoGlosa: this.codigoGlosa,
		]
	}

}
