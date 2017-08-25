package com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srfaytkn.android.maskededittextlib.MaskedEditText;
import com.srfaytkn.android.creditcardviewlib.R;
import com.srfaytkn.android.creditcardviewlib.cardEdit.CardEditActivity;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.CardEditFragment;


/**
 * Created by srfaytkn on 8/10/17.
 */

public class CardCVVFragment extends Fragment implements CardEditFragment, TextWatcher {

    private static final String KEY_CVV = "KEY_CVV";

    private MaskedEditText mCardCVVInput;
    private CardEditActivity cardEditActivity;

    public static CardCVVFragment newInstance(String cardCVV) {
        CardCVVFragment fragment = new CardCVVFragment();

        Bundle args = new Bundle();
        args.putString(KEY_CVV, cardCVV);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardEditActivity = ((CardEditActivity) getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_cvv, container, false);

        findViews(view);
        setUp();

        return view;
    }

    private void findViews(View view) {
        mCardCVVInput = view.findViewById(R.id.input_card_cvv);
    }

    private void setUp() {
        mCardCVVInput.setText(getArguments().getString(KEY_CVV));
        mCardCVVInput.addTextChangedListener(this);
        cardEditActivity.creditCardView.setCardCVV(mCardCVVInput.getUnMaskedText());
    }

    @Override
    public boolean isValid() {
        return mCardCVVInput.getUnMaskedText().length() == 3;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        cardEditActivity.creditCardView.setCardCVV(mCardCVVInput.getUnMaskedText());
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isValid()) {
            cardEditActivity.viewPager.setCurrentItem(cardEditActivity.viewPager.getCurrentItem() + 1);
        }
    }
}
