package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.dto.DtoGuiaGenerica
import br.com.zgsolucoes.glosaminn.dto.DtoGuiaGlosaMaxx
import br.com.zgsolucoes.glosaminn.dto.DtoItemGlosaMaxx
import br.com.zgsolucoes.glosaminn.utils.CSVReader
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import java.text.SimpleDateFormat

@CompileStatic(TypeCheckingMode.SKIP)
class FonteConvenioGlosaMaxx extends FonteConvenio{
    List<DtoGuiaGenerica> guiasArquivo
    List<Map<String,String>> arquivoPreProcessado

    FonteConvenioGlosaMaxx(){
        guiasArquivo = new ArrayList<DtoGuiaGlosaMaxx>()
    }
    void preProcesseConteudoArquivo(String caminhoArquivo) {
        File arquivoConvenio = new File(caminhoArquivo)
        arquivoPreProcessado  = CSVReader.read(arquivoConvenio)
    }

    void processeConteudoArquivo() {
        List<DtoItemGlosaMaxx> listaItens = new ArrayList<DtoItemGlosaMaxx>()
        for(Map<String,String> tupla: arquivoPreProcessado){
            DtoItemGlosaMaxx dtoItemGlosaMaxx = new DtoItemGlosaMaxx()
            dtoItemGlosaMaxx.lote = tupla['lote']
            dtoItemGlosaMaxx.numeroGuiaPrestador = tupla['numeroGuiaPrestador']
            dtoItemGlosaMaxx.numeroSolicitanteInternacao = tupla['numeroSolicitacaoInternacao']
            dtoItemGlosaMaxx.numeroGuiaOperadora = tupla['numeroGuiaOperadora']
            dtoItemGlosaMaxx.senha = tupla['senha']
            dtoItemGlosaMaxx.matricula = tupla['matricula']
            dtoItemGlosaMaxx.rn = tupla['RN']
            dtoItemGlosaMaxx.nomeGuiaPrestador = tupla['nome']

            String strDataInicio = tupla['dataExecucao']
            String strHoraInicio = tupla['horaInicial']
            String strHoraFinal = tupla['horaFinal']
            def patternDateTime = "yyyy-MM-dd HH:mm:ss"
            Date dataHoraInicial = new SimpleDateFormat(patternDateTime).parse(strDataInicio+ ' '+strHoraInicio)
            Date dataHoraFinal = new SimpleDateFormat(patternDateTime).parse(strDataInicio+ ' '+strHoraFinal)
            dtoItemGlosaMaxx.dataHoraInicial = dataHoraInicial
            dtoItemGlosaMaxx.dataHoraFinal = dataHoraFinal

            dtoItemGlosaMaxx.codigoTabela = tupla['codigoTabela']
            dtoItemGlosaMaxx.codigoItem = tupla['codigoProcedimento']
            dtoItemGlosaMaxx.descricaoProcedimento = tupla['descricaoProcedimento']
            dtoItemGlosaMaxx.quantidade = tupla['quantidadeExecutada']
            dtoItemGlosaMaxx.valorUnitario = tupla['valorUnitario'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.valorApresentado = tupla['valorApresentado'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.valorPago = tupla['valorPago'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.valorGlosado = tupla['valorGlosa'].replace(',','.').toBigDecimal()
            dtoItemGlosaMaxx.motivoGlosa = tupla['motivoGlosa']

            listaItens.add(dtoItemGlosaMaxx)
        }
        for (Map.Entry<String, List<DtoItemGlosaMaxx>> guia: listaItens.groupBy {it.numeroGuiaPrestador}){
            DtoGuiaGlosaMaxx dtoGuiaGlosaMaxx = new DtoGuiaGlosaMaxx()
            dtoGuiaGlosaMaxx.numeroGuiaPrestador = guia.value[0].numeroGuiaPrestador
            dtoGuiaGlosaMaxx.lote = guia.value[0].lote
            dtoGuiaGlosaMaxx.atendimentoRN = guia.value[0].rn
            dtoGuiaGlosaMaxx.itens = guia.value
            dtoGuiaGlosaMaxx.nome = guia.value[0].nomeGuiaPrestador
            dtoGuiaGlosaMaxx.matricula = guia.value[0].matricula
            dtoGuiaGlosaMaxx.numeroGuiaOperadora = guia.value[0].numeroGuiaOperadora
            dtoGuiaGlosaMaxx.numeroSolicitanteInternacao = guia.value[0].numeroSolicitanteInternacao
            dtoGuiaGlosaMaxx.senha = guia.value[0].senha

            guiasArquivo.add(dtoGuiaGlosaMaxx)
        }
        guiasArquivo
    }
}
