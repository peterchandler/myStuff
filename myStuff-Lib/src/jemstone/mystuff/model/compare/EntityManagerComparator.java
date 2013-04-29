package jemstone.mystuff.model.compare;

import jemstone.mystuff.dao.XmlConstants;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.EntityManager.F;
import jemstone.mystuff.model.Property;
import jemstone.util.compare.BaseComparator;
import jemstone.util.compare.CompareException;
import jemstone.util.compare.ListComparator;

public class EntityManagerComparator extends BaseComparator<EntityManager> implements XmlConstants {
  private IdFactoryComparator idFactoryComparator;
  private ListComparator<Category> categoryComparator;
  private ListComparator<Property> propertyComparator;

  public EntityManagerComparator(boolean compareId) {
    this(compareId, true);
  }
  
  public EntityManagerComparator(boolean compareId, boolean strict) {
    super(compareId);

    idFactoryComparator = new IdFactoryComparator(compareId, false);
    categoryComparator = new ListComparator<Category>(compareId, F.Categories.name(), new CategoryComparator(compareId));
    propertyComparator = new ListComparator<Property>(compareId, F.Properties.name(), new PropertyComparator(compareId));

    addChild(idFactoryComparator);
    addChild(propertyComparator);
    addChild(categoryComparator);
  }

  @Override
  public boolean equals(EntityManager o1, EntityManager o2) throws CompareException {
    if (checkNull(F.EntityManager, o1, o2)) {
      return (o1 == o2);
    }

    boolean result = true;
    result &= categoryComparator.equals(o1.getCategories(), o2.getCategories());
    result &= propertyComparator.equals(o1.getProperties(), o2.getProperties());
    result &= idFactoryComparator.equals(o1.getIdFactory(), o2.getIdFactory());
    
    return result;
  }
}
