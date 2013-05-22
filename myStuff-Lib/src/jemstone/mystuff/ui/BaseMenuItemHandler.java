package jemstone.mystuff.ui;

import jemstone.mystuff.command.AddCategoryCommand;
import jemstone.mystuff.command.AddPropertyCommand;
import jemstone.mystuff.lib.R;
import jemstone.ui.MenuItemHandler;
import jemstone.util.log.Logger;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseMenuItemHandler implements MenuItemHandler<ActivityManager,ActivityParameters> {
  protected final Logger log = Logger.getLogger(this);

  private final int menuId;
  private final BaseFragment fragment;
  
  private Menu menu;

  public BaseMenuItemHandler() {
    this.menuId = R.menu.main_activity_menu;
    this.fragment = null;
  }

  public BaseMenuItemHandler(BaseFragment fragment) {
    this.menuId = R.menu.main_activity_menu;
    this.fragment = fragment;
  }

  public BaseMenuItemHandler(int menuId) {
    this.menuId = menuId;
    this.fragment = null;
  }

  @Override
  public void onCreateMenu(Menu menu, MenuInflater inflater, ActivityParameters parameters) {
    log.debug("onCreateMenu: %s", parameters);
    inflater.inflate(menuId, menu);
    
    this.menu = menu;
    
    onRefresh();
  }
  
  @Override
  public boolean onMenuItemSelected(MenuItem item, ActivityParameters parameters, ActivityManager activityManager) {
    log.debug("onMenuItemSelected: %s", parameters);

    final int menuItemId = item.getItemId();
    
    // See if the command button handler processes the menu item
    if (menuItemId == R.id.undo) {
      onUndo();
    }
    
    else if (menuItemId == R.id.redo) {
      onRedo();
    }
    
    else if (menuItemId == R.id.add) {
      onAdd();
    }
    
    else if (menuItemId == R.id.delete) {
      onDelete();
    }
    
    else if (menuItemId == R.id.accept) {
      onAccept();
    }
    
    else if (menuItemId == R.id.cancel) {
      onCancel();
    }
    
    else if (menuItemId == android.R.id.home) {
      activityManager.startHomeActivity();
    }
    
    else if (menuItemId == R.id.reload) {
      onReload();
    }
    
    else if (menuItemId == R.id.cut) {
      onCut();
    }
    
    else if (menuItemId == R.id.copy) {
      onCopy();
    }
    
    else if (menuItemId == R.id.paste) {
      onPaste();
    }
    
    else if (menuItemId == R.id.menu_configure) {
      activityManager.startConfigurationActivity();
    }
    
    else if (menuItemId == R.id.menu_add_category) {
      onAddCategory();
    }
    
    // Default
    else {
      return false;
    }
    
    return true;
  }

  @Override
  public void onRefresh() {
    log.trace("onRefresh:");

    if (menu != null) {
      setVisible(R.id.undo, canUndo());
      setVisible(R.id.redo, canRedo());
      setVisible(R.id.add,  canAdd());
      setVisible(R.id.delete, canDelete());
      setVisible(R.id.accept, canAccept());
      setVisible(R.id.cancel, canCancel());
      setVisible(R.id.reload, canReload());
      setVisible(R.id.cut, canCut());
      setVisible(R.id.copy, canCopy());
      setVisible(R.id.paste, canPaste());
      setVisible(R.id.menu_configure, canConfig());
      setVisible(R.id.menu_add_category, canAddCategory());
      setVisible(R.id.menu_add_property, canAddProperty());
    }
  }

  private void setVisible(int id, boolean visible) {
    MenuItem item = menu.findItem(id);
    if (item != null) {
      item.setVisible(visible);
    }
  }

  @Override
  public boolean canAdd() {
    return false;
  }

  public boolean canAddCategory() {
    return (fragment != null);
  }

  public boolean canAddProperty() {
    return (fragment != null);
  }

  @Override
  public boolean canConfig() {
    return true;
  }

  @Override
  public boolean canDelete() {
    return false;
  }

  @Override
  public boolean canAccept() {
    return false;
  }

  @Override
  public boolean canCancel() {
    return false;
  }

  @Override
  public boolean canUndo() {
    return false;
  }

  @Override
  public boolean canRedo() {
    return false;
  }

  @Override
  public boolean canReload() {
    return true;
  }

  @Override
  public boolean canCut() {
    return false;
  }

  @Override
  public boolean canCopy() {
    return false;
  }

  @Override
  public boolean canPaste() {
    return false;
  }

  @Override
  public void onAdd() {
  }

  public void onAddCategory() {
    // Create a new account
    AddCategoryCommand command = new AddCategoryCommand();
    fragment.execute(command);

    // Set the new category id, and start the edit activity
    fragment.getActivityManager().startCategoryEditActivity(command.getCategory(), true);
  }

  public void onAddProperty() {
    // Create a new account
    AddPropertyCommand command = new AddPropertyCommand();
    fragment.execute(command);

    // Set the new category id, and start the edit activity
    fragment.getActivityManager().startPropertyEditActivity(command.getProperty(), true);
  }

  @Override
  public void onDelete() {
  }

  @Override
  public void onAccept() {
  }

  @Override
  public void onCancel() {
  }

  @Override
  public void onUndo() {
  }

  @Override
  public void onRedo() {
  }

  @Override
  public void onReload() {
  }

  @Override
  public void onCut() {
  }

  @Override
  public void onCopy() {
  }

  @Override
  public void onPaste() {
  }
}
