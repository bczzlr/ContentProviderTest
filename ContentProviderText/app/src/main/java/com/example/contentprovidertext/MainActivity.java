package com.example.contentprovidertext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_search;
    Button button_add;
    Button button_delete;
    Button button_change;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final ContentResolver contentResolver = getContentResolver();
        button_search = (Button) findViewById(R.id.button_search);
        button_add = (Button)findViewById(R.id.button_add);
        button_delete = (Button)findViewById(R.id.button_delete);
        button_change = (Button)findViewById(R.id.button_change);
        uri= Uri.parse("content://com.example.wordnote.provider/dict");

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor query = getContentResolver().query(uri, null, "word = ?", new String[]{"user"}, null);
                if (query.moveToFirst()){
                    do {
                        String word = query.getString(query.getColumnIndex("interpret"));
                        Toast.makeText(MainActivity.this,word,Toast.LENGTH_LONG).show();
                    }while (query.moveToNext());
                }
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("word","haha");
                getContentResolver().insert(uri,values);
                Toast.makeText(MainActivity.this,"增加成功",Toast.LENGTH_LONG).show();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContentResolver().delete(uri,"word = ?",new String[]{"user"});
            }
        });

        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("interpret","笑哈哈");
                getContentResolver().update(uri,values,"word = ?", new String[]{"haha"});
            }
        });
    }
}
