package restservice

import grails.core.GrailsApplication
import groovy.sql.Sql
import org.apache.catalina.core.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope


class Employee {

    String name
    String surname
    String username
    String userID


    static constraints = {

    }

    void setUserID(String userID) {
        if (userID == "***") {
            this.userID = "***"
        } else if (this.userID == null) {
            long l = (findAll([sort: "id", order: 'desc', max: 1])[0]?.id ?: 0) + 1
            this.userID = l + "_" + UUID.randomUUID().toString()
        }
    }

    void setUsername(String username) {
        this.username = username
        if (this.userID == null) {
            long l = (findAll([sort: "id", order: 'desc', max: 1])[0]?.id ?: 0) + 1
            this.userID = l + "_" + UUID.randomUUID().toString()
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
