package br.com.zgsolucoes.glosaminn.domain.fonte

import br.com.zgsolucoes.glosaminn.domain.dto.DtoGuiaGenerica

class FonteConvenioGlosaMaxx extends FonteConvenio{
    List<DtoGuiaGenerica> guiasArquivo

    void preProcesseConteudoArquivo() {}

    void processeConteudoArquivo() {}

    static List<DtoGuiaGenerica> retorneGuiasDoArquivo() { return []}
}
