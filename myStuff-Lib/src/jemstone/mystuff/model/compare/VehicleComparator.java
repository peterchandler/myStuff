package jemstone.mystuff.model.compare;

import jemstone.mystuff.model.Vehicle;
import jemstone.mystuff.model.Vehicle.F;
import jemstone.util.compare.CompareException;

public class VehicleComparator extends ItemComparator<Vehicle> {
  public VehicleComparator(boolean compareId) {
    super(compareId);
  }

  @Override
  public boolean equals(Vehicle o1, Vehicle o2) throws CompareException {
    if (checkNull(F.Vehicle, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(F.Color, o1.getColor(), o2.getColor());
      result &= equals(F.Make, o1.getMake(), o2.getMake());
      result &= equals(F.Model, o1.getModel(), o2.getModel());
      result &= equals(F.Registration, o1.getRegistration(), o2.getRegistration());
      result &= equals(F.Year, o1.getYear(), o2.getYear());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
