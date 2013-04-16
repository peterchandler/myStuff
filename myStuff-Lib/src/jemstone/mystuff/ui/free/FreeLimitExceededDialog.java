package jemstone.mystuff.ui.free;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.ui.BaseDialogFragment;
import jemstone.mystuff.ui.MyStuffApp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FreeLimitExceededDialog extends BaseDialogFragment {
  private int limitStringId;

  public FreeLimitExceededDialog() {
    super();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.free_limit_exceeded_dialog, container);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    // Set title, and list properties
    setTitle(R.string.titleFreeLimitExceeded);
    
    // Set text views
    View view = getView();
    initTextView(view, R.id.freelimit_reached, R.string.freelimit_reached, getString(limitStringId));
    initTextView(view, R.id.freelimit_scenarios, R.string.freelimit_scenarios, FreeLimitManager.MAX_FREE_TRANSACTIONS);
    initTextView(view, R.id.freelimit_accounts, R.string.freelimit_accounts, FreeLimitManager.MAX_FREE_TRANSACTIONS);
    initTextView(view, R.id.freelimit_transactions, R.string.freelimit_transactions, FreeLimitManager.MAX_FREE_TRANSACTIONS);
    
    // Set handler to provide feedback
    View linkToGooglePlay = view.findViewById(R.id.link_to_google_play);
    linkToGooglePlay.setOnClickListener(new OnClickListener());
  }

  public void setLimitStringId(int limitStringId) {
    this.limitStringId = limitStringId;
  }
  
  private void initTextView(View parent, int viewId, int stringId, Object ... args) {
    TextView view = (TextView) parent.findViewById(viewId);
    
    String text = getString(stringId, args);
    view.setText(text);
  }
  
  private class OnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      getBaseActivity().navigateToGooglePlay(MyStuffApp.paidPackageName);
    }
  }
}
