package com.srfaytkn.android.creditcardviewlib.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.srfaytkn.android.creditcardviewlib.R;


/**
 * Created by srfaytkn on 8/13/17.
 */

public class AddCreditCardView extends FrameLayout {
    private View view;

    public AddCreditCardView(@NonNull Context context) {
        super(context);
        initView();
    }

    public AddCreditCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AddCreditCardView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_add_credit_card, this, true);
    }
}
