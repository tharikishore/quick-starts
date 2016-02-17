package angular.spa

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view: 'index')
        "/"(view: 'spa')
        "500"(view: '/application/serverError')
        "404"(view: '/application/notFound')
    }
}