package item

class ItemHospital extends Item {
	BigDecimal reducaoAcrescimo
	String unidadeMedida

	Map toMap() {
		this.toMapAbstract() + [
				reducaoAcrescimo: this.reducaoAcrescimo,
				unidadeMedida   : this.unidadeMedida,
		]
	}
}
