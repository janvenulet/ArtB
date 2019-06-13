package com.example.artb;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.artb.R;

public class CompanyView extends RelativeLayout {


    @StyleableRes
    int index0 = 0;
    @StyleableRes
    int index1 = 1;
    @StyleableRes
    int index2 = 2;
    @StyleableRes
    int index3 = 3;
    @StyleableRes
    int index4 = 4;

    TextView priceText;
    TextView companyText;
    TextView indexText;
    TextView sharesText;
    ImageView logoImage;
    PopupWindow popupWindow;

    public Button sellButton;
    public Button buyButton;

    public CompanyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.company_view, this);


        buyButton = findViewById(R.id.buy_btn);
        sellButton = findViewById(R.id.sell_btn);

        int [] sets2 = {R.attr.indexText};
        TypedArray typedArray2 = context.obtainStyledAttributes(attrs, sets2);
        CharSequence index = typedArray2.getText(index0);

        int [] sets3 = {R.attr.logoImage};
        TypedArray typedArray3 = context.obtainStyledAttributes(attrs, sets3);
        Drawable logo = typedArray3.getDrawable(index0);

        int[] sets = { R.attr.companyText, R.attr.priceText, R.attr.sharesText}; // R.attr.indexText }, R.attr.priceText,  R.attr.logoImage, R.attr.sharesText};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence company = typedArray.getText(index0);
        CharSequence price = typedArray.getText(index1);
        CharSequence shares = typedArray.getText(index2);

        typedArray.recycle();
        typedArray2.recycle();
        typedArray3.recycle();

        initComponents();


        setPriceText(price);
        setCompanyText(company);
        setIndexText(index);
        setSharesText(shares);
        setLogoImage(logo);


    }


    private void initComponents() {
        priceText = (TextView) findViewById(R.id.price_text);

        companyText = (TextView) findViewById(R.id.company_text);

        indexText = (TextView)  findViewById(R.id.index_text);

        sharesText = (TextView)  findViewById(R.id.shares_text);

        logoImage = (ImageView)  findViewById(R.id.logo_image);
    }



    public void buyShares(View view)
    {

    }

    public void sellShares(View view)
    {

    }

//    public Drawable getDrawable(Context context, Drawable logo) {
//        Resources resources = context.getResources();
//        int iconId = resources.getIdentifier( logo.toString(), "drawable",
//                context.getPackageName());
//        if (iconId==0) return null;
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), iconId, null);//ContextCompat.getDrawable( context.getApplicationContext(), iconId);
//        return drawable;
//    }

    public void setPriceText(CharSequence value) {
       priceText.setText(value);
    }

    public CharSequence getPriceText() {
        return priceText.getText();
    }

    public void setCompanyText(CharSequence value) {
        companyText.setText(value);
    }

    public CharSequence getCompanyText() {
        return companyText.getText();
    }

    public void setIndexText(CharSequence value) {
        indexText.setText(value);
    }

    public void setIndexColorRed(boolean bool) {
        if (bool) {
            indexText.setTextColor(Color.RED);
            Drawable image = getContext().getResources().getDrawable(R.drawable.trendingdown);
            //int [] sets1 = {R.drawable.};
            //TypedArray typedArray3 = context.obtainStyledAttributes(attrs, sets3);
            //Drawable logo = typedArray3.getDrawable(index0);
            indexText.setCompoundDrawablesWithIntrinsicBounds(image,null,null,null);
        }
        else {
            indexText.setTextColor(Color.GREEN);
            Drawable image = getContext().getResources().getDrawable(R.drawable.trendingup);
            //int [] sets1 = {R.drawable.};
            //TypedArray typedArray3 = context.obtainStyledAttributes(attrs, sets3);
            //Drawable logo = typedArray3.getDrawable(index0);
            indexText.setCompoundDrawablesWithIntrinsicBounds(image,null,null,null);
        }
    }

    public CharSequence getIndex() {
        return indexText.getText();
    }

    public void setSharesText(CharSequence value) {
        sharesText.setText(value);
    }

    public CharSequence getShares() {
        return sharesText.getText();
    }

    public void setLogoImage(Drawable value) {
        logoImage.setImageDrawable(value);
    }

    public Drawable getLogoImage(Drawable value) {
        return logoImage.getDrawable();
    }

}



