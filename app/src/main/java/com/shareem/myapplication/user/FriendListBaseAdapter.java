package com.shareem.myapplication.user;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shareem.myapplication.R;

import java.util.List;

public class FriendListBaseAdapter extends BaseAdapter {

    private List<User> friends;
    private Activity context;

    public FriendListBaseAdapter(Activity context, List<User> friends) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int i) {
        return friends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return friends.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User friend = friends.get(i);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.userprofile_friendlist_listview_row, null);

        TextView txtFriendName = rowView.findViewById(R.id.txtFriendName);
        TextView txtFriendEmail = rowView.findViewById(R.id.txtFriendEmail);
        TextView txtFriendAddress = rowView.findViewById(R.id.txtFriendAddress);

        txtFriendName.setText(friend.getName());
        txtFriendEmail.setText(friend.getUsername());
        txtFriendAddress.setText(friend.getAddress());

        return rowView;
    }
}
