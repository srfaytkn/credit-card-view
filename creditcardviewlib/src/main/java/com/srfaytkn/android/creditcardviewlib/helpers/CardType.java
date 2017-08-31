package com.srfaytkn.android.creditcardviewlib.helpers;


import com.srfaytkn.android.creditcardviewlib.R;

import java.util.regex.Pattern;


/**
 * Created by srfaytkn on 8/13/17.
 */

public class CardType {

    public static final String CARD_TYPE_VISA = "CARD_TYPE_VISA";
    public static final String CARD_TYPE_MASTER = "CARD_TYPE_MASTER";
    public static final String CARD_TYPE_MAESTRO = "CARD_TYPE_MAESTRO";
    public static final String CARD_TYPE_AMEX = "CARD_TYPE_A_EXPRESS";
    public static final String CARD_TYPE_UNDEFINED = "CARD_TYPE_UNDEFINED";

    private static Pattern visaPattern = Pattern.compile("^4.{15}");
    private static Pattern masterPattern = Pattern.compile("^5.{15}");
    private static Pattern maestroPattern = Pattern.compile("^(5018|5020|5038|6304|6759|6761|6763).{12}$");
    private static Pattern amexPattern = Pattern.compile("^3[47].{13}$");

    public static CardDesign getCardDesign(String cardNumber) {
        if (visaPattern.matcher(cardNumber).matches()) {
            return new CardDesign(CARD_TYPE_VISA);
        } else if (maestroPattern.matcher(cardNumber).matches()) {
            return new CardDesign(CARD_TYPE_MAESTRO);
        } else if (masterPattern.matcher(cardNumber).matches()) {
            return new CardDesign(CARD_TYPE_MASTER);
        } else if (amexPattern.matcher(cardNumber).matches()) {
            return new CardDesign(CARD_TYPE_AMEX);
        } else {
            return new CardDesign(CARD_TYPE_UNDEFINED);
        }
    }


    public static class CardDesign {
        private String cardType;
        private Integer backgroundId;
        private Integer logoId;

        public CardDesign(String cardType) {
            this.cardType = cardType;

            switch (cardType) {
                case CARD_TYPE_VISA:
                    backgroundId = R.drawable.bg_card_visa;
                    logoId = R.drawable.ic_visa;
                    break;
                case CARD_TYPE_MASTER:
                    backgroundId = R.drawable.bg_card_master;
                    logoId = R.drawable.ic_mastercard;
                    break;
                case CARD_TYPE_MAESTRO:
                    backgroundId = R.drawable.bg_card_maestro;
                    logoId = R.drawable.ic_maestro;
                    break;
                case CARD_TYPE_AMEX:
                    backgroundId = R.drawable.bg_card_amex;
                    logoId = R.drawable.ic_american_express;
                    break;
                case CARD_TYPE_UNDEFINED:
                    backgroundId = R.drawable.bg_card_undefined;
                    break;
                default:
                    break;
            }
        }

        public String getCardType() {
            return cardType;
        }

        public Integer getBackgroundId() {
            return backgroundId;
        }

        public Integer getLogoId() {
            return logoId;
        }
    }
}
