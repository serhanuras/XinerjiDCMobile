package com.xinerji.xinerjidc.utilities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinerji.xinerjidc.R;

/**
 * Created by msuras on 2/13/18.
 */

public class ActionBarUtil {

    private Activity activity;
    private String title;
    private Class homeClass;
    private ImageView homeImageView;
    private ImageView prevImageView;

    public ActionBarUtil(final Activity activity, final String title,  final Class homeClass){
        this.activity = activity;
        this.title = title;
        this.homeClass = homeClass;

        ActionBar mActionBar = activity.getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(activity);

        View mCustomView = mInflater.inflate(R.layout.transaction_actionbar, null);
        TextView transactionactionbar_label = (TextView) mCustomView.findViewById(R.id.transactionactionbar_label);
        homeImageView = (ImageView) mCustomView.findViewById(R.id.transactionactionbar_home);
        prevImageView = (ImageView) mCustomView.findViewById(R.id.transactionactionbar_prev);

        prevImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, homeClass);
                activity.startActivity(intent);
            }
        });


       transactionactionbar_label.setText(title);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getHomeClass() {
        return homeClass;
    }

    public void setHomeClass(Class homeClass) {
        this.homeClass = homeClass;
    }

    public ImageView getHomeImageView() {
        return homeImageView;
    }

    public void setHomeImageView(ImageView homeImageView) {
        this.homeImageView = homeImageView;
    }

    public ImageView getPrevImageView() {
        return prevImageView;
    }

    public void setPrevImageView(ImageView prevImageView) {
        this.prevImageView = prevImageView;
    }
}
