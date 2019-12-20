package conciliacao

import br.com.glosa.minn.conciliacao.ConciliacaoService
import guia.GuiaConvenio
import guia.GuiaHospital
import item.ItemConvenio
import item.ItemHospital
import spock.lang.Specification
import valortotal.ValorTotal

class ConciliacaoServiceTest extends Specification {

	GuiaConvenio guiaConvenio1
	GuiaConvenio guiaConvenio2
	GuiaHospital guiaHospital1
	GuiaHospital guiaHospital2
	GuiaHospital guiaHospital3
	ConciliacaoService conciliacaoService
	List<GuiaHospital> guiasHospital
	List<GuiaConvenio> guiasConvenio
	ItemHospital itemHospital1
	ItemHospital itemHospital2
	ItemHospital itemHospital3
	ItemConvenio itemConvenio1
	ItemConvenio itemConvenio2
	ItemConvenio itemConvenio3

	void setup() {
		conciliacaoService = new ConciliacaoService()
		guiaConvenio1 = new GuiaConvenio(numeroGuiaOperadora: '1234', numeroGuiaPrestador: '12345')

		guiaConvenio2 = new GuiaConvenio(numeroGuiaOperadora: '12344', numeroGuiaPrestador: '123455')

		guiaHospital1 = new GuiaHospital(numeroGuiaOperadora: '12344', numeroGuiaPrestador: '123455')
		guiaHospital1.valorTotal = new ValorTotal(valorTotalGeral: 1500)
		guiaHospital1.itens = []

		guiaHospital2 = new GuiaHospital()
		guiaHospital2.valorTotal = new ValorTotal(valorTotalGeral: 200)
		guiaHospital3 = new GuiaHospital(numeroGuiaOperadora: '1234', numeroGuiaPrestador: '12345')
		guiaHospital3.valorTotal = new ValorTotal(valorTotalGeral: 500)
		guiaHospital3.itens = []

		itemConvenio1 = new ItemConvenio(valorTotal: 200, codigoItem: '123', quantidade: 1)
		itemConvenio2 = new ItemConvenio(valorTotal: 300, codigoItem: '321', quantidade: 1)
		itemConvenio3 = new ItemConvenio(valorTotal: 1000, codigoItem: '111', quantidade: 1)

		itemHospital1 = new ItemHospital(valorTotal: 1000, codigoItem: '111', quantidade: 1)
		itemHospital2 = new ItemHospital(valorTotal: 300, codigoItem: '321', quantidade: 1)
		itemHospital3 = new ItemHospital(valorTotal: 200, codigoItem: '123', quantidade: 1)

		guiaHospital1.itens.addAll([itemHospital1, itemHospital2, itemHospital3])

		guiaConvenio2.addItem(itemConvenio1)
		guiaConvenio2.addItem(itemConvenio2)
		guiaConvenio2.addItem(itemConvenio3)


		guiaConvenio1.addItem(itemConvenio1)
		guiaConvenio1.addItem(itemConvenio2)

		guiaHospital3.itens.add(itemHospital1)

		guiasHospital = [guiaHospital1, guiaHospital2, guiaHospital3]
		guiasConvenio = [guiaConvenio1, guiaConvenio2]

	}

	void cleanup() {
	}

	def "RealizaConciliacaoDasGuias"() {
		when:
		conciliacaoService.realizaConciliacao(guiasHospital, guiasConvenio)
		then:
		guiaHospital1.guiaConciliada == guiaConvenio2
		itemConvenio1.itemConciliado == itemHospital3
		guiaHospital2.guiaConciliada == null
		guiaConvenio1.guiaConciliada == guiaHospital3
	}
}
