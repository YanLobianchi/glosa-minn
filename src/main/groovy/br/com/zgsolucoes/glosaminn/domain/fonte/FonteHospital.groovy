package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.domain.dto.DtoGuiaGenerica;

abstract class FonteHospital {

	void preProcesseConteudoArquivo(String caminhoArquivo) {}

	void processeConteudoArquivo() {}

	static List<DtoGuiaGenerica> retorneGuiasDoArquivo() { return [] }
}
