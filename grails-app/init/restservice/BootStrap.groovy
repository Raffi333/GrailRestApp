package restservice

class BootStrap {

    def init = { servletContext ->


        100.times {
            new Employee(name: "Hs",surname: "Va",username: "M",userID: "AAA").save()
        }

    }
    def destroy = {
    }
}
