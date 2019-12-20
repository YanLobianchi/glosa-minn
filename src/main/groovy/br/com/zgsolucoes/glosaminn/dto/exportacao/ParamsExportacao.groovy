package br.com.zgsolucoes.glosaminn.dto.exportacao

class ExportacaoParams {
	List<Long> remessas
	TipoExportacao tipoExportacao
}

enum TipoExportacao {
	CSV, JSON
}
