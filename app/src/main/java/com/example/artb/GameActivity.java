package com.example.artb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GameActivity extends AppCompatActivity {


    int money = 300000;
    final int quartersLimit = 12;
    int value;
    int quarters = 0;
    final int request_Code_buy = 10;
    final int request_Code_sell = 11;

    int classIterator = 0;

    List<Company> companyList = new Vector <Company>();
    List<CompanyView> viewsList = new Vector<>();
    List<Button> buyButtons = new Vector<>();
    List<Button> sellButtons = new Vector<>();


    TextView yourMoneyView;
    TextView yourValueView;
    TextView quartersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initViews();
        initCompanies();
        initButtonsListeners();
        quartersView.append(" " + quarters + "/" + quartersLimit);
    }


    public void nextQuarter(View view)
    {
        if (quarters == quartersLimit)
            gameFinish();
        else {
            Random generator = new Random();
            for (Company company : companyList) {
                double change = ((generator.nextGaussian() + 0.4) / 20);
                company.stockValue += change * company.stockValue;
                if (company.stockValue < 0) company.stockValue = 0;
                company.lastChange = (float) change;
            }
            updateViews();
            quarters++;
            quartersView.setText("Quarters: " + quarters + "/" + quartersLimit);

            updateValue();
        }
    }

    void gameFinish(){
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        intent.putExtra("money", money);
        startActivity(intent);
        finish();
    }

    void initCompanies(){
        for (CompanyView view : viewsList) {
            companyList.add(new Company(view.getCompanyText().toString(), "sad", "sad", view.getPriceText().toString()));
        }
    }

    void initViews() {
        yourMoneyView =  findViewById(R.id.your_money);
        yourValueView =  findViewById(R.id.your_value);
        quartersView = findViewById(R.id.quarters);

        yourMoneyView.append(" " + money + "$");
        yourValueView.append(" " + value + "$");

        RelativeLayout rootRelativeLayout = findViewById(R.id.game_field);
        int count = rootRelativeLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = rootRelativeLayout.getChildAt(i);
            if (view instanceof CompanyView) {
                viewsList.add((CompanyView) view);
                buyButtons.add(((CompanyView) view).buyButton);
                sellButtons.add(((CompanyView) view).sellButton);
            }
        }
    }

    void initButtonsListeners(){
        classIterator = 0;
        for (Button button : buyButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                int tmp = classIterator;
                @Override
                public void onClick(View v) {
                    Intent intentPopup = new Intent(GameActivity.this, BuyActivity.class);
                    intentPopup.putExtra("money", money);
                    intentPopup.putExtra("stock_price", companyList.get(tmp).stockValue);
                    intentPopup.putExtra("company_id", tmp);
                    startActivityForResult(intentPopup, request_Code_buy);
                }
            });
            classIterator++;
        }
        classIterator = 0;
        for (Button button : sellButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                int tmp = classIterator;
                @Override
                public void onClick(View v) {
                    Intent intentPopup = new Intent(GameActivity.this, SellActivity.class);
                    intentPopup.putExtra("owned", companyList.get(tmp).ownedShares);
                    intentPopup.putExtra("stock_price", companyList.get(tmp).stockValue);
                    intentPopup.putExtra("company_id", tmp);
                    startActivityForResult(intentPopup, request_Code_sell);
                }
            });
            classIterator++;
        }
    }

    void updateViews() {
        int i = 0;
        for ( CompanyView companyView : viewsList) {
            companyView.setPriceText(Integer.toString(companyList.get(i).stockValue) + "$");
            float flt = (companyList.get(i).lastChange) * 100 ;
            companyView.setIndexText(String.format( "%.2f", flt) + "%");

            if (flt >0 ) companyView.setIndexColorRed(false);
            else
                companyView.setIndexColorRed(true);

            i++;
        }
    }



    void updateValue() {
        int result = 0;
        for (Company company : companyList) {
            result += company.stockValue * company.ownedShares;
        }
        yourValueView.setText("YOUR VALUE: " + result + "$");
    }

    public void backToMenu(View view)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_Code_buy) {
            if (resultCode == RESULT_OK) {
                int companyId = data.getIntExtra("company_id", -1);
                if (companyId == -1) return;
                money -= data.getIntExtra("spent", 0);
                int bought = data.getIntExtra("bought", 0 );
                yourMoneyView.setText("YOUR MONEY: " + money + "$");
                viewsList.get(companyId).setSharesText(Integer.toString(bought + companyList.get(companyId).ownedShares));
                companyList.get(companyId).ownedShares += bought;
                updateValue();
            }
        }
        if (requestCode == request_Code_sell) {
            if (resultCode == RESULT_OK) {
                int companyId = data.getIntExtra("company_id", -1);
                if (companyId == -1) return;
                money += data.getIntExtra("profit", 0);
                int sold = data.getIntExtra("sold", 0 );
                yourMoneyView.setText("YOUR MONEY: " + money + "$");
                viewsList.get(companyId).setSharesText(Integer.toString(companyList.get(companyId).ownedShares- sold));
                companyList.get(companyId).ownedShares -= sold;
                updateValue();
            }
        }
    }

}
