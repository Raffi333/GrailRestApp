package restservice

class BootStrap {

    def init = { servletContext ->


        100.times {
            new Employee(name: "name${(char) (it + 600)}", surname: "Va", username: "${it}M", userID: "AAA").save()
        }

    }
    def destroy = {
    }
}
