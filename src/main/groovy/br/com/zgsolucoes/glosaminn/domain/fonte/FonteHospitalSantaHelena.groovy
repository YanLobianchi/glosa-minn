package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.domain.dto.DtoGuiaGenerica

class FonteHospitalSantaHelena extends FonteHospital {

	void preProcesseConteudoArquivo(String caminhoArquivo) {

		String conteudoArquivo = new File(caminhoArquivo).text

		conteudoArquivo
	}

	void processeConteudoArquivo() {}

	static List<DtoGuiaGenerica> retorneGuiasDoArquivo() { return [] }
}
