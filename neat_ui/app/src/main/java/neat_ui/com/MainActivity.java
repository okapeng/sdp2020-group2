package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.manual_mode);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goToManualMovementActivity();

            }

        });
    }

    private void goToManualMovementActivity() {

        Intent intent = new Intent(this, ManualMovementActivity.class);

        startActivity(intent);

    }
}
