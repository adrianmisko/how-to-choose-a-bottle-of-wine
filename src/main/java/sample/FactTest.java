package sample;

import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FactTest {

    public static void main(String[] args) throws DroolsParserException,
            IOException {
        FactTest factTest = new FactTest();
        factTest.executeMessage();
    }

    public void executeMessage() throws IOException,
            DroolsParserException {
        KieServices ks = KieServices.Factory.get();
        BasicConfigurator.configure();
        Logger.getLogger(FactTest.class).setLevel(Level.OFF);
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession session = kContainer.newKieSession("ksession-rules");

        Fact fact = new Fact();
        fact.text = "Personal use";
        session.insert(fact);

        session.fireAllRules();
    }

}