package guia


import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import item.Item

@CompileStatic(TypeCheckingMode.SKIP)
abstract class Guia<I extends Item> {
	Guia guiaConciliada
	String nomeConvenio
	String lote
	String numeroGuiaPrestador
	String numeroSolicitacaoInternacao
	String numeroGuiaOperadora
	String senha
	DadosBeneficiario dadosBeneficiario
	List<I> itens = [] as List<I>

	Map toMapAbstract() {
		return [
				guiaConciliada             : this.guiaConciliada,
				nomeConvenio               : this.nomeConvenio,
				lote                       : this.lote,
				numeroGuiaPrestador        : this.numeroGuiaPrestador,
				numeroSolicitacaoInternacao: this.numeroSolicitacaoInternacao,
				numeroGuiaOperadora        : this.numeroGuiaOperadora,
				senha                      : this.senha,
				dadosBeneficiario          : this.dadosBeneficiario,
				itens                      : this.itens*.toMap(),
		]
	}

	abstract Map toMap()

	static hasMany = [itens: Item]

	static constraints = {
	}

	static embedded = ['dadosBeneficiario']
}
