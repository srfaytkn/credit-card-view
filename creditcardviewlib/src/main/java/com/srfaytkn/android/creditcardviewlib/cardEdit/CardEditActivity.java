package com.srfaytkn.android.creditcardviewlib.cardEdit;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.srfaytkn.android.creditcardviewlib.R;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.CardEditFragment;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.impl.CardCVVFragment;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.impl.CardExpiryDateFragment;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.impl.CardHolderNameFragment;
import com.srfaytkn.android.creditcardviewlib.cardEdit.inputs.impl.CardNumberFragment;
import com.srfaytkn.android.creditcardviewlib.helpers.CardFragmentPagerAdapter;
import com.srfaytkn.android.creditcardviewlib.helpers.SlideOverTransformer;
import com.srfaytkn.android.creditcardviewlib.view.CreditCardView;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srfaytkn on 8/10/17.
 */

public class CardEditActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public ViewPager viewPager;
    public CreditCardView creditCardView;
    private CardFragmentPagerAdapter mPagerAdapter;

    public static Intent newIntent(Activity callerActivity,
                                   String cardCVV,
                                   String cardExpiryDate,
                                   String cardHolderName,
                                   String cardNumber) {
        Intent intent = new Intent(callerActivity, CardEditActivity.class);

        intent.putExtra(CreditCardView.CARD_CVV, cardCVV);
        intent.putExtra(CreditCardView.CARD_EXPIRY_DATE, cardExpiryDate);
        intent.putExtra(CreditCardView.CARD_HOLDER_NAME, cardHolderName);
        intent.putExtra(CreditCardView.CARD_NUMBER, cardNumber);
        return intent;
    }

    public static Intent newIntent(Activity callerActivity) {
        Intent intent = new Intent(callerActivity, CardEditActivity.class);

        intent.putExtra(CreditCardView.CARD_CVV, "");
        intent.putExtra(CreditCardView.CARD_EXPIRY_DATE, "");
        intent.putExtra(CreditCardView.CARD_HOLDER_NAME, "");
        intent.putExtra(CreditCardView.CARD_NUMBER, "");
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorToolbar));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViews();
        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            cardCreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUp() {
        Bundle extras = getIntent().getExtras();
        assert extras != null;

        List<CardEditFragment> cardEditFragments = new ArrayList<>();
        cardEditFragments.add(CardNumberFragment.newInstance(extras.getString(CreditCardView.CARD_NUMBER)));
        cardEditFragments.add(CardExpiryDateFragment.newInstance(extras.getString(CreditCardView.CARD_EXPIRY_DATE)));
        cardEditFragments.add((CardCVVFragment.newInstance(extras.getString(CreditCardView.CARD_CVV))));
        cardEditFragments.add(CardHolderNameFragment.newInstance(extras.getString(CreditCardView.CARD_HOLDER_NAME)));

        mPagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), cardEditFragments);
        SlideOverTransformer slideOverTransformer = new SlideOverTransformer();

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageTransformer(false, slideOverTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(48);
        viewPager.addOnPageChangeListener(this);
    }

    private void findViews() {
        viewPager = findViewById(R.id.viewPager);
        creditCardView = findViewById(R.id.creditCardView);
    }

    public void cardCreate() {
        boolean isInvalid = false;
        for (CardEditFragment cardEditFragment : mPagerAdapter.getCardEditFragments()) {
            if (!cardEditFragment.isValid()) {
                isInvalid = true;
                break;
            }
        }

        if (isInvalid) {
            Alerter.create(this)
                    .setTitle(R.string.alert_title_error)
                    .setBackgroundColorRes(R.color.alert_error)
                    .setText(R.string.error_invalid_card)
                    .show();
            return;
        }

        Intent intent = new Intent();

        intent.putExtra(CreditCardView.CARD_CVV, creditCardView.getCardCVV());
        intent.putExtra(CreditCardView.CARD_EXPIRY_DATE, creditCardView.getCardExpiryDate());
        intent.putExtra(CreditCardView.CARD_HOLDER_NAME, creditCardView.getCardHolderName());
        intent.putExtra(CreditCardView.CARD_NUMBER, creditCardView.getCardNumber());

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mPagerAdapter.getItem(position) instanceof CardCVVFragment) {
            creditCardView.flipCardBack();
        } else {
            creditCardView.flipCardFront();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
