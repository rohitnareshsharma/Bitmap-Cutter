Bitmap-Cutter
=============

Cut any shape from a Bitmap in Android

Check the screenshot and observe the pointed shape of bitmap.

![Alt text](/assets/screenshot.png?raw=true “Cropped Image Sample”)

Magic comes from Here
    
   Paint p = new Paint();
   p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));

   
If you will draw anything on a canvas using the above paint instance.
Pixel will get cleared from there. 

So create canvas from original bitmap and do something like this.

            Bitmap bmOverlay = Bitmap.createBitmap(originalBitmap.getWidth(),
                                                   originalBitmap.getHeight(),
                                                   Bitmap.Config.ARGB_8888);

            Paint p = new Paint();
            p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));              
            
            Canvas canvas = new Canvas(bmOverlay); 
            canvas.drawBitmap(originalBitmap, 0, 0, null); 
            canvas.drawRect(0, 0, 20, 20, p);

            return bmOverlay;

We have only cut a rect here. You can use any other available
canvas.draw method and specifying coordinates will create any shape you need.

From a rectangular image we have created a pointed shape bitmap.

Thats It

Enjoy


    