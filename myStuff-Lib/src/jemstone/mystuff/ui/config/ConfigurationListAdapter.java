package jemstone.mystuff.ui.config;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.model.EntityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Provides an adapter over the list of accounts
 */
public class ConfigurationListAdapter extends BaseAdapter {
  private final LayoutInflater inflater;
  private final String[] groups;
  private final String[] descriptions;

  public ConfigurationListAdapter(Context context) {
    super();
    inflater = LayoutInflater.from(context);
    groups = context.getResources().getStringArray(R.array.main_groups);
    descriptions = context.getResources().getStringArray(R.array.main_descriptions);
  }

  @Override
  public int getCount() {
    return groups.length;
  }

  @Override
  public String getItem(int position) {
    return groups[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      view = inflater.inflate(R.layout.main_list_item, parent, false);
    }

    TextView nameView = (TextView)view.findViewById(R.id.name);
    if (nameView != null) {
      nameView.setText(groups[position]);
    }

    TextView countView = (TextView)view.findViewById(R.id.count);
    if (countView != null) {
      int count = 0;
      switch (position) {
        case 0: count = EntityManager.getInstance().getCategories().size(); break;
      }
      if (count > 0) {
        countView.setText("(" + count + ")");
      } else {
        countView.setText("");
      }
    }

    TextView describeView = (TextView)view.findViewById(R.id.description);
    if (describeView != null) {
      describeView.setText(descriptions[position]);
    }

    return view;
  }
}
