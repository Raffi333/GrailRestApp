package restservice

class UrlMappings {

    static mappings = {

        "/api/$userID?/$format?" {
            controller = 'employee'
            action = [
                    POST  : 'create',
                    GET   : 'read',
                    PUT   : 'update',
                    DELETE: 'delete',
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


/*
"/music/$artistName/$albumTitle?/$songTitle?"{
controller = {
if(params.albumTitle && params.songTitle) return 'song'
else if(params.albumTitle) return 'album'
else return 'artist'
}
action = [GET:'show', PUT:'save', POST:'update', DELETE:'delete']
}
 */