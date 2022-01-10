package restservice


import grails.converters.JSON
import grails.converters.XML
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class EmployeeController {


    def readAll() {

        def list = Employee.list()

        list.each { it.userID = "***" }

        if (list) {
            withFormat {

                //build  xml object
//                xml {
//                    render(contentType: "text/xml") {
//                        AllEmployee() {
//                            for (l in list) {
//                                employee(id: l.id) {
//                                    name(name: l.name)
//                                    surname(surname: l.surname)
//                                    username(username: l.username)
//                                }
//                            }
//                        }
//                    }
//                }

                //auto build xml object
                xml {
                    render list as XML
                }
                json {
                    def jsonify = list as JSON
                    jsonify.prettyPrint = true
                    render jsonify
//                    render (['success':'ok',data:list] as JSON)
                }
                text {
                    StringBuilder stringBuilder = new StringBuilder("")
                    list.each {
                        stringBuilder.append("  NAME-> " + it.name)
                        stringBuilder.append(":  SURNAME-> " + it.surname)
                        stringBuilder.append(":  USERNAME-> " + it.username)
                        stringBuilder.append("<br>")
                    }
                    render(stringBuilder.toString())
                }
                html {
                    render {
                        list.each { H3(it, ["style": "font-family:Monospace"]) }
                    }
                }
            }

            //client test
//            def url = new URL("http://localhost:8080/api/1_5e5a2dc4-cda1-41cf-be94-583472c77408")
//            def conn = url.openConnection()
//            conn.addRequestProperty("accept","application/json")
//            def artist=conn.content
//            println "Artist Name = ${artist}"

        } else response.sendError 404
    }


    //C
    @Transactional
    def create() {
        Employee employee = new Employee(name: params.name, surname: params.surname, username: params.username).save()
        render("USERNAME= ${employee.username} AND USERID= ${employee.userID}")
    }

    //R
    @Transactional
    def read(String userID) {
        Employee employee = Employee.findByUserID(userID)

        if (employee) {
            withFormat {
                xml { render(employee as XML) }
                json {
                    def jsonify = employee as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                }
            }
        } else response.sendError 404
    }

    //U
    @Transactional
    def update(String userID) {
        Employee employee = Employee.findByUserID(userID)
        if (employee) {
            if (params.name) {
                employee.name = params.name
            }
            if (params.surname) {
                employee.surname = params.surname
            }
            if (params.username) {
                employee.username = params.username
            }
            employee.save()

            withFormat {
                xml { render(employee as XML) }
                json {
                    def jsonify = employee as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                }
            }
        } else response.sendError 404
    }


    //D
    @Transactional
    def delete(String userID) {
        Employee employee = Employee.findByUserID(userID)
        if (employee) {
            String empData = employee.toString()
            employee.delete()
            render(empData + " =>DELETED")
        } else response.sendError 404
    }

}
