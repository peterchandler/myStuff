package jemstone.mystuff.ui.category;

import jemstone.mystuff.command.DeleteCategoryCommand;
import jemstone.mystuff.lib.R;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.ui.BaseFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class CategoryListFragment extends BaseFragment {
  private CategoryListAdapter adapter;
  private ListView list;

  public CategoryListFragment() {
    super();
    setMenuItemHandler(new MenuItemHandler());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View view = inflater.inflate(R.layout.list_fragment, container);

    // Create adapter
    adapter = new CategoryListAdapter(view.getContext(), new OnNextClickListener());

    list = (ListView)view.findViewById(android.R.id.list);
    list.setAdapter(adapter);
    list.setOnItemClickListener(new OnItemClickListener());
    list.setOnItemLongClickListener(new OnItemLongClickListener());

    return view;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    // Initialize the header
    setTitle(R.string.titleCategoryList);
  }

  @Override
  public void onRefresh() {
    adapter.notifyDataSetChanged();
    adapter.scrollTo(list, getParameters().getCategory());
  }

  private void deleteCategory(DeleteCategoryCommand command) {
    log.debug("deleteCategory [category=%s]", command.getCategoryNames());

    execute(command);

//    if (command.getNumTransactionsToMove() != 0) {
//      selectNewCategory(command);
//    } else {
//      execute(command);
//    }
  }

  private void selectNewCategory(final DeleteCategoryCommand command) {
    final CategorySelectDialog dialog = new CategorySelectDialog(getParameters());
  
    // Set custom title
    final String title = getString(R.string.titleDeleteCategory, command.getCategoryNames());
//    final String subtitle = getString(R.string.titleSelectNewCategory, command.getNumTransactionsToMove(), command.getCategoryNames());
    dialog.setTitle(title);
//    dialog.setSubTitle(subtitle);
  
    dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Set the selected account type
        Category newCategory = dialog.getAdapter().getItem(position);
        command.setNewCategory(newCategory);
  
        // Close the dialog
        dialog.dismiss();
  
        // Create a new account
        execute(command);
        refresh();
      }
    });
  
    // Ensure any pending edits are processed, and show the dialog
    dialog.show(getFragmentManager());
  }

  private class OnItemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      // Set the selected category, and start the edit activity
      getActivityManager().startCategoryEditActivity(adapter.getItem(position), false);
    }
  }

  private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
      adapter.setSelectionMode(true);
      adapter.selectItem(position, true);
      refresh();
      return true;
    }
  }
  
  private class OnNextClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      Object tag = view.getTag();
      if (tag != null && tag instanceof Category) {
        // TODO Start activity
//        getActivityManager().startTransByCategoryActivity(getParameters().getScenario(), (Category) tag);
      }
    }
  }

  public class MenuItemHandler extends BaseFragment.MenuItemHandler {
    private boolean isSelectionMode() {
      return (adapter != null) && adapter.isSelectionMode();
    }

    @Override
    public boolean canAdd() {
      return !isSelectionMode();
    }

    @Override
    public boolean canAddCategory() {
      return false;
    }

    @Override
    public boolean canCancel() {
      return isSelectionMode();
    }
    
    @Override
    public boolean canConfig() {
      return false;
    }

    @Override
    public boolean canDelete() {
      return isSelectionMode();
    }

    @Override
    public void onAdd() {
      onAddCategory();
    }

    @Override
    public void onDelete() {
      deleteCategory(new DeleteCategoryCommand(adapter.getSelectedItems()));
      adapter.setSelectionMode(false);
    }

    @Override
    public void onCancel() {
      adapter.setSelectionMode(false);
    }
  }
}
