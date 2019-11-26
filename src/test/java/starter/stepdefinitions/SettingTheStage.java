package starter.stepdefinitions;

import cucumber.api.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class SettingTheStage {

    /**
     * The EnvironmentVariables field wraps the system properties and the properties defined in the serenity.properties
     * file. This is a convenient way to access system or environment properties. Serenity will inject it automatically
     * into a step definition class.
     */
    EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(
                /*You can also use the whereEveryoneCan method to provide additional abilities, e.g.
                This will provide actors who can both use WebDriver tasks and RestAssured tasks.
                Reference: https://serenity-bdd.github.io/theserenitybook/latest/screenplay-selenium-tasks.html
                 */
                OnlineCast.whereEveryoneCan(
                        CallAnApi.at("http://localhost:5000")
                )
        );
    }

}
