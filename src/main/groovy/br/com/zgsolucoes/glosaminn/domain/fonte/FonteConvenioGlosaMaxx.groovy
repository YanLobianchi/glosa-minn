package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.utils.CSVReader
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import guia.DadosBeneficiario
import guia.GuiaConvenio
import item.ItemConvenio

import java.text.SimpleDateFormat

@CompileStatic(TypeCheckingMode.SKIP)
class FonteConvenioGlosaMaxx extends FonteConvenio{
    List<GuiaConvenio> guiasArquivo
    List<Map<String,String>> arquivoPreProcessado

    FonteConvenioGlosaMaxx(){
        guiasArquivo = new ArrayList<GuiaConvenio>()
    }
    void preProcesseConteudoArquivo(String caminhoArquivo) {
        File arquivoConvenio = new File(caminhoArquivo)
        arquivoPreProcessado  = CSVReader.read(arquivoConvenio)
    }

    void processeConteudoArquivo() {
        List<ItemConvenio> listaItens = new ArrayList<ItemConvenio>()
        for(Map<String,String> tupla: arquivoPreProcessado){
            ItemConvenio dtoItemGlosaMaxx = new ItemConvenio()
            dtoItemGlosaMaxx.lote = tupla['lote']
            dtoItemGlosaMaxx.numeroGuiaPrestador = tupla['numeroGuiaPrestador']
            dtoItemGlosaMaxx.numeroSolicitanteInternacao = tupla['numeroSolicitacaoInternacao']
            dtoItemGlosaMaxx.numeroGuiaOperadora = tupla['numeroGuiaOperadora']
            dtoItemGlosaMaxx.senha = tupla['senha']
            dtoItemGlosaMaxx.matricula = tupla['matricula']
            dtoItemGlosaMaxx.rn = tupla['RN']
            dtoItemGlosaMaxx.nomeGuiaPrestador = tupla['nome']

            String strHoraInicio = tupla['horaInicial']
            String strHoraFinal = tupla['horaFinal']
            String strData = tupla['dataExecucao']
            def patternDateTime = "yyyy-MM-dd"
            Date data = new SimpleDateFormat(patternDateTime).parse(strData)
            dtoItemGlosaMaxx.dataExecucao = data
            dtoItemGlosaMaxx.horaInicial = strHoraInicio
            dtoItemGlosaMaxx.horaFinal = strHoraFinal

            dtoItemGlosaMaxx.codigoTabela = tupla['codigoTabela']
            dtoItemGlosaMaxx.codigoItem = tupla['codigoProcedimento']
            dtoItemGlosaMaxx.descricaoItem = tupla['descricaoProcedimento']
            dtoItemGlosaMaxx.quantidade = tupla['quantidadeExecutada'].toInteger()
            dtoItemGlosaMaxx.valorUnitario = tupla['valorUnitario'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.valorTotal = tupla['valorApresentado'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.valorPago = tupla['valorPago'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.valorGlosa = tupla['valorGlosa'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.codigoGlosa = tupla['motivoGlosa']

            listaItens.add(dtoItemGlosaMaxx)
        }
        for (Map.Entry<String, List<ItemConvenio>> guia: listaItens.groupBy {it.numeroGuiaPrestador}){
            DadosBeneficiario dadosBeneficiario = new DadosBeneficiario()
            dadosBeneficiario.matricula = guia.value[0].matricula
            dadosBeneficiario.atendimentoRn = guia.value[0].rn
            dadosBeneficiario.nomeBeneficiario = guia.value[0].nomeGuiaPrestador
            dadosBeneficiario.numero_guia_internacao = guia.value[0].numeroSolicitanteInternacao

            GuiaConvenio dtoGuiaGlosaMaxx = new GuiaConvenio()
            dtoGuiaGlosaMaxx.numeroGuiaPrestador = guia.value[0].numeroGuiaPrestador
            dtoGuiaGlosaMaxx.lote = guia.value[0].lote
            dtoGuiaGlosaMaxx.itens = guia.value
            dtoGuiaGlosaMaxx.numeroGuiaOperadora = guia.value[0].numeroGuiaOperadora
            dtoGuiaGlosaMaxx.senha = guia.value[0].senha
            dtoGuiaGlosaMaxx.nomeConvenio = 'GlosaMaxx'
            dtoGuiaGlosaMaxx.dadosBeneficiario = dadosBeneficiario

            dtoGuiaGlosaMaxx.valorApresentado = (BigDecimal)guia.value.sum {it.valorTotal}
            dtoGuiaGlosaMaxx.valorPago = (BigDecimal)guia.value.sum {it.valorPago}
            dtoGuiaGlosaMaxx.valorGlosa = (BigDecimal)guia.value.sum {it.valorGlosa}

            guiasArquivo.add(dtoGuiaGlosaMaxx)
        }
        guiasArquivo
    }
}
