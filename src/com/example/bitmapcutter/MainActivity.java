package com.example.bitmapcutter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ImageView imageView;
        
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            imageView = (ImageView) rootView.findViewById(R.id.image);
            return rootView;
        }
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            imageView.setImageBitmap(cropAndGivePointedShape(BitmapFactory.decodeResource(getResources(), R.id.image)));
        }
        
        private Bitmap cropAndGivePointedShape(Bitmap originalBitmap)
        {
            Bitmap bmOverlay = Bitmap.createBitmap(originalBitmap.getWidth(),
                                                   originalBitmap.getHeight(),
                                                   Bitmap.Config.ARGB_8888);

            Paint p = new Paint();
            p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));              
            Canvas canvas = new Canvas(bmOverlay); 
            canvas.drawBitmap(originalBitmap, 0, 0, null); 
            canvas.drawRect(0, 0, 20, 20, p);

            Point a = new Point(0, 20);
            Point b = new Point(20, 20);
            Point c = new Point(0, 40);

            Path path = new Path();
            path.setFillType(FillType.EVEN_ODD);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();

            canvas.drawPath(path, p);
            
            a = new Point(0, 40);
            b = new Point(0, 60);
            c = new Point(20, 60);

            path = new Path();
            path.setFillType(FillType.EVEN_ODD);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();

            canvas.drawPath(path, p);
            
            canvas.drawRect(0, 60, 20, originalBitmap.getHeight(), p);
            
            return bmOverlay;
        }
    }

}
