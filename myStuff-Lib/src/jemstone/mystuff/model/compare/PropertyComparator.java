package jemstone.mystuff.model.compare;

import jemstone.mystuff.model.Building;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Property;
import jemstone.mystuff.model.Property.F;
import jemstone.mystuff.model.Vehicle;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.ListComparator;

public class PropertyComparator extends ItemComparator<Property> {
  private AddressComparator addressComparator;
  private ListComparator<Building> buildingComparator;
  private ListComparator<Vehicle> vehicleComparator;
  private ListComparator<Item> itemComparator;

  public PropertyComparator(boolean compareId) {
    super(compareId);
    
    addressComparator = new AddressComparator(compareId);
    buildingComparator = new ListComparator<Building>(compareId, F.Buildings.name(), new BuildingComparator(compareId));
    vehicleComparator = new ListComparator<Vehicle>(compareId, F.Vehicles.name(), new VehicleComparator(compareId));
    itemComparator = new ListComparator<Item>(compareId, F.Items.name(), new ItemComparator<Item>(compareId));
    
    addChild(addressComparator);
    addChild(buildingComparator);
    addChild(vehicleComparator);
    addChild(itemComparator);
  }

  @Override
  public boolean equals(Property o1, Property o2) throws CompareException {
    if (checkNull(F.Property, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(F.LandArea, o1.getLandArea(), o2.getLandArea());
      result &= equals(F.LandValue, o1.getLandValue(), o2.getLandValue());
      result &= addressComparator.equals(o1.getAddress(), o2.getAddress());
      result &= buildingComparator.equals(o1.getBuildings(), o2.getBuildings());
      result &= vehicleComparator.equals(o1.getVehicles(), o2.getVehicles());
      result &= itemComparator.equals(o1.getItems(), o2.getItems());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
