package com.example.artb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SellActivity extends AppCompatActivity {


    int companyId;

    TextView profitText;
    TextView stocksText;
    SeekBar seekBar;

    Button sellButton;
    Button cancelButton;


    int stockPrice;
    int profit;
    int ownedStocks;
    int sellStocks;

    public void seekbarFunction() {
        seekBar.setMax(ownedStocks);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                profitText.setText( "Profit: " + progress*stockPrice + "$");
                stocksText.setText("Stocks: " +  progress + "/" + ownedStocks);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sellStocks = progress_value;
                profitText.setText( "Profit: " + progress_value*stockPrice + "$");
                stocksText.setText("Stocks: " +  progress_value + "/" + ownedStocks);
            }
        });
    }

    void initViews(){
        seekBar = findViewById(R.id.seekBar);
        profitText = findViewById(R.id.profit_text);
        stocksText = findViewById(R.id.stocks_text);
        sellButton = findViewById(R.id.sell_btn);
        cancelButton = findViewById(R.id.cancel_btn);

        profitText.setText( "Profit: " + profit  + "$");
        stocksText.setText("Stocks: 0/0");

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int profit = stockPrice * sellStocks;
                Intent data = new Intent();
                data.putExtra("sold", sellStocks);
                data.putExtra("profit", profit);
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
        ownedStocks = getIntent().getIntExtra("owned",0);
        stockPrice = getIntent().getIntExtra("stock_price",0);
        companyId = getIntent().getIntExtra("company_id", -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

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
