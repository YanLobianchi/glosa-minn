package br.com.zgsolucoes.glosaminn.domain

import br.com.zgsolucoes.glosaminn.domain.guia.Guia

class Lote {
	String numeroLote
	List<? extends Guia> guias
}
