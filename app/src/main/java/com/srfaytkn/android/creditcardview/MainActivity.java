package com.srfaytkn.android.creditcardview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.srfaytkn.android.creditcardviewlib.cardEdit.CardEditActivity;
import com.srfaytkn.android.creditcardviewlib.view.CreditCardView;

public class MainActivity extends AppCompatActivity {

    public static final int CREATE_NEW_CARD = 0;

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
        }
    }
}
