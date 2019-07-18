package com.shareem.myapplication.user;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shareem.myapplication.R;

import java.util.List;

// TODO convert to base adapter
public class FriendListAdapter extends ArrayAdapter {

    private final Activity context;
    private List<User> friends;

    public FriendListAdapter(Activity context, List<User> friends){
        super(context, R.layout.userprofile_friendlist_listview_row, friends);
        this.context = context;
        this.friends = friends;
    }


    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.userprofile_friendlist_listview_row, null, true);

        TextView txtFriendName = rowView.findViewById(R.id.txtFriendName);
        TextView txtFriendEmail = rowView.findViewById(R.id.txtFriendEmail);
        TextView txtFriendAddress = rowView.findViewById(R.id.txtFriendAddress);

        txtFriendName.setText(friends.get(position).getName());
        txtFriendEmail.setText(friends.get(position).getUsername());
        txtFriendAddress.setText(friends.get(position).getAddress());

        return rowView;
    }


}

