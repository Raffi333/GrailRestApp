package restservice

class UrlMappings {

    static mappings = {

        "/api/$userID?/$format?" {
            controller = 'employee'
            action = [
                    POST: 'create',
                    GET : 'read',
                    PUT : 'update',
                    DELETE : 'delete',
            ]
        }

         "/api/all/$format?" {
            controller = 'employee'
            action = "readAll"
        }



        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
