package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.IdFactory;
import jemstone.mystuff.model.IdFactory.F;
import jemstone.util.compare.BaseComparator;
import jemstone.util.compare.CompareException;

public class IdFactoryComparator extends BaseComparator<IdFactory> implements XmlConstants {
  private boolean strict = false;

  public IdFactoryComparator(boolean compareId, boolean strict) {
    super(compareId);
    this.strict = strict;
  }

  @Override
  public boolean equals(IdFactory o1, IdFactory o2) throws CompareException {
    if (checkNull(F.IdFactory, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = true;
      result &= equals(F.NextCategoryId, o1.getNextCategoryId(), o2.getNextCategoryId());
      result &= equals(F.NextItemId, o1.getNextItemId(), o2.getNextItemId());
      result &= equals(F.NextPhotoId, o1.getNextPhotoId(), o2.getNextPhotoId());
      result &= equals(F.NextPropertyId, o1.getNextPropertyId(), o2.getNextPropertyId());

      if (strict) {
      }

      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s with %s", getClassName(o1), getClassName(o2));
      throw e;
    }
  }
}
