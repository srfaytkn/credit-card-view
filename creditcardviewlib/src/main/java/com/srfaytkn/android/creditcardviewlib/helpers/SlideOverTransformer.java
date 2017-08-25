package com.srfaytkn.android.creditcardviewlib.helpers;

import android.support.v4.view.ViewPager;
import android.view.View;


public class SlideOverTransformer implements ViewPager.PageTransformer {

    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    private static final float MIN_ALPHA_SLIDE = 0.35f;

    public void transformPage(View page, float position) {
        final float alpha;
        final float scale;
        final float translationX;

        if (position < 0 && position > -1) {
            scale = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE;
            alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position));
            int pageWidth = page.getWidth();
            float translateValue = position * -pageWidth;
            if (translateValue > -pageWidth) {
                translationX = translateValue;
            } else {
                translationX = 0;
            }
        } else {
            alpha = 1;
            scale = 1;
            translationX = 0;
        }

        page.setAlpha(alpha);
        page.setTranslationX(translationX);
        page.setScaleX(scale);
        page.setScaleY(scale);
    }

}
