package carcheck.search;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CarTaxCheckPage {

    public static final Target NOT_FOUND =  Target.the("not found dialogue").locatedBy("//*[@id=\"m\"]/div[2]/div[12]/div/div/dl/div/dd[1]");

    public static final Target MAKE =  Target.the("car make").locatedBy("//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[2]");

    public static final Target MODEL =  Target.the("car model").locatedBy("//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[3]");

    public static final Target COLOUR =  Target.the("car colour").locatedBy("//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[4]");

    public static final Target YEAR =  Target.the("car year").locatedBy("//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[5]");


    public static String getMake(Actor actor){
       return MAKE.resolveFor(actor).find(By.tagName("dd")).getText();
    }

    public static String getModel(Actor actor){
        return MODEL.resolveFor(actor).find(By.tagName("dd")).getText();
    }

    public static String getColour(Actor actor){
        return COLOUR.resolveFor(actor).find(By.tagName("dd")).getText();
    }

    public static String getYear(Actor actor){
        return YEAR.resolveFor(actor).find(By.tagName("dd")).getText();
    }
}