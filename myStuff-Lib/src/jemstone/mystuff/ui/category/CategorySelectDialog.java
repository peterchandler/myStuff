package jemstone.mystuff.ui.category;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.ui.ActivityParameters;
import jemstone.mystuff.ui.BaseDialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CategorySelectDialog extends BaseDialogFragment {
  private CategoryListAdapter adapter;
  private OnItemClickListener listener;

  public CategorySelectDialog() {
    super();
  }

  public CategorySelectDialog(ActivityParameters parameters) {
    super(parameters);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.list_dialog, container);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    // Create adapter
    adapter = new CategoryListAdapter(getView().getContext(), null);

    // Set title, and list properties
    if (getTitle() == null) {
      setTitle(R.string.titleSelectCategory);
    }

    ListView list = (ListView) getView().findViewById(android.R.id.list);
    list.setAdapter(adapter);
    list.setOnItemClickListener(listener);
  }

  public CategoryListAdapter getAdapter() {
    return adapter;
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    this.listener = listener;
  }
}
