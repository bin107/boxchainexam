package com.example.dobin.boxchainexam.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dobin.boxchainexam.R;

public class UserDetailsView extends LinearLayout {

    private LinearLayout userList;
    private Context mContext;

    public UserDetailsView(Context context) {
        super(context);
        init();
        mContext = context;
    }


    public UserDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mContext = context;
    }

    public UserDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        mContext = context;
    }

    private void init() {
        inflate(getContext(), R.layout.layout_userlist, this);

        userList = findViewById(R.id.userList);

    }

    public void addTextView(String txt) {
        TextView txtView = new TextView(mContext);
        txtView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        txtView.setText(txt);
        userList.addView(txtView);
    }

}
