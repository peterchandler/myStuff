package jemstone.mystuff.ui.category;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.ui.AbstractListAdapter;
import android.content.Context;
import android.view.View;

public class CategoryListAdapter extends AbstractListAdapter<Category> {
  public CategoryListAdapter(Context context, View.OnClickListener onNextClickListener) {
    super(context, 
          EntityManager.getInstance().getCategories(), 
          R.layout.list_item_with_next_button, 
          onNextClickListener);
  }
}

