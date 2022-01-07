package restservice

class BootStrap {

    def init = { servletContext ->


        10.times {
            new Employee(name: "Hs",surname: "Va",username: "M",userID: "AAA").save()
        }

    }
    def destroy = {
    }
}
