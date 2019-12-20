package br.com.zgsolucoes.glosaminn.dto

class DtoGuiaGenerica {
	String lote
	String numeroGuiaPrestador
	String numero
	String senha
	String nomeGuiaPrestador
	String numeroGuiaOperadora
	String matricula
	String nome
	String atendimentoRN
	BigDecimal valorPagoGuia
	BigDecimal valorApresentadoGuia
	BigDecimal valorGlosadoGuia
	List<DtoItemGenerico> itens
}
