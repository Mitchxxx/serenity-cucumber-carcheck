package carcheck.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable theCarCheckHomePage() {
        return Task.where("{0} opens the car check home page",
                Open.browserOn().the(CarTaxCheckHomePage.class));
    }
}
