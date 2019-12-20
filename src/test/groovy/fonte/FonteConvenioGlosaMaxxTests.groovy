package fonte

import br.com.zgsolucoes.glosaminn.domain.fonte.FonteConvenioGlosaMaxx

class FonteConvenioGlosaMaxxTests extends GroovyTestCase{
    FonteConvenioGlosaMaxx fonte

    void setUp(){
        fonte = new FonteConvenioGlosaMaxx()
    }

    void testaImportacaoArquivo(){
        String nomeArquivo = 'resources/convenio/glosaMaxx/pagamento_glosamax_1.csv'
        fonte.preProcesseConteudoArquivo(nomeArquivo)
        fonte.processeConteudoArquivo()
    }
}
