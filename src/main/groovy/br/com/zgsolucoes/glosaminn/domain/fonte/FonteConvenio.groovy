package br.com.zgsolucoes.glosaminn.domain.fonte

import guia.GuiaConvenio

abstract class FonteConvenio {
	String caminhoArquivo
	FonteConvenio(String caminhoArquivo) {this.caminhoArquivo = caminhoArquivo}

	void preProcesseConteudoArquivo() {}

	List<GuiaConvenio> processeConteudoArquivo() {}
}
