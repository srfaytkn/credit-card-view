package com.srfaytkn.android.creditcardviewlib.helpers;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by srfaytkn on 8/21/17.
 */

public class FlipListener implements ValueAnimator.AnimatorUpdateListener {

    private final View mFrontView;
    private final View mBackView;
    private boolean mFlipped;

    public FlipListener(final View front, final View back) {
        this.mFrontView = front;
        this.mBackView = back;
        this.mBackView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationUpdate(final ValueAnimator animation) {
        final float value = animation.getAnimatedFraction();

        if (value <= 0.5f) {
            this.mFrontView.setRotationY(180 * value);
            if (mFlipped) {
                setStateFlipped(false);
            }
        } else {
            this.mBackView.setRotationY(-180 * (1f - value));
            if (!mFlipped) {
                setStateFlipped(true);
            }
        }
    }

    private void setStateFlipped(boolean flipped) {
        mFlipped = flipped;
        this.mFrontView.setVisibility(flipped ? View.GONE : View.VISIBLE);
        this.mBackView.setVisibility(flipped ? View.VISIBLE : View.GONE);
    }
}