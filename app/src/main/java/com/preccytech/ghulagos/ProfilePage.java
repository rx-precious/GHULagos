package com.preccytech.ghulagos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);


        ImageView profileImageView = (ImageView) findViewById(R.id.profile_page_picture);
        TextView userNameTextView = (TextView) findViewById(R.id.profile_page_name);
        Button shareButton = (Button) findViewById(R.id.profile_share_button);
        TextView userUrl = (TextView) findViewById(R.id.profile_page_url);





        Intent intent = getIntent();
        final String username = intent.getStringExtra(DevelopersAdapter.NAME);
        String image = intent.getStringExtra(DevelopersAdapter.IMAGE);
        final String profileUrl = intent.getStringExtra(DevelopersAdapter.URL);


        Picasso.with(this)
                .load(image)
                .into(profileImageView);

        userNameTextView.setText(username);
        userUrl.setText(profileUrl);

       userUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent shareIntent = new Intent(Intent.ACTION_VIEW);
                shareIntent.setData(Uri.parse(profileUrl));
                startActivity(shareIntent);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer "
                        + username
                        + ", " + profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });



    }


}
