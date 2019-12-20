<div id="controllers" role="navigation">
	<h2>Conciliação</h2>
	<ul>
		<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
			<li class="controller">
				<g:link controller="${c.realizarConciliacao}">Realizar Conciliação</g:link>
			</li>
		</g:each>
	</ul>
</div>