package com.example.roomwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.roomwords.REPLY";
    public static final String EXTRA_REPLY_ID = "com.android.example.roomwordssample.REPLY_ID";

    private EditText mEditWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        int id = -1;

        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String word = extras.getString(MainActivity.EXTRA_DATA_UPDATE_WORD, "");
            if (!word.isEmpty()) {
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String reply = mEditWordView.getText().toString();
                    replyIntent.putExtra(NewWordActivity.EXTRA_REPLY, reply);
                    if (extras != null && extras.containsKey(EXTRA_REPLY_ID)) {
                        int id = extras.getInt(EXTRA_REPLY_ID, -1);
                        if (id != -1) {
                            replyIntent.putExtra(NewWordActivity.EXTRA_REPLY_ID, id);
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}