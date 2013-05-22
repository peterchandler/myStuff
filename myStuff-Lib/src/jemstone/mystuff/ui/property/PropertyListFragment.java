package jemstone.mystuff.ui.property;

import jemstone.mystuff.command.DeletePropertyCommand;
import jemstone.mystuff.lib.R;
import jemstone.mystuff.model.Property;
import jemstone.mystuff.ui.BaseFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class PropertyListFragment extends BaseFragment {
  private PropertyListAdapter adapter;
  private ListView list;

  public PropertyListFragment() {
    super();
    setMenuItemHandler(new MenuItemHandler());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View view = inflater.inflate(R.layout.list_fragment, container);

    // Create adapter
    adapter = new PropertyListAdapter(view.getContext());

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
    setTitle(R.string.titlePropertyList);
  }

  @Override
  public void onRefresh() {
    adapter.notifyDataSetChanged();
    adapter.scrollTo(list, getParameters().getProperty());
  }

  private void deleteProperty(DeletePropertyCommand command) {
    log.debug("deleteProperty [Property=%s]", command.getPropertyNames());

    execute(command);

//    if (command.getNumTransactionsToMove() != 0) {
//      selectNewProperty(command);
//    } else {
//      execute(command);
//    }
  }

  private void selectNewProperty(final DeletePropertyCommand command) {
    final PropertySelectDialog dialog = new PropertySelectDialog(getParameters());
  
    // Set custom title
    final String title = getString(R.string.titleDeleteProperty, command.getPropertyNames());
//    final String subtitle = getString(R.string.titleSelectNewProperty, command.getNumTransactionsToMove(), command.getPropertyNames());
    dialog.setTitle(title);
//    dialog.setSubTitle(subtitle);
  
    dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Set the selected account type
        Property newProperty = dialog.getAdapter().getItem(position);
        command.setNewProperty(newProperty);
  
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
      // Set the selected Property, and start the edit activity
      getActivityManager().startPropertyEditActivity(adapter.getItem(position), false);
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
      if (tag != null && tag instanceof Property) {
        // TODO Start activity
//        getActivityManager().startTransByPropertyActivity(getParameters().getScenario(), (Property) tag);
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
    public boolean canAddProperty() {
      return true;
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
      onAddProperty();
    }

    @Override
    public void onDelete() {
      deleteProperty(new DeletePropertyCommand(adapter.getSelectedItems()));
      adapter.setSelectionMode(false);
    }

    @Override
    public void onCancel() {
      adapter.setSelectionMode(false);
    }
  }
}
