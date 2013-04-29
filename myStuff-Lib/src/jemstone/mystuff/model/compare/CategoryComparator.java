package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.Category.F;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.EntityComparator;

public class CategoryComparator extends EntityComparator<Category> implements XmlConstants {
  public CategoryComparator(boolean compareId) {
    super(compareId);
  }

  @Override
  public boolean equals(Category o1, Category o2) throws CompareException {
    if (checkNull(F.Category, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = super.equals(o1, o2);
      result &= equals(F.Name, o1.getName(), o2.getName());
      result &= equals(F.Description, o1.getDescription(), o2.getDescription());
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s id=%s with %s id=%s", getClassName(o1), o1.getId(), getClassName(o2), o2.getId());
      throw e;
    }
  }
}
