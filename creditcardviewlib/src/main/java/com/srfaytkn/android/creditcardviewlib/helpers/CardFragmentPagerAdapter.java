package com.srfaytkn.android.creditcardviewlib.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.CardEditFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by srfaytkn on 8/10/17.
 */

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter {

    List<CardEditFragment> cardEditFragments = new ArrayList<>();

    public CardFragmentPagerAdapter(FragmentManager fm, List<CardEditFragment> cardEditFragments) {
        super(fm);
        this.cardEditFragments = cardEditFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) cardEditFragments.get(position);
    }

    @Override
    public int getCount() {
        return cardEditFragments.size();
    }

    public void addCardEditFragment(CardEditFragment cardEditFragment) {
        cardEditFragments.add(cardEditFragment);
    }

    public List<CardEditFragment> getCardEditFragments() {
        return cardEditFragments;
    }
}
