package jemstone.mystuff.model.compare;

import jemstone.mystuff.model.IdFactory;
import jemstone.util.compare.BaseComparator;
import jemstone.util.compare.CompareException;

public class IdFactoryComparator extends BaseComparator<IdFactory> {
  private boolean strict = false;

  public IdFactoryComparator(boolean compareId, boolean strict) {
    super(compareId);
    this.strict = strict;
  }

  @Override
  public boolean equals(IdFactory o1, IdFactory o2) throws CompareException {
    if (checkNull("IdFactory", o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = true;
      result &= equals("nextCategoryId", o1.getNextCategoryId(), o2.getNextCategoryId());

      if (strict) {
      }

      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s with %s", getClassName(o1), getClassName(o2));
      throw e;
    }
  }
}
