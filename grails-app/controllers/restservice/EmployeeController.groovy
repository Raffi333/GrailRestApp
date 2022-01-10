package restservice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils
import grails.converters.*
import grails.gorm.transactions.Transactional

import javax.servlet.http.HttpServletRequest

@Transactional(readOnly = true)
class EmployeeController {


    def readAll() {
        def list = Employee.list()

        list.each { it.userID = "***" }

        if (list) {
            withFormat {

                xml {
                    render list as XML
                }

                json {
                    def jsonify = list as JSON
                    jsonify.prettyPrint = true
                    render jsonify
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
