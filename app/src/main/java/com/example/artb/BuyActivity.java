package com.example.artb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BuyActivity extends AppCompatActivity {


    int companyId;

    TextView moneyText;
    TextView stocksText;
    SeekBar seekBar;

    Button buyButton;
    Button cancelButton;

    int money;
    int stockPrice;
    int price;
    int stocks;


    public void seekbarFunction() {
        seekBar.setMax((int) Math.floor(money/stockPrice));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                moneyText.setText( "Cost: " + progress*stockPrice + "/" + money + "$");
                stocksText.setText("Stocks: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                stocks = progress_value;
                moneyText.setText( "Cost: " + progress_value*stockPrice + "/" + money + "$");
                stocksText.setText("Stocks: " + progress_value);
            }
        });
    }

    void initViews(){
        seekBar = findViewById(R.id.seekBar);
        moneyText = findViewById(R.id.money_text);
        stocksText = findViewById(R.id.stocks_text);
        buyButton = findViewById(R.id.buy_btn);
        cancelButton = findViewById(R.id.cancel_btn);

        moneyText.setText( "Cost: " + price + "/" + money + "$");
        stocksText.setText("Stocks: " + stocks);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spent = stockPrice * stocks;
                Intent data = new Intent();
                data.putExtra("bought", stocks);
                data.putExtra("spent", spent);
                data.putExtra("company_id", companyId);
                setResult(RESULT_OK ,data);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void getExtras(){
        money = getIntent().getIntExtra("money",0);
        stockPrice = getIntent().getIntExtra("stock_price",0);
        companyId = getIntent().getIntExtra("company_id", -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        getExtras();
        initViews();
        seekbarFunction();
        setDialogSize();
    }

    void setDialogSize(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = (int) ((int) dm.widthPixels * 0.8);
        int height = (int) ((int) dm.heightPixels * 0.2);
        getWindow().setLayout((int) width ,(int) height );
    }

}
