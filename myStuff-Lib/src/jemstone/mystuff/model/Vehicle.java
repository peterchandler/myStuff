package jemstone.mystuff.model;

public class Vehicle extends Item {
  public enum F { Vehicle, Registration, Color, Make, Model, Year };
  
  /** The registration number (number plate) of the vehicle */
  private String registration;
  
  /** The colour of the vehicle */
  private String color;
  
  /** The make or manufacturer e.g. Ford, Toyota */
  private String make;
  
  /** The model of the vehicle */
  private String model;
  
  /** The year the vehicle was manufactured */
  private int year;
  
  /** Constructed using {@link EntityManager#newVehicle()} */
  Vehicle(int id) {
    super(id);
  }

  public String getRegistration() {
    return registration;
  }

  public void setRegistration(String registration) {
    this.registration = registration;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
