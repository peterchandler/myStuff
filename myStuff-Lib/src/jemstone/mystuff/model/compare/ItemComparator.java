package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Photo;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.EntityComparator;
import jemstone.util.compare.ListComparator;

public class ItemComparator extends EntityComparator<Item> implements XmlConstants {
  private ListComparator<Photo> photoComparator;
  
  public ItemComparator(boolean compareId) {
    super(compareId);
    photoComparator = new ListComparator<Photo>(compareId, PHOTOS, new PhotoComparator(compareId));
  }

  @Override
  public boolean equals(Item o1, Item o2) throws CompareException {
    if (checkNull(ITEM, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(NAME, o1.getName(), o2.getName());
      result &= equals(DESCRIPTION, o1.getDescription(), o2.getDescription());
      result &= equals(DATE, o1.getDate(), o2.getDate());
      result &= equals(AMOUNT, o1.getAmount(), o2.getAmount());
      result &= equals(CATEGORY, o1.getCategory(), o2.getCategory());
      result &= photoComparator.equals(o1.getPhotos(), o2.getPhotos());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
