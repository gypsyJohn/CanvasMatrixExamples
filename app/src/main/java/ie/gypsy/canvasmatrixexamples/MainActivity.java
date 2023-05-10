package ie.gypsy.canvasmatrixexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    CanvasView canvasView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        button = findViewById(R.id.xppButton);
        canvasView = (CanvasView) findViewById(R.id.canvasView);
    }


    public void increaseXSkew(View view) {
        canvasView.increaseXSkew();
    }

    public void increaseYSkew(View view) {
        canvasView.increaseYSkew();
    }

    public void decreaseXSkew(View view) {
        canvasView.decreaseXSkew();
    }

    public void decreaseYSkew(View view) {
        canvasView.decreaseYSkew();
    }

    public void increaseAngle(View view) {
        canvasView.increaseAngle();
    }

    public void decreaseAngle(View view) {
        canvasView.decreaseAngle();
    }
}