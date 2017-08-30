package com.preccytech.ghulagos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rx Precious on 8/26/2017.
 */

public class DevelopersAdapter extends ArrayAdapter<DevelopersList> {


    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String URL = "url";

    Context context;

    public DevelopersAdapter(Context context, ArrayList<DevelopersList> developers) {
        super(context, R.layout.profile_list, developers);
        this.context = context;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) {
            //Initiate the view where the array are gonna be arranged
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.profile_list, parent, false);
        }


        //Get the object located at this position in the list
        final DevelopersList currentDeveloper = getItem(position);
        //Find the textview in the items.xml layout with the ID specified
        TextView Username = (TextView) ListItemView.findViewById(R.id.profile_name);
        // Get the miwok translation from the currentWord and set the text to the text View above
        Username.setText(currentDeveloper.getmUsername());
        //
        LinearLayout list = (LinearLayout) ListItemView.findViewById(R.id.profile_list);

        ImageView image = (ImageView) ListItemView.findViewById(R.id.profile_picture);

        Picasso.with(context)
                .load(currentDeveloper.getmPicture())
                .into(image);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openIntent = new Intent(v.getContext(), ProfilePage.class);
                openIntent.putExtra(NAME, currentDeveloper.getmUsername());
                openIntent.putExtra(URL, currentDeveloper.getmUrl());
                openIntent.putExtra(IMAGE, currentDeveloper.getmPicture());
                v.getContext().startActivity(openIntent);
            }
        });

        return ListItemView;
    }

}