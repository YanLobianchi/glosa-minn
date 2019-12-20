package guia

import grails.compiler.GrailsCompileStatic
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import item.Item

@CompileStatic(TypeCheckingMode.SKIP)
@GrailsCompileStatic
abstract class Guia<I extends Item> {
	Guia guiaConciliada
	String lote
	String numeroGuiaPrestador
	String numeroSolicitacaoInternacao
	String numeroGuiaOperadora
	String senha
	DadosBeneficiario dadosBeneficiario
	List<I> itens = []

	static hasMany = [itens: Item]

	static constraints = {
		guiaConciliada nullable: true
	}

	static embedded = ['dadosBeneficiario']
}
