package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.Item;
import jemstone.util.compare.BaseComparator;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.ListComparator;

public class EntityManagerComparator extends BaseComparator<EntityManager> implements XmlConstants {
  private IdFactoryComparator idFactoryComparator;
  private ListComparator<Item> itemComparator;
  private ListComparator<Category> categoryComparator;

  public EntityManagerComparator(boolean compareId) {
    this(compareId, true);
  }
  
  public EntityManagerComparator(boolean compareId, boolean strict) {
    super(compareId);

    idFactoryComparator = new IdFactoryComparator(compareId, false);
    itemComparator = new ListComparator<Item>(compareId, ITEMS, new ItemComparator(compareId));
    categoryComparator = new ListComparator<Category>(compareId, CATEGORIES, new CategoryComparator(compareId));

    addChild(idFactoryComparator);
    addChild(itemComparator);
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
    result &= itemComparator.equals(o1.getItems(), o2.getItems());
    return result;
  }
}
