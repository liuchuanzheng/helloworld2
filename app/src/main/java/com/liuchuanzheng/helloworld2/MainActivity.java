package com.liuchuanzheng.helloworld2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.actionStart(MainActivity.this,"aaa","bbb");

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.listview:
                Main2Activity.actionStart(MainActivity.this,"aaa","bbb");
                break;
            case R.id.gridview:
                Main3Activity.actionStart(MainActivity.this,"aaa","bbb");
                break;
            case R.id.gridview_h:
                Main4Activity.actionStart(MainActivity.this,"aaa","bbb");
                break;
            case R.id.strager:
                Main5Activity.actionStart(MainActivity.this,"aaa","bbb");
                //瀑布流
                break;

        }
        return true;
    }
}
