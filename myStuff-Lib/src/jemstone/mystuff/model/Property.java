package jemstone.mystuff.model;

import java.io.PrintWriter;
import java.util.List;

import jemstone.util.NameOrderedList;
import jemstone.util.Printer;

/**
 * @author Peter
 * @version 1.0
 * @created 08-Jun-2011 21:25:47
 */
public class Property extends Item {
  public enum F { Property, Address, LandArea, LandValue, Buildings, Vehicles, Items };
  
  /** The address of this property */
  private Address address;
  
  /** The land area of this property */
  private double landArea = Double.NaN;
  
  /** The land value of this property */
  private double landValue = Double.NaN;
  
  /** A list of buildings on this property */
  private List<Building> buildings = new NameOrderedList<Building>(); 
  
  /** A list of vehicles located at this property */
  private List<Vehicle> vehicles = new NameOrderedList<Vehicle>(); 
  
  /** A list of items located at this property */
  private List<Item> items = new NameOrderedList<Item>(); 

  Property(int id) {
    super(id);
  }

  public Address getAddress() {
    if (address == null) {
      address = new Address();
    }
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public double getLandArea() {
    return landArea;
  }

  public void setLandArea(double landArea) {
    this.landArea = landArea;
  }

  public double getLandValue() {
    return landValue;
  }

  public void setLandValue(double landValue) {
    this.landValue = landValue;
  }

  public Building add(Building building) {
    buildings.add(building);
    return building;
  }

  public Vehicle add(Vehicle vehicle) {
    vehicles.add(vehicle);
    return vehicle;
  }

  public Item add(Item item) {
    items.add(item);
    return item;
  }

  public List<Building> getBuildings() {
    return buildings;
  }
  
  public List<Vehicle> getVehicles() {
    return vehicles;
  }
  
  public List<Item> getItems() {
    return items;
  }
  
  @Override
  public void print(PrintWriter out, int depth) {
    Printer.print(out, this, depth);
  }
}