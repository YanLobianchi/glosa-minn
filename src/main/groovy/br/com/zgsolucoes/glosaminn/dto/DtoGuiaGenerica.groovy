package br.com.zgsolucoes.glosaminn.dto

class DtoGuiaGenerica {
    String lote
    String numeroGuiaPrestador
    String nomeGuiaPrestador
    BigDecimal valorPagoGuia
    BigDecimal valorApresentadoGuia
    BigDecimal valorGlosadoGuia
    List<DtoItemGenerico> itensDaGuia
}
