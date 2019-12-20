package glosa.minn

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic(TypeCheckingMode.SKIP)
class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?" {
			constraints {
				// apply constraints here
			}
		}

		delete "/$controller/$id(.$format)?"(action: "delete")
		get "/$controller(.$format)?"(action: "index")
		get "/$controller/$id(.$format)?"(action: "show")
		post "/$controller(.$format)?"(action: "save")
		put "/$controller/$id(.$format)?"(action: "update")
		patch "/$controller/$id(.$format)?"(action: "patch")

		"/"(view: "/index")
		"500"(view: '/error')
		"404"(view: '/notFound')

		get "/exportacao/json"(controller: 'exportacao', action: 'json')
		get "/exportacao/csvGuias"(controller: 'exportacao', action: 'csvGuias')
		get "/exportacao/csvItens"(controller: 'exportacao', action: 'csvItens')
	}
}
