package guia

class DadosBeneficiario {
	String matricula
	String atendimentoRn
	String nomeBeneficiario
	String numero_guia_internacao

	static belongsTo = Guia
}
