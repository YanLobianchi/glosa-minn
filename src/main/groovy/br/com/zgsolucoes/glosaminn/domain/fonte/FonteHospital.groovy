package br.com.zgsolucoes.glosaminn.domain.fonte

import guia.GuiaHospital

abstract class FonteHospital {

	void preProcesseConteudoArquivo(String caminhoArquivo) {}

	List<GuiaHospital> processeConteudoArquivo(String conteudoXml) {}
}