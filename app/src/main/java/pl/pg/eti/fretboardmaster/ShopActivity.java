package pl.pg.eti.fretboardmaster;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    private TextView tvShopStats;
    private Button btnBuyLife, btnBuyMetronome, btnBuyCapo, btnBackFromShop;
    private int points, lives, metronomeLevel, capoUnlocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        tvShopStats = findViewById(R.id.tvShopStats);
        btnBuyLife = findViewById(R.id.btnBuyLife);
        btnBuyMetronome = findViewById(R.id.btnBuyMetronome);
        btnBuyCapo = findViewById(R.id.btnBuyCapo);
        btnBackFromShop = findViewById(R.id.btnBackFromShop);

        loadPlayerData();

        btnBuyLife.setOnClickListener(v -> tryBuyItem(10, 1));
        btnBuyMetronome.setOnClickListener(v -> tryBuyItem(20, 2));
        btnBuyCapo.setOnClickListener(v -> tryBuyItem(50, 3));

        btnBackFromShop.setOnClickListener(v -> finish());
    }

    private void loadPlayerData() {
        int[] progress = FileManager.loadPlayerProgress(this);
        points = progress[0];
        lives = progress[1];
        metronomeLevel = progress[2];
        capoUnlocked = progress[3];
        updateUI();
    }

    private void updateUI() {
        String stats = "Punkty: " + points + "\nŻycia: " + lives + " | Metronom: Lvl " + metronomeLevel;
        tvShopStats.setText(stats);

        if (capoUnlocked == 1) {
            btnBuyCapo.setText("Kapodaster: ODBLOKOWANY");
            btnBuyCapo.setEnabled(false);
        }
    }

    private void tryBuyItem(int cost, int itemType) {
        if (points >= cost) {
            points -= cost;

            if (itemType == 1) {
                lives++;
            } else if (itemType == 2) {
                metronomeLevel++;
            } else if (itemType == 3) {
                capoUnlocked = 1;
            }

            FileManager.savePlayerProgress(this, points, lives, metronomeLevel, capoUnlocked == 1);
            updateUI();
            Toast.makeText(this, "Zakup udany!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Brakuje Ci punktów!", Toast.LENGTH_SHORT).show();
        }
    }
}