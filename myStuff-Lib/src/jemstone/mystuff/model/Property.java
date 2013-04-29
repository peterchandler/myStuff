package jemstone.mystuff.model;

import java.io.PrintWriter;
import java.util.List;

import jemstone.model.Entity;
import jemstone.model.HasDescription;
import jemstone.model.HasName;
import jemstone.util.NameOrderedList;
import jemstone.util.Printer;

/**
 * @author Peter
 * @version 1.0
 * @created 08-Jun-2011 21:25:47
 */
public class Property extends Entity implements HasName, HasDescription {
  public enum F { Property, Name, Description, Address, Buildings, Vehicles, Items };
  
  /** The name of the property */
  private String name;

  /** A short description of the property */
  private String description;
  
  /** The address of this property */
  private Address address;
  
  /** A list of buildings on this property */
  private List<Building> buildings = new NameOrderedList<Building>(); 
  
  /** A list of vehicles located at this property */
  private List<Vehicle> vehicles = new NameOrderedList<Vehicle>(); 
  
  /** A list of items located at this property */
  private List<Item> items = new NameOrderedList<Item>(); 

  Property(int id) {
    super(id);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
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