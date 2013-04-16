package jemstone.mystuff.ui.free;

import jemstone.mystuff.ui.BaseFragment;


public class FreeLimitManager {
  public static final int MAX_FREE_TRANSACTIONS = 20;
  
  private static boolean isFreeVersion;
  
  /**
   * @return <code>true</code> if this is a free version of the application
   */
  public static boolean isFreeVersion() {
    return isFreeVersion;
  }

  public static void setFreeVersion(boolean isFreeVersion) {
    FreeLimitManager.isFreeVersion = isFreeVersion;
  }

  public static boolean isLimitReached(BaseFragment fragment) {
//    int numScenarios = EntityManager.getInstance().getScenarios().size();
//    if (isFreeVersion && numScenarios >= MAX_FREE_SCENARIOS) {
//      showLimitExceededDialog(fragment, R.string.scenarios);
//      return true;
//    }
    return false;
  }
  
  private static void showLimitExceededDialog(BaseFragment fragment, int limitResId) {
    final FreeLimitExceededDialog dialog = new FreeLimitExceededDialog();
    dialog.setLimitStringId(limitResId);
    dialog.show(fragment.getFragmentManager());
  }
}
