package demo

import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED
import static javax.servlet.http.HttpServletResponse.SC_OK
import spock.lang.Specification
import spock.lang.Unroll
import grails.test.mixin.TestFor

@TestFor(StudentController)
class StudentControllerAllowedMethodsSpec extends Specification {

    @Unroll
    def "StudentController.save does not accept #method requests"(String method) {
        when:
        request.method = method
        controller.save()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'GET', 'PUT']
    }

    def "StudentController.save accepts POST requests"() {
        when:
        request.method = 'POST'
        controller.save()

        then:
        response.status == SC_OK
    }
}
