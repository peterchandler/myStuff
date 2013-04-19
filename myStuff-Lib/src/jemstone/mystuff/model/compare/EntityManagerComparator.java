package jemstone.mystuff.model.compare;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.util.compare.BaseComparator;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.ListComparator;

public class EntityManagerComparator extends BaseComparator<EntityManager> {
  private IdFactoryComparator idFactoryComparator;
  private ListComparator<Category> categoryComparator;

  public EntityManagerComparator(boolean compareId) {
    this(compareId, true);
  }
  
  public EntityManagerComparator(boolean compareId, boolean strict) {
    super(compareId);

    idFactoryComparator = new IdFactoryComparator(compareId, false);
    categoryComparator = new ListComparator<Category>(compareId, "Categories", new CategoryComparator(compareId));

    addChild(idFactoryComparator);
    addChild(categoryComparator);
  }

  @Override
  public boolean equals(EntityManager o1, EntityManager o2) throws CompareException {
    if (checkNull("EntityManager", o1, o2)) {
      return (o1 == o2);
    }

    boolean result = true;
    result &= categoryComparator.equals(o1.getCategories(), o2.getCategories());
    result &= idFactoryComparator.equals(o1.getIdFactory(), o2.getIdFactory());
    return result;
  }
}
