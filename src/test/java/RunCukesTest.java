import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Command to run maven, cucumber tests from command prompt.
 * mvn clean test -Dcucumber.options="src/test/resources --tags @@Your_tag"
 * or
 * mvn test -Dcucumber.options="src/test/resources"
 * or
 * $ mvn clean install
 * $ mvn -Dtest=RunnerTest test
 */

/**
 *  mvn test  - runs Cucumber Features using Cucumber’s JUnit Runner. The @RunWith (Cucumber.class) annotation on the TestRunner class tells JUnit to kick off Cucumber.
 *  Cucumber run time parses the command-line options to know what Feature to run, where the Glue Code lives, what plugins to use, and so on.
 * On the other hand, if you run test from an IDE when you use the JUnit Runner, these options are generated from the @CucumberOptions annotation on your test.
 */
/*
* Glue Option helps cucumber to locate the step definition file. In glue option, we basically specify the package (see name of the root package of this project
*  to load glue code (step definitions or hooks) from.
* When no glue is provided, Cucumber will use the package of the annotated class. For example, if the annotated class is com.example.Runner
* then glue is assumed to be located in com.example.
* */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        glue = {"stepdefs"}
        )
//@CucumberOptions(features = {"src/test/resources"})
public class RunCukesTest {

}
