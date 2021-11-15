package dto;

import java.util.Objects;

public class CarDto {

    private String registration;
    private String make;
    private String model;
    private String color;
    private String year;

    public CarDto(String registration, String make, String model, String color, String year) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return Objects.equals(registration, carDto.registration) && Objects.equals(make, carDto.make) && Objects.equals(model, carDto.model) && Objects.equals(color, carDto.color) && Objects.equals(year, carDto.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, make, model, color, year);
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
