package br.com.glosa.minn.guia

import grails.gorm.transactions.Transactional
import guia.Guia
import item.Item
import javassist.NotFoundException

import java.util.stream.Collectors

@Transactional
abstract class AbstractGuiaService<G extends Guia> {

	@Transactional(readOnly = true)
	Guia get(final Long id) throws NotFoundException {
		if (!Guia.exists(id)) {
			throw new NotFoundException("Guia de id: '$id' não existe")
		}

		final Guia guia = Guia.get(id)
		return guia
	}

	G create(final G guia) {
		final Guia guiaSalva = guia.save(failOnError: true)
		return (G) guiaSalva
	}


	G edit(final Long id, final G guiaEditada) {
		final Guia guiaNoBanco = get(id)
		amarraRelacionamentosGuia(guiaNoBanco, guiaEditada)
		final Guia guiaSalva = guiaNoBanco.save(failOnError: true)
		return (G) guiaSalva
	}

	void delete(final Long id) {
		final Guia guia = get(id)
		guia.delete(failOnError: true)
	}

	void amarraRelacionamentosGuiaAbstrata(final G guiaNoBanco, final G guiaEditada) {
		guiaNoBanco.lote = guiaEditada.lote
		guiaNoBanco.numeroGuiaPrestador = guiaEditada.numeroGuiaPrestador
		guiaNoBanco.numeroSolicitacaoInternacao = guiaEditada.numeroSolicitacaoInternacao
		guiaNoBanco.numeroGuiaOperadora = guiaEditada.numeroGuiaOperadora
		guiaNoBanco.senha = guiaEditada.senha
		guiaNoBanco.itens.clear()
		guiaNoBanco.itens.addAll(
				guiaEditada.itens.stream()
						   .map({ final Item item ->
							   return Item.exists(item.id) ? Item.get(item.id) : item
						   }).collect(Collectors.<Item> toSet())
		)
	}

	abstract void amarraRelacionamentosGuia(final G guiaNoBanco, final G guiaEditada)

}
