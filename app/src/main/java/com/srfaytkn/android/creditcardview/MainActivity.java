package com.srfaytkn.android.creditcardview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.srfaytkn.android.creditcardviewlib.cardEdit.CardEditActivity;
import com.srfaytkn.android.creditcardviewlib.view.CreditCardView;

public class MainActivity extends AppCompatActivity {

    public static final int CREATE_NEW_CARD = 0;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorToolbar));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        container = findViewById(R.id.container);
        findViewById(R.id.action_new_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCreditCard();
            }
        });
    }

    private void createNewCreditCard() {
        Intent intent = CardEditActivity.newIntent(this);
        startActivityForResult(intent, CREATE_NEW_CARD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CREATE_NEW_CARD) {
            String cvv = data.getStringExtra(CreditCardView.CARD_CVV);
            String expiryDate = data.getStringExtra(CreditCardView.CARD_EXPIRY_DATE);
            String holderName = data.getStringExtra(CreditCardView.CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardView.CARD_NUMBER);

            addCard(cvv, expiryDate, holderName, cardNumber);
        }
    }

    private void addCard(String cvv, String expiryDate, String holderName, String cardNumber) {
        CreditCardView creditCardView = new CreditCardView(this);

        creditCardView.setCardNumber(cardNumber);
        creditCardView.setCardExpiryDate(expiryDate);
        creditCardView.setCardCVV(cvv);
        creditCardView.setCardHolderName(holderName);

        container.addView(creditCardView);
    }
}
