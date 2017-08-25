package com.srfaytkn.android.creditcardviewlib.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.srfaytkn.android.creditcardviewlib.R;
import com.srfaytkn.android.creditcardviewlib.helpers.CardType;
import com.srfaytkn.android.creditcardviewlib.helpers.FlipListener;
import com.srfaytkn.android.maskededittextlib.MaskedEditText;


/**
 * Created by srfaytkn on 8/13/17.
 */

public class CreditCardView extends FrameLayout {

    public static final String CARD_CVV = "CARD_CVV";
    public static final String CARD_EXPIRY_DATE = "CARD_EXPIRY_DATE";
    public static final String CARD_HOLDER_NAME = "CARD_HOLDER_NAME";
    public static final String CARD_NUMBER = "CARD_NUMBER";

    private View view;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private ValueAnimator mFlipAnimator;
    private boolean isFlipped = false;

    private String mCardType;
    private ImageView mCardBGFrontImage;
    private ImageView mCardBGBackImage;
    private ImageView mCardFrontLogoImage;
    private ImageView mCardBackLogoImage;

    private MaskedEditText mCardNumber;
    private EditText mCardHolderName;
    private MaskedEditText mCardExpiryDate;
    private MaskedEditText mCardCVV;

    public CreditCardView(@NonNull Context context) {
        super(context);
        initView();
    }

    public CreditCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CreditCardView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_credit_card, this, true);
        setUp();
    }

    private void setUp() {
        findViews();
        changeCameraDistance();

        mFlipAnimator = ValueAnimator.ofFloat(0f, 1f);
        mFlipAnimator.setDuration(1200);
        mFlipAnimator.addUpdateListener(new FlipListener(mCardFrontLayout, mCardBackLayout));
    }

    private void changeCameraDistance() {
        float scale = getResources().getDisplayMetrics().density * 8000;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void findViews() {
        mCardBackLayout = view.findViewById(R.id.layout_card_back);
        mCardFrontLayout = view.findViewById(R.id.layout_card_front);

        mCardBGFrontImage = view.findViewById(R.id.img_card_bg_front);
        mCardBGBackImage = view.findViewById(R.id.img_card_bg_back);
        mCardFrontLogoImage = view.findViewById(R.id.img_card_front_logo);
        mCardBackLogoImage = view.findViewById(R.id.img_card_back_logo);

        mCardNumber = view.findViewById(R.id.input_card_number);
        mCardHolderName = view.findViewById(R.id.input_card_holder_name);
        mCardExpiryDate = view.findViewById(R.id.input_card_expiry_date);
        mCardCVV = view.findViewById(R.id.input_card_cvv);
    }

    public void flipCardFront() {
        if (isFlipped) {
            isFlipped = false;
            mFlipAnimator.reverse();
        }
    }

    public void flipCardBack() {
        if (!isFlipped) {
            isFlipped = true;
            mFlipAnimator.start();
        }
    }

    private void setDesign(CardType.CardDesign design) {
        if (design.getCardType().equals(mCardType)) {
            return;
        }

        mCardType = design.getCardType();
        mCardBGFrontImage.setImageResource(design.getBackgroundId());
        mCardBGBackImage.setImageResource(design.getBackgroundId());
        mCardFrontLogoImage.setImageResource(design.getLogoId() != null ? design.getLogoId() : 0);
        mCardBackLogoImage.setImageResource(design.getLogoId() != null ? design.getLogoId() : 0);
    }

    public void setCardNumber(String cardNumber) {
        setDesign(CardType.getCardDesign(cardNumber));
        mCardNumber.setText(cardNumber);
    }

    public String getCardNumber() {
        return mCardNumber.getUnMaskedText();
    }

    public void setCardHolderName(String cardHolderName) {
        mCardHolderName.setText(cardHolderName);
    }

    public String getCardHolderName() {
        return mCardHolderName.getText().toString();
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        mCardExpiryDate.setText(cardExpiryDate);
    }

    public String getCardExpiryDate() {
        return mCardExpiryDate.getText().toString();
    }

    public void setCardCVV(String cardCVV) {
        mCardCVV.setText(cardCVV);
    }

    public String getCardCVV() {
        return mCardCVV.getUnMaskedText();
    }

    public String getCardType() {
        return mCardType;
    }
}
