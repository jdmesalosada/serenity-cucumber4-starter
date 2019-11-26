package starter.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.TheValue;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import starter.tasks.SignUp;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class SignUpStepDefinitions {

    private Actor julian;

    @Given("Julian is an user that wants to manage their bank products")
    public void julian_is_an_user_that_wants_to_manage_their_bank_products() {
        julian = OnStage.theActorCalled("Julian");
    }

    @When("he sends the required information to sign up")
    public void he_sends_the_required_information_to_sign_up() {
        String registerUserInfo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        julian.attemptsTo(
                SignUp.withInfo(registerUserInfo)
        );
    }

    @Then("he should get a virtual account to manage their products")
    public void he_should_get_a_virtual_account_to_manage_their_products() {
        //with this the text that appears in the report is: An appropriate error message was returned
        julian.should(
                seeThatResponse("An appropriate error message was returned",
                        response -> response.statusCode(HttpStatus.SC_CREATED)
                ));

        //Then el codigo de respuesta should be (201)
        julian.should(
                seeThat("el codigo de respuesta", TheValue.of(LastResponse.received().answeredBy(julian).statusCode()),
                        equalTo(201))
        );
    }
}
