package glosa.minn

import conciliacao.ConciliacaoService
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
