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

import java.util.Calendar;


/**
 * Created by srfaytkn on 8/10/17.
 */

public class CardExpiryDateFragment extends Fragment implements CardEditFragment, TextWatcher {
    private static final String KEY_EXPIRY_DATE = "KEY_EXPIRY_DATE";

    private MaskedEditText mCardExpiryDateInput;
    private CardEditActivity cardEditActivity;

    public static CardExpiryDateFragment newInstance(String cardExpiryDate) {
        CardExpiryDateFragment fragment = new CardExpiryDateFragment();

        Bundle args = new Bundle();
        args.putString(KEY_EXPIRY_DATE, cardExpiryDate);
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
        View view = inflater.inflate(R.layout.fragment_card_expiry_date, container, false);

        findViews(view);
        setUp();

        return view;
    }

    private void findViews(View view) {
        mCardExpiryDateInput = view.findViewById(R.id.input_card_expiry_date);
    }

    private void setUp() {
        mCardExpiryDateInput.setText(getArguments().getString(KEY_EXPIRY_DATE));
        mCardExpiryDateInput.addTextChangedListener(this);
        cardEditActivity.creditCardView.setCardExpiryDate(mCardExpiryDateInput.getUnMaskedText());
    }

    @Override
    public boolean isValid() {
        if (mCardExpiryDateInput.getUnMaskedText().length() < 4) {
            return false;
        }
        int nowYear = Calendar.getInstance().get(Calendar.YEAR) % 2000;

        int expiryMonth = Integer.parseInt(mCardExpiryDateInput.getUnMaskedText().substring(0, 2));
        int expiryYear = Integer.parseInt(mCardExpiryDateInput.getUnMaskedText().substring(2, 4));

        if (expiryMonth > 12 || expiryMonth == 0) {
            mCardExpiryDateInput.setError(getResources().getString(R.string.error_invalid_month));
            return false;
        }

        if (expiryYear < nowYear) {
            mCardExpiryDateInput.setError(getResources().getString(R.string.error_invalid_year));
            return false;
        }

        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        cardEditActivity.creditCardView.setCardExpiryDate(mCardExpiryDateInput.getUnMaskedText());
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isValid()) {
            cardEditActivity.viewPager.setCurrentItem(cardEditActivity.viewPager.getCurrentItem() + 1);
        }
    }
}
