package jemstone.mystuff.ui;

import java.io.Serializable;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.ui.category.CategoryListActivity;
import jemstone.util.MyRuntimeException;
import jemstone.util.log.Logger;
import android.app.Activity;
import android.content.Intent;

public class ActivityManager implements jemstone.ui.ActivityManager {
  private static final Logger log = Logger.getLogger(ActivityManager.class);

  private static final Definition[] definitions = {
//    new Definition(AboutActivity.class, false, Intent.FLAG_ACTIVITY_CLEAR_TOP),
//    new Definition(ConfigurationActivity.class, false, Intent.FLAG_ACTIVITY_CLEAR_TOP),
//    new Definition(CategoryEditActivity.class, false, Intent.FLAG_ACTIVITY_CLEAR_TOP),
    new Definition(CategoryListActivity.class, false, Intent.FLAG_ACTIVITY_CLEAR_TOP),
  };

  public static final String PARENT_DEFN = Definition.class.getName();

  private final BaseActivity parent;

  public ActivityManager(BaseActivity parent) {
    this.parent = parent;
  }

  private static Definition find(Class<? extends Activity> activityClass) {
    // Find definition for specified activity
    for (Definition defn : definitions) {
      if (defn.activityClass == activityClass) {
        return defn;
      }
    }
    throw new MyRuntimeException("ActivityManager cannot find definition for %s", activityClass.getSimpleName());
  }

  private void startActivity(Class<? extends Activity> activityClass, ActivityParameters parameters) {
    Definition definition = find(activityClass);
    startActivity(definition, parameters);
  }

  private void startActivity(Definition definition, ActivityParameters parameters) {
    log.debug("%s is starting %s: %s", parent.getClass().getSimpleName(), definition.activityClass.getSimpleName(), parameters);

    // Create the intent and start the new activity
    Intent intent = createIntentForActivity(definition, parameters);
    parent.startActivity(intent);
  }

  private Intent createIntentForActivity(Definition definition, ActivityParameters parameters) {
    // Set parent activity in the parameters
    parameters.setParentActivityClass(parent.getClass().getName());
    
    // Launch the activity
    Intent intent = new Intent(Intent.ACTION_EDIT);
    intent.setClass(parent, definition.activityClass);
    intent.putExtra(ActivityParameters.NAME, parameters);
    if (definition.intentFlagActivity != null) {
      intent.setFlags(definition.intentFlagActivity);
    }

    return intent;
  }

  public boolean isParentOneOf(Class<?> ... classes) {
    final String parentActivityClass = parent.getParameters().getParentActivityClass();
    if (parentActivityClass != null) {
      for (Class<?> clazz : classes) {
        if (parentActivityClass.equals(clazz.getName())) {
          return true;
        }
      }
    }
    return false;
  }
  

  public static boolean isHomeActivity(Class<? extends Activity> activityClass) {
    Definition defn = find(activityClass);
    return (defn != null && defn.homeActivity);
  }


  public void startAboutActivity() {
    ActivityParameters parameters = new ActivityParameters();
//    startActivity(AboutActivity.class, parameters);
  }

  public void startHomeActivity() {
  }

  public void startConfigurationActivity() {
    ActivityParameters parameters = new ActivityParameters();
//    startActivity(ConfigurationActivity.class, parameters);
  }

  public void startCategoryEditActivity(Category category, boolean isNewCategory) {
    ActivityParameters parameters = new ActivityParameters();
    parameters.setCategory(category);
    parameters.setNewEntity(isNewCategory);
//    startActivity(CategoryEditActivity.class, parameters);
  }

  public void startCategoryListActivity() {
    ActivityParameters parameters = new ActivityParameters();
//    startActivity(CategoryListActivity.class, parameters);
  }

  private static class Definition implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Class<? extends Activity> activityClass;
    private final boolean homeActivity;
    private final Integer intentFlagActivity;

    public Definition(Class<? extends Activity> activityClass,
                      boolean homeActivity,
                      Integer intentFlagActivity) {
      this.activityClass = activityClass;
      this.homeActivity = homeActivity;
      this.intentFlagActivity = intentFlagActivity;
    }
  }
}
