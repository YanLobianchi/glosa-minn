package glosa.minn

import br.com.glosa.minn.conciliacao.ConciliacaoService
import org.springframework.stereotype.Component

@Component
class ConciliacaoController {

    ConciliacaoService conciliacaoService
    
    def index() {
    }

    def realizarConciliacao() {
        conciliacaoService.realizaImportacaoEConciliacao()
    }
}
