package br.com.glosa.minn.remessa

import grails.gorm.transactions.Transactional
import guia.GuiaHospital
import remessa.Remessa

@Transactional
class RemessaService {

	List<Remessa> list() {
		return Remessa.list()
	}

}
