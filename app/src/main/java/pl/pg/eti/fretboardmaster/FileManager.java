package pl.pg.eti.fretboardmaster;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileManager {

    private static final String PROGRESS_FILE = "player_progress.txt";

    public static int[] loadPlayerProgress(Context context) {
        int[] stats = {0, 3, 1, 0};

        try {
            FileInputStream fis = context.openFileInput(PROGRESS_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    stats[0] = Integer.parseInt(parts[0]);
                    stats[1] = Integer.parseInt(parts[1]);
                    stats[2] = Integer.parseInt(parts[2]);
                    stats[3] = Integer.parseInt(parts[3]);
                }
            }
            reader.close();
        } catch (Exception e) {
            savePlayerProgress(context, stats[0], stats[1], stats[2], stats[3] == 1);
        }

        return stats;
    }

    public static void savePlayerProgress(Context context, int points, int lives, int metronomeLevel, boolean capoUnlocked) {
        try {
            FileOutputStream fos = context.openFileOutput(PROGRESS_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            int capoInt = capoUnlocked ? 1 : 0;
            writer.write(points + "," + lives + "," + metronomeLevel + "," + capoInt);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}