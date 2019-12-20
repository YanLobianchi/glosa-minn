package fonte

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteConvenioGlosaMaxx
import guia.GuiaConvenio
import item.ItemConvenio
import spock.lang.Specification

class FonteConvenioGlosaMaxxTests extends Specification{
    FonteConvenioGlosaMaxx fonte

    void 'testa processamento de arquivo Convenio GlosaMax'(){
        given:
        String nomeArquivo = 'resources/convenio/glosaMaxx/pagamento_glosamax_1.csv'
        fonte = new FonteConvenioGlosaMaxx(nomeArquivo)

        when:
        List<GuiaConvenio> guiasArquivo = fonte.processeConteudoArquivo()

        then:
        guiasArquivo.size() == 2

        guiasArquivo[0].dadosBeneficiario.nomeBeneficiario == 'CLERISVALDO SILVA SOUZA'
        guiasArquivo[0].dadosBeneficiario.matricula == '106261029'
        guiasArquivo[0].senha == '201600011781'
        guiasArquivo[0].lote == '8550'
        guiasArquivo[0].numeroGuiaPrestador == '173284000000'
        guiasArquivo[0].itens.size() == 29

        ItemConvenio primeiroItemPrimeiraGuia = guiasArquivo[0].itens.first()
        primeiroItemPrimeiraGuia.codigoItem == '20103212'
        primeiroItemPrimeiraGuia.descricaoItem == 'Disturbios circulatorios arterio-venosos e linfaticos'
        primeiroItemPrimeiraGuia.quantidade == 1
        primeiroItemPrimeiraGuia.valorUnitario == 13.16
        primeiroItemPrimeiraGuia.valorTotal == 13.16
        primeiroItemPrimeiraGuia.valorPago == 0.16
        primeiroItemPrimeiraGuia.valorGlosa == 13
        primeiroItemPrimeiraGuia.codigoGlosa == '2666'
        ItemConvenio ultimoItemPrimeiraGuia = guiasArquivo[0].itens.last()
        ultimoItemPrimeiraGuia.codigoItem == '90174569'
        ultimoItemPrimeiraGuia.descricaoItem == 'PANTOZOL - 4 MG/ML PO LIOF INJ CX FA VD INC+AMP DIL X 10 ML'
        ultimoItemPrimeiraGuia.quantidade == 1
        ultimoItemPrimeiraGuia.valorUnitario == 114.25
        ultimoItemPrimeiraGuia.valorTotal == 114.25
        ultimoItemPrimeiraGuia.valorPago == 112.08
        ultimoItemPrimeiraGuia.valorGlosa == 2.17
        ultimoItemPrimeiraGuia.codigoGlosa == '2666'

        guiasArquivo[0].valorGlosa == 131.74
        guiasArquivo[0].valorApresentado == 1517.99
        guiasArquivo[0].valorPago == 1386.25

        guiasArquivo[1].dadosBeneficiario.nomeBeneficiario == 'MARCOS ALVES DA SILVA'
        guiasArquivo[1].dadosBeneficiario.matricula == '106208454'
        guiasArquivo[1].senha == '201600024428'
        guiasArquivo[1].lote == '8550'
        guiasArquivo[1].numeroGuiaPrestador == '173175000000'
        guiasArquivo[1].itens.size() == 215
        ItemConvenio primeiroItemSegundaGuia = guiasArquivo[1].itens.first()
        primeiroItemSegundaGuia.codigoItem == '40301630'
        primeiroItemSegundaGuia.descricaoItem == 'Creatinina - pesquisa e/ou dosagem'
        primeiroItemSegundaGuia.quantidade == 1
        primeiroItemSegundaGuia.valorUnitario == 5.74
        primeiroItemSegundaGuia.valorTotal == 5.74
        primeiroItemSegundaGuia.valorPago == 1.15
        primeiroItemSegundaGuia.valorGlosa == 4.59
        primeiroItemSegundaGuia.codigoGlosa == '2666'
        ItemConvenio ultimoItemSegundaGuia = guiasArquivo[1].itens.last()
        ultimoItemSegundaGuia.codigoItem == '70222800'
        ultimoItemSegundaGuia.descricaoItem == 'EQUIPO INTRAFIX PRIMELINE AIR IL SLIP 401413P'
        ultimoItemSegundaGuia.quantidade == 2
        ultimoItemSegundaGuia.valorUnitario == 61.49
        ultimoItemSegundaGuia.valorTotal == 122.98
        ultimoItemSegundaGuia.valorPago == 122.98
        ultimoItemSegundaGuia.valorGlosa == 0
        ultimoItemSegundaGuia.codigoGlosa == ''

        guiasArquivo[1].valorGlosa == 1721.79
        guiasArquivo[1].valorApresentado == 8744.69
        guiasArquivo[1].valorPago == 7022.90
    }
}
