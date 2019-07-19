package com.shareem.myapplication.user;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.shareem.myapplication.R;

import java.util.List;

public class AddFriendListAdapter extends BaseAdapter {

    private Activity context;
    private List<User> users;
    private View.OnClickListener btnAddFriendOnClickListener;

    public AddFriendListAdapter(Activity context, List<User> users, View.OnClickListener btnAddFriendOnClickListener) {
        this.users = users;
        this.context = context;
        this.btnAddFriendOnClickListener = btnAddFriendOnClickListener;
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return users.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User user = users.get(i);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.addfriend_userlist_listview_row, null);

        TextView txtName = rowView.findViewById(R.id.txtAddFriendUserName);
        TextView txtAddress = rowView.findViewById(R.id.txtAddFriendUserAddress);
        TextView txtEmail = rowView.findViewById(R.id.txtAddFriendUserEmail);
        Button btnAddFriend = rowView.findViewById(R.id.btnAddFriendAdd);

        txtName.setText(user.getName());
        txtEmail.setText(user.getUsername());
        txtAddress.setText(user.getAddress());
        btnAddFriend.setTag(user);
        btnAddFriend.setOnClickListener(btnAddFriendOnClickListener);

        return rowView;
    }

    public List<User> getUsers() {
        return users;
    }
}
