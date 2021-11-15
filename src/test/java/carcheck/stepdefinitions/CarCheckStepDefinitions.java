package carcheck.stepdefinitions;

import dto.CarDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import carcheck.navigation.NavigateTo;
import carcheck.search.CarTaxCheckPage;
import carcheck.search.LookForInformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

public class CarCheckStepDefinitions {

    private List<String> registrations = new ArrayList<>();
    private Map<String,CarDto> cars = new HashMap<>();

    @Given("input file with registration numbers {string}")
    public void registrationNumberExtracted(String inputFile) throws IOException {

        Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}");

        String readString = Files.readString(Paths.get(inputFile));

        Matcher matcher = pattern.matcher(readString);

        while (matcher.find()) {
            registrations.add(matcher.group().replace(" ",""));
        }

    }


    @When("{actor} go to the car check homepage")
    public void goToCarCheckPage(Actor actor){
        actor.wasAbleTo(NavigateTo.theCarCheckHomePage());
    }


    @When("{actor} extracts car details")
    public void searchesForCarData(Actor actor) {

        for (String reg: registrations) {
            actor.attemptsTo(
                    LookForInformation.reg(reg)

            );


            if(CarTaxCheckPage.NOT_FOUND.isVisibleFor(actor)){
                goToCarCheckPage(actor);
                continue;
            }


            String make = CarTaxCheckPage.getMake(actor).toLowerCase().trim();
            String model = CarTaxCheckPage.getModel(actor).toLowerCase().trim();
            String colour = CarTaxCheckPage.getColour(actor).toLowerCase().trim();
            String year = CarTaxCheckPage.getYear(actor).toLowerCase().trim();

            CarDto carDto = new CarDto(reg.toLowerCase().trim(), make, model, colour, year);
            cars.put(reg.toLowerCase().trim(),carDto);

            goToCarCheckPage(actor);
        }

    }

    @Then("the car data is equal to {string}")
    public void carDataShouldMatchOutput(String outputFile) throws IOException {
        Map<String, CarDto> outputCars = Files.lines(Paths.get(outputFile)).skip(1).map(car -> {
            String[] carData = car.split(",");
            return new CarDto(carData[0].toLowerCase().trim(), carData[1].toLowerCase().trim(), carData[2].toLowerCase().trim(), carData[3].toLowerCase().trim(), carData[4].toLowerCase().trim());
        }).collect(Collectors.toMap(CarDto::getRegistration, carDto -> carDto));

        for (Map.Entry<String, CarDto> entry : outputCars.entrySet()) {
            assertThat(cars, hasEntry(entry.getKey(), entry.getValue()));
        }

    }
}
