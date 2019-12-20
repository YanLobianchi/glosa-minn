package guia

import item.ItemHospital
import valortotal.ValorTotal

class GuiaHospital extends Guia<ItemHospital> {
	ValorTotal valorTotal

	static embedded = ['valorTotal']

	Map toMap() {
		toMapAbstract() + [
				valorTotal: this.valorTotal.toMap(),
		]
	}

}
