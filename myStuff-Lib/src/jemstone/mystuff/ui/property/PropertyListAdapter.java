package jemstone.mystuff.ui.property;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.Property;
import jemstone.ui.AbstractListAdapter;
import android.content.Context;

public class PropertyListAdapter extends AbstractListAdapter<Property> {
  public PropertyListAdapter(Context context) {
    super(context, 
          EntityManager.getInstance().getProperties(), 
          R.layout.list_item_with_next_button, 
          null);
  }
}

