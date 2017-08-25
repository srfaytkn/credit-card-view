package com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.srfaytkn.android.creditcardviewlib.R;
import com.srfaytkn.android.creditcardviewlib.cardEdit.CardEditActivity;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.CardEditFragment;


/**
 * Created by srfaytkn on 8/10/17.
 */

public class CardHolderNameFragment extends Fragment implements CardEditFragment, TextWatcher, TextView.OnEditorActionListener {
    private static final String KEY_HOLDER_NAME = "KEY_HOLDER_NAME";

    private EditText mCardHolderNameInput;
    private CardEditActivity cardEditActivity;

    public static CardHolderNameFragment newInstance(String cardHolderName) {
        CardHolderNameFragment fragment = new CardHolderNameFragment();

        Bundle args = new Bundle();
        args.putString(KEY_HOLDER_NAME, cardHolderName);
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
        View view = inflater.inflate(R.layout.fragment_card_holder_name, container, false);

        findViews(view);
        setUp();

        return view;
    }

    private void findViews(View view) {
        mCardHolderNameInput = view.findViewById(R.id.input_card_holder_name);
    }

    private void setUp() {
        mCardHolderNameInput.setText(getArguments().getString(KEY_HOLDER_NAME));
        mCardHolderNameInput.addTextChangedListener(this);
        mCardHolderNameInput.setOnEditorActionListener(this);
        cardEditActivity.creditCardView.setCardHolderName(mCardHolderNameInput.getText().toString());
    }

    @Override
    public boolean isValid() {
        return mCardHolderNameInput.length() > 0;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        cardEditActivity.creditCardView.setCardHolderName(mCardHolderNameInput.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            cardEditActivity.cardCreate();
            return true;
        }
        return false;
    }
}
