package guia

import item.ItemHospital
import glosa.minn.ValorTotal

class GuiaHospital extends Guia<ItemHospital> {
	ValorTotal valorTotal

	static embedded = ['valorTotal']

}
