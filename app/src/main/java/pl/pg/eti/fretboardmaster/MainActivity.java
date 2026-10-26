package pl.pg.eti.fretboardmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvStats;
    private Button btnPlay, btnShop, btnTuner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStats = findViewById(R.id.tvStats);
        btnPlay = findViewById(R.id.btnPlay);
        btnShop = findViewById(R.id.btnShop);
        btnTuner = findViewById(R.id.btnTuner);

        updateStats();

        btnPlay.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        });

        btnShop.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ShopActivity.class));
        });

        btnTuner.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TunerActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStats();
    }

    private void updateStats() {
        int[] progress = FileManager.loadPlayerProgress(this);
        tvStats.setText("Punkty: " + progress[0] + " | Życia: " + progress[1]);
    }
}