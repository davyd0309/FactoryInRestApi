import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.dawydiuk.FactoryInRestApi.FactoryInRestApiApplication
import pl.dawydiuk.FactoryInRestApi.controller.MainController
import spock.lang.Specification

@SpringBootTest(classes = FactoryInRestApiApplication)
class LoadContextIT extends Specification{

    @Autowired
    private MainController mainController

    def "when context is loaded then all expected beans are created"() {
        expect: "the MainController is created"
        mainController
    }

}
