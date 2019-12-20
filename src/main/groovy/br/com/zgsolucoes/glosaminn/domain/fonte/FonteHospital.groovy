package br.com.zgsolucoes.glosaminn.domain.fonte;

abstract class FonteHospital {

	void preProcesseConteudoArquivo() {}

	void processeConteudoArquivo() {}

	static List<Object> retorneGuiasDoArquivo() { return [] }
}
