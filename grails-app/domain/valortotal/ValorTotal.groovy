package valortotal


import guia.GuiaHospital

class ValorTotal {
	BigDecimal valorProcedimentos = 0
	BigDecimal valorDiarias = 0
	BigDecimal valorTaxasAlugueis = 0
	BigDecimal valorMateriais = 0
	BigDecimal valorMedicamentos = 0
	BigDecimal valorOPME = 0
	BigDecimal valorGasesMedicinais = 0
	BigDecimal valorTotalGeral = 0

	static belongsTo = GuiaHospital

	Map toMap() {
		[
				valorProcedimentos  : this.valorProcedimentos,
				valorDiarias        : this.valorDiarias,
				valorTaxasAlugueis  : this.valorTaxasAlugueis,
				valorMateriais      : this.valorMateriais,
				valorMedicamentos   : this.valorMedicamentos,
				valorOPME           : this.valorOPME,
				valorGasesMedicinais: this.valorGasesMedicinais,
				valorTotalGeral     : this.valorTotalGeral,
		]
	}

}
