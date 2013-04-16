package jemstone.mystuff.ui;

import jemstone.mystuff.model.HasCategory;
import jemstone.ui.AbstractFragment;
import jemstone.util.MyRuntimeException;
import jemstone.util.command.UndoableCommand;
import jemstone.util.file.FileManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class BaseFragment extends AbstractFragment<BaseActivity,ActivityManager,ActivityParameters> {
  public BaseFragment() {
    setHasOptionsMenu(true);
  }

  @Override
  protected void setParametersClone(ActivityParameters parameters) {
    // Clone the parameters
    setParameters(parameters.clone());
  }

  private void updateParameters(UndoableCommand command) {
    if (command instanceof HasCategory) {
      getParameters().setCategory(((HasCategory)command).getCategory());
    }
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getMenuItemHandler().onCreateMenu(menu, inflater, getParameters());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    log.debug("onOptionsItemSelected: %s", getParameters());

    boolean result = getMenuItemHandler().onMenuItemSelected(item, getParameters(), getActivityManager());
    refresh();
    return result;
  }

  public void execute(UndoableCommand command) {
    getCommandManager().execute(command);
    updateParameters(command);
  }

  public UndoableCommand undo() {
    UndoableCommand command = getCommandManager().undo();
    updateParameters(command);
    
    return command;
  }

  public UndoableCommand redo() {
    UndoableCommand command = getCommandManager().redo();
    updateParameters(command);
    
    return command;
  }
  
  /**
   * Display the specified error message as a {@link Toast}
   * @param stringId
   * @param args
   */
  public void showWarning(int stringId, Object ... args) {
    String message = getString(stringId, args);
    Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show();
  }

  public class MenuItemHandler extends BaseMenuItemHandler {
    public MenuItemHandler() {
      super(BaseFragment.this);
    }
    
    protected boolean isNewEntity() {
      ActivityParameters parameters = getParameters();
      return (parameters != null) && parameters.isNewEntity();
    }

    @Override
    public void onAccept() {
      log.trace("onAccept: Clearing focus and hiding soft keyboard");
      finishActivity();
    }

    @Override
    public boolean canUndo() {
      return getCommandManager().hasUndo();
    }

    @Override
    public boolean canRedo() {
      return getCommandManager().hasRedo();
    }

    @Override
    public void onUndo() {
      undo();
    }

    @Override
    public void onRedo() {
      redo();
    }

    @Override
    public void onReload() {
      try {
        FileManager fileManager = getBaseActivity().getFileManager();
        log.debug("onReload: %s", fileManager.getFileName());
        
        fileManager.load();
      } catch (Exception e) {
        throw new MyRuntimeException(e, "Error reloading from file");
      }
    }
  }
}
