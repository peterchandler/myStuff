package jemstone.mystuff.model.compare;

import jemstone.mystuff.model.Address;
import jemstone.mystuff.model.Address.F;
import jemstone.util.compare.BaseComparator;
import jemstone.util.compare.CompareException;

public class AddressComparator extends BaseComparator<Address> {
  public AddressComparator(boolean compareId) {
    super(compareId);
  }

  @Override
  public boolean equals(Address o1, Address o2) throws CompareException {
    if (checkNull(F.Address, o1, o2)) {
      return (o1 == o2);
    }

    try {
      boolean result = false;
      
      result &= equals(F.Street, o1.getStreet(), o2.getStreet());
      result &= equals(F.Suburb, o1.getSuburb(), o2.getSuburb());
      result &= equals(F.City, o1.getCity(), o2.getCity());
      result &= equals(F.PostCode, o1.getPostCode(), o2.getPostCode());
      result &= equals(F.CountryCode, o1.getCountryCode(), o2.getCountryCode());
      
      return result;
    } catch (CompareException e) {
      e.add("Error comparing %s [%s] with %s [%s]", getClassName(o1), o1, getClassName(o2), o2);
      throw e;
    }
  }
}
