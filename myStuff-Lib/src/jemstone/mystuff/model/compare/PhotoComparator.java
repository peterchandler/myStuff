package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.Photo;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.EntityComparator;

public class PhotoComparator extends EntityComparator<Photo> implements XmlConstants {
  public PhotoComparator(boolean compareId) {
    super(compareId);
  }

  @Override
  public boolean equals(Photo o1, Photo o2) throws CompareException {
    if (checkNull("Photo", o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(NAME, o1.getName(), o2.getName());
      result &= equals(CAPTION, o1.getCaption(), o2.getCaption());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
