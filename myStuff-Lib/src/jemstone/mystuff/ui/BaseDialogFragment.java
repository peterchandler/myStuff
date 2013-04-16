package jemstone.mystuff.ui;

import jemstone.ui.AbstractDialogFragment;
import android.app.Activity;

public class BaseDialogFragment extends AbstractDialogFragment<ActivityParameters> {
  private BaseActivity activity;
  
  public BaseDialogFragment() {
    super();
  }

  public BaseDialogFragment(ActivityParameters parameters) {
    super(parameters);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    // Keep track of the parent Activity
    this.activity = (BaseActivity) activity;

    // Set parameters from the parent Activity if not already set
    if (getParameters() == null) {
      setParameters(this.activity.getParameters().clone());
    }

    log.debug("onAttach: %s", getParameters());
  }

  public BaseActivity getBaseActivity() {
    return activity;
  }
}
