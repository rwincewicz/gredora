class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                
        "/$id?"(controller: "GredoraObject", action: "show")
        "/$id?/datastream/$dsId?"(controller: "GredoraDatastream", action: "show")
        "/gredoraDatastream/list/$id?"(controller: "GredoraDatastream", action: "list")

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
