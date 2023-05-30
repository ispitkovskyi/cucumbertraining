import cucumber.api.junit.Cucumber;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

@Deprecated
public class TestRunner {
    public static void main(String [] args){
        JUnitCore.runClasses(Cucumber.class);
        JUnitCore.main(args);
    }
}
