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
import com.srfaytkn.android.creditcardviewlib.helpers.CardType;


/**
 * Created by srfaytkn on 8/10/17.
 */

public class CardNumberFragment extends Fragment implements CardEditFragment, TextWatcher {
    private static final String KEY_NUMBER = "KEY_NUMBER";

    private MaskedEditText mCardNumberInput;
    private CardEditActivity cardEditActivity;

    public static CardNumberFragment newInstance(String cardNumber) {
        CardNumberFragment fragment = new CardNumberFragment();

        Bundle args = new Bundle();
        args.putString(KEY_NUMBER, cardNumber);
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
        View view = inflater.inflate(R.layout.fragment_card_number, container, false);

        findViews(view);
        setUp();

        return view;
    }

    private void findViews(View view) {
        mCardNumberInput = view.findViewById(R.id.input_card_number);
    }

    private void setUp() {
        mCardNumberInput.setText(getArguments().getString(KEY_NUMBER));
        mCardNumberInput.addTextChangedListener(this);
        mCardNumberInput.requestFocus();
        cardEditActivity.creditCardView.setCardNumber(mCardNumberInput.getUnMaskedText());
    }

    @Override
    public boolean isValid() {
        String cardType = cardEditActivity.creditCardView.getCardType();
        return !cardType.equals(CardType.CARD_TYPE_UNDEFINED)
                || cardType.equals(CardType.CARD_TYPE_UNDEFINED) && mCardNumberInput.getUnMaskedText().length() == 16;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        cardEditActivity.creditCardView.setCardNumber(mCardNumberInput.getUnMaskedText());
    }

    @Override
    public void afterTextChanged(Editable editable) {
       if (isValid()){
           cardEditActivity.viewPager.setCurrentItem(cardEditActivity.viewPager.getCurrentItem() + 1);
       }
    }
}
