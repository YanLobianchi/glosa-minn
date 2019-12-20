package guia

import grails.compiler.GrailsCompileStatic
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

	static hasMany = [itens: Item]

	static constraints = {
	}

	static embedded = ['dadosBeneficiario']
}
