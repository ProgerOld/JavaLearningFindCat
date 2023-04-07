package com.example.findcat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView coordinatesOut;
    private float x;
    private float y;
    private String sDown;
    private String sMove;
    private String sUp;


    private final float xCat = 500;
    private final float yCat = 500;
    private final float deltaCat = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatesOut = findViewById(R.id.coordinatesOut);

        coordinatesOut.setOnTouchListener(listener);

    }

    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие: координата X = " + x + ", координата y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE: // движение
                    sMove = "Движение: координата X = " + x + ", координата y = " + y;

                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat) && y > (yCat - deltaCat)) {

                        showToast(view, (int) xCat, (int) yCat);

//                        Toast toast = Toast.makeText(getApplicationContext(),R.string.text_find_cat, Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.LEFT, (int) xCat,(int) yCat);
//
//                       LinearLayout toastContainer = (LinearLayout) toast.getView();
//
//                         ImageView cat = new ImageView(getApplicationContext());
//                        cat.setImageResource(R.drawable.img);
//                        toastContainer.addView(cat, 1);
//                       toast.show();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Отпускание: координата X = " + x + ", координата y = " + y;
                    break;
            }


            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);

            return true;
        }
    };

    public void showToast(View view, int xCat, int yCat) {
        //создаём и отображаем текстовое уведомление
        /*Toast toast = Toast.makeText(getApplicationContext(),
                "Пора покормить кота!",
                Toast.LENGTH_SHORT);
        toast.show();*/

        LayoutInflater inflater = getLayoutInflater();

        // inflate layout file in Layout Inflater
        view = inflater.inflate(R.layout.toast_image_layout,
                (ViewGroup) findViewById(R.id.relativeLayoutToast));
        Toast toast = new Toast(getApplicationContext());

        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);

        // add view of toast to
        // toast_image_layout file
        toast.setView(view);

        // show toast
        toast.show();

    }

}