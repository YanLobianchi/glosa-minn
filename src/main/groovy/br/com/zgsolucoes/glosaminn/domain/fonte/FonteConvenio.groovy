package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.domain.dto.DtoGuiaGenerica;

abstract class FonteConvenio {

	void preProcesseConteudoArquivo() {}

	void processeConteudoArquivo() {}

	static List<DtoGuiaGenerica> retorneGuiasDoArquivo() { return []}

}
