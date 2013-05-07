package jemstone.mystuff.model.compare;

import jemstone.mystuff.model.Building;
import jemstone.mystuff.model.Building.F;
import jemstone.util.compare.CompareException;

public class BuildingComparator extends ItemComparator<Building> {
  public BuildingComparator(boolean compareId) {
    super(compareId);
  }

  @Override
  public boolean equals(Building o1, Building o2) throws CompareException {
    if (checkNull(F.Building, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(F.FloorArea, o1.getFloorArea(), o2.getFloorArea());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
