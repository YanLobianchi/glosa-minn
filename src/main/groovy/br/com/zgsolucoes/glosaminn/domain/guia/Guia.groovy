package br.com.zgsolucoes.glosaminn.domain.guia

import br.com.zgsolucoes.glosaminn.domain.item.Item

abstract class Guia<I extends Item> {

	String lote
	String numeroGuiaPrestador
	String numeroSolicitacaoInternacao
	String numeroGuiaOperadora
	String senha
	String nomeBeneficiario
	String matricula
	String atendimentoRn
	List<I> itens
}
