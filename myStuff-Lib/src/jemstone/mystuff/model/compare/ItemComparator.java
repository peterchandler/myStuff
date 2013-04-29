package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Item.F;
import jemstone.mystuff.model.Photo;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.EntityComparator;
import jemstone.util.compare.ListComparator;

public class ItemComparator<E extends Item> extends EntityComparator<E> implements XmlConstants {
  private CategoryComparator categoryComparator;
  private ListComparator<Photo> photoComparator;
  
  public ItemComparator(boolean compareId) {
    super(compareId);
    
    categoryComparator = new CategoryComparator(compareId);
    photoComparator = new ListComparator<Photo>(compareId, F.Photos.name(), new PhotoComparator(compareId));
    
    addChild(photoComparator);
  }

  @Override
  public boolean equals(E o1, E o2) throws CompareException {
    if (checkNull(F.Item, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(F.Name, o1.getName(), o2.getName());
      result &= equals(F.Description, o1.getDescription(), o2.getDescription());
      result &= equals(F.PurchaseDate, o1.getPurchaseDate(), o2.getPurchaseDate());
      result &= equals(F.PurchaseAmount, o1.getPurchaseAmount(), o2.getPurchaseAmount());
      result &= categoryComparator.equals(o1.getCategory(), o2.getCategory());
      result &= photoComparator.equals(o1.getPhotos(), o2.getPhotos());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
