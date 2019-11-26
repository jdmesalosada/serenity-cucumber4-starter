package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SignUp implements Task {

    private final Object userInfo;

    public SignUp(Object userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(Object userInfo) {
        return instrumented(SignUp.class, userInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.can(   CallAnApi.at("http://localhost:5000"));
        actor.attemptsTo(
                Post.to("/api/users").with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                )
        );

    }
}
