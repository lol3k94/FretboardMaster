package pl.pg.eti.fretboardmaster;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TunerActivity extends AppCompatActivity {

    private TextView tvTargetNote;
    private Button btnE6, btnA5, btnD4, btnG3, btnB2, btnE1, btnBack;
    private ValueAnimator currentAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);

        tvTargetNote = findViewById(R.id.tvTargetNote);
        btnE6 = findViewById(R.id.btnE6);
        btnA5 = findViewById(R.id.btnA5);
        btnD4 = findViewById(R.id.btnD4);
        btnG3 = findViewById(R.id.btnG3);
        btnB2 = findViewById(R.id.btnB2);
        btnE1 = findViewById(R.id.btnE1);
        btnBack = findViewById(R.id.btnBack);

        btnE6.setOnClickListener(v -> simulateTuning("E"));
        btnA5.setOnClickListener(v -> simulateTuning("A"));
        btnD4.setOnClickListener(v -> simulateTuning("D"));
        btnG3.setOnClickListener(v -> simulateTuning("G"));
        btnB2.setOnClickListener(v -> simulateTuning("B"));
        btnE1.setOnClickListener(v -> simulateTuning("e"));

        btnBack.setOnClickListener(v -> finish());
    }

    private void simulateTuning(String noteLabel) {
        if (currentAnimator != null && currentAnimator.isRunning()) {
            currentAnimator.cancel();
        }

        int startValue = (Math.random() > 0.5) ? 10 : 90;

        currentAnimator = ValueAnimator.ofInt(startValue, 50);
        currentAnimator.setDuration(1500);
        currentAnimator.addUpdateListener(animation -> {
            int val = (int) animation.getAnimatedValue();

            if (val >= 48 && val <= 52) {
                tvTargetNote.setText("IDEALNIE: " + noteLabel);
                tvTargetNote.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00E676")));
                tvTargetNote.setTextColor(Color.BLACK);
            } else if (val < 48) {
                tvTargetNote.setText("NACIĄGNIJ STRUNĘ (Za nisko) \u2192");
                tvTargetNote.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFA000")));
                tvTargetNote.setTextColor(Color.WHITE);
            } else {
                tvTargetNote.setText("\u2190 POLUZUJ STRUNĘ (Za wysoko)");
                tvTargetNote.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E53935")));
                tvTargetNote.setTextColor(Color.WHITE);
            }
        });
        currentAnimator.start();
    }
}