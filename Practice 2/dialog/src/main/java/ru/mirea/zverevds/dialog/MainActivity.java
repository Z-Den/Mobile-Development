package ru.mirea.zverevds.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button timeDialog = findViewById(R.id.timeDialog);
        Button dateDialog = findViewById(R.id.dateDialog);
        Button progressDialog = findViewById(R.id.progressDialog);

        timeDialog.setOnClickListener(v -> {
            showDialog(new TimeDialogFragment());
        });
        dateDialog.setOnClickListener(v -> {
            showDialog(new DateDialogFragment());
        });
        progressDialog.setOnClickListener(v -> {
            showDialog(new ProgressDialogFragment());
        });
    }

    private void showDialog(DialogFragment dialog) {
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Отлично, молодец, так держать!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Не расстраивайся, ещё чуть-чуть!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Главное не забрасывай, успех уже близко!",
                Toast.LENGTH_LONG).show();
    }
}