package fonte

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteConvenioGlosaMaxx
import guia.GuiaConvenio
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

        guiasArquivo[0].itens.size() == 29
        guiasArquivo[0].valorGlosa == 131.74
        guiasArquivo[0].valorApresentado == 1517.99
        guiasArquivo[0].valorPago == 1386.25

        guiasArquivo[1].itens.size() == 215
        guiasArquivo[1].valorGlosa == 1721.79
        guiasArquivo[1].valorApresentado == 8744.69
        guiasArquivo[1].valorPago == 7022.90
    }
}
