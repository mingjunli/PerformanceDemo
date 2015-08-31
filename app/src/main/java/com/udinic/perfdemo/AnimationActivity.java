package com.udinic.perfdemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udinic.perfdemo.util.ViewServer;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = findViewById(R.id.udinic);

                view.animate()
                    .translationX(240f)
                    .translationY(-600)
                    .alpha(0.2f)
                    .rotation(3000f)
                    .scaleX(2f)
                    .setDuration(8000)
                    .withLayer()
                    .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            view.findViewById(R.id.btn2).setBackgroundColor((int)(animation.getAnimatedFraction() * 0xF));
                        }
                    })
                    .start();
            }

        });

        View alphaLayout = findViewById(R.id.alphaLayout);
        alphaLayout.setAlpha(0.5f);

        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
