package glosa.minn

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic(TypeCheckingMode.SKIP)
class UrlMappings {

	static mappings = {
		"/api/$controller/$action?/$id?(.$format)?" {
			constraints {
				// apply constraints here
			}
		}

		delete "/api/$controller/$id(.$format)?"(action: "delete")
		get "/api/$controller(.$format)?"(action: "index")
		get "/api/$controller/$id(.$format)?"(action: "show")
		post "/api/$controller(.$format)?"(action: "save")
		put "/api/$controller/$id(.$format)?"(action: "update")
		patch "/api/$controller/$id(.$format)?"(action: "patch")

		"/"(view: "/index")
		"500"(view: '/error')
		"404"(view: '/notFound')
	}
}
