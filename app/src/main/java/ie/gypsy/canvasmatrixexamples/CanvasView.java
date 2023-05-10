package ie.gypsy.canvasmatrixexamples;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.graphics.Camera;
import android.graphics.Matrix;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class CanvasView extends View {
    private static final String TAG = "CanvasView";


    Rect rect;
    Rect rectBlue;
    Rect rectGreen;
    int height = 1, width = 1;
    Paint paint;
    Paint paintGreen = new Paint();
    Paint paintBlue = new Paint();
    Paint tractorPaint = new Paint();
    Paint tractorCabPaint = new Paint();
    Paint ltGrayPaint = new Paint();
    Paint arrowPaint = new Paint();
    float xSkew = 0f, ySkew = 0f;
    float angle = 0f;
    Camera camera = new Camera();
    Bitmap bmp;
    int[] pixels = new int[1280*800];
    Matrix matrix = new Matrix();
    int zoomedNumberOfPixelsPerMetre = 5;
    TractorThreeDMeasurements tractorThreeDMeasurements = new TractorThreeDMeasurements();
    TractorThreeDMeasurements tractorThreeDMeasurements2 = new TractorThreeDMeasurements();


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(rect!=null){
            canvas.save();
            canvas.scale(0.65f,0.65f,width/2, height/2);
            tractorThreeDMeasurements.drawThreeDTractor(canvas,new int[] {width/2, height/2}, 1);
            canvas.restore();
            tractorThreeDMeasurements2.drawThreeDTractor(canvas,new int[] {400, height/2}, 1);
            drawTractor(canvas, new int[]{800,height/2},1);
//            drawTractor(canvas,new int[]{width/2,height/2+200}, 1);
//            drawThreeDTractor(canvas,new int[] {width/2, height/2} ,1);
            canvas.concat(matrix);
//            canvas.drawRect(rect, paint);
//            canvas.drawRect(rectBlue,paintBlue);
//            canvas.drawRect(rectGreen,paintGreen);
//            drawTractor(canvas,new int[]{width/2,height/2}, 1);
        }
    }

    //This Works
    void set3D(){
        camera.save();
        camera.rotateX(40);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-width/2,-height/2);
        matrix.postTranslate(width/2,height/2);
    }


    private void drawThreeDTractor(Canvas canvas,int[] centreOfTractor, float paintAlpha){
        int x = centreOfTractor[0];
        int y = centreOfTractor[1];

        tractorPaint.setColor(Color.GRAY);
        Rect bodyOfCab = new Rect((int) (x-(1.25 * zoomedNumberOfPixelsPerMetre)-2),y-20,(int) (x+(1.25 * zoomedNumberOfPixelsPerMetre)+2),(int) (y+(2.5 * zoomedNumberOfPixelsPerMetre)+2));
        Rect backOfCab = new Rect((int) (x-(1.25 * zoomedNumberOfPixelsPerMetre)-2),y+2,(int) (x+(1.25 * zoomedNumberOfPixelsPerMetre)+2),(int) (y+(2.5 * zoomedNumberOfPixelsPerMetre)+20));

        ltGrayPaint.setColor(Color.LTGRAY);
        canvas.drawRect(bodyOfCab,tractorPaint);
        canvas.drawRect(backOfCab,ltGrayPaint);

    }

    private void drawTractor(Canvas canvas, int[] centreOfTractor, float paintAlpha){


        int x = centreOfTractor[0];
        int y = centreOfTractor[1];


        //Set size of tractor to scale with zoom level, multiplicative factors based on full size tractor at zNOPPM = 20
        tractorPaint.setColor(Color.GRAY);
        tractorPaint.setAlpha((int) (255 * paintAlpha));
        canvas.drawRect((int) (x-(1.25 * zoomedNumberOfPixelsPerMetre)-2),y-2,(int) (x+(1.25 * zoomedNumberOfPixelsPerMetre)+2),(int) (y+(2.5 * zoomedNumberOfPixelsPerMetre)+2),tractorCabPaint);
        canvas.drawRect((int) (x-(1.25 * zoomedNumberOfPixelsPerMetre)),y,(int) (x+(1.25 * zoomedNumberOfPixelsPerMetre)),(int) (y+(2.5 * zoomedNumberOfPixelsPerMetre)),tractorPaint);//Tractor Cab

        tractorCabPaint.setAlpha((int) (255 * paintAlpha));
        canvas.drawRect((int) (x-(.85 * zoomedNumberOfPixelsPerMetre)),(int) (y-(2.5 * zoomedNumberOfPixelsPerMetre)),(int) (x+(.85 * zoomedNumberOfPixelsPerMetre)),y,tractorCabPaint);//Tractor Bonnet
        tractorPaint.setColor(Color.BLACK);
        tractorPaint.setAlpha((int) (255 * paintAlpha));
        canvas.drawRect(x-(2 * zoomedNumberOfPixelsPerMetre),(int) (y+(.5 * zoomedNumberOfPixelsPerMetre)),(int) (x-(1.5 * zoomedNumberOfPixelsPerMetre)), y+(2* zoomedNumberOfPixelsPerMetre),tractorPaint);//Back Left Wheel
        canvas.drawRect((int) (x+(1.5 * zoomedNumberOfPixelsPerMetre)),(int) (y+(.5 * zoomedNumberOfPixelsPerMetre)), x+(2 * zoomedNumberOfPixelsPerMetre), y+(2 * zoomedNumberOfPixelsPerMetre),tractorPaint);//Back RightWheel
        canvas.drawRect((int) (x-(1.6 * zoomedNumberOfPixelsPerMetre)), y-(2 * zoomedNumberOfPixelsPerMetre),(int) (x-(1.1 * zoomedNumberOfPixelsPerMetre)),(int) (y-(.5 * zoomedNumberOfPixelsPerMetre)),tractorPaint);//Front Left Wheel
        canvas.drawRect((int) (x+(1.1 * zoomedNumberOfPixelsPerMetre)), y-(2 * zoomedNumberOfPixelsPerMetre),(int) (x+(1.6 * zoomedNumberOfPixelsPerMetre)),(int) (y-(.5 * zoomedNumberOfPixelsPerMetre)),tractorPaint);//Front Right Wheel

        //Only draw arrow at front of tractor if it is central tractor icon
        arrowPaint.setStrokeWidth((float) 0.35*zoomedNumberOfPixelsPerMetre);
        if(paintAlpha==1) {
            canvas.drawLine(x, (int) (y-(2.5 * zoomedNumberOfPixelsPerMetre)), x, y-(5 * zoomedNumberOfPixelsPerMetre), arrowPaint);
            canvas.drawLine((int) (x+(.1 * zoomedNumberOfPixelsPerMetre)), y-(5 * zoomedNumberOfPixelsPerMetre), x-(zoomedNumberOfPixelsPerMetre), (int) (y-(3.5 * zoomedNumberOfPixelsPerMetre)), arrowPaint);
            canvas.drawLine((int) (x-(.1 * zoomedNumberOfPixelsPerMetre)), y-(5 * zoomedNumberOfPixelsPerMetre), x+(zoomedNumberOfPixelsPerMetre), (int) (y-(3.5 * zoomedNumberOfPixelsPerMetre)), arrowPaint);
        }
    }


    //This works on bitmap but
    //1) Leaves all other lines untransformed (would need to transform parallel lines, field outlines, etc. separately)
    //2) A warning online from 2015 of computational intensity (https://stackoverflow.com/questions/13935053/how-to-distort-an-image-to-any-quadrangle)
    void drawWithMatrixPolylineTransform(Canvas canvas){
        canvas.drawBitmap(bmp,matrix,null);
        canvas.drawLines(new float[]{width/4,height/4,3*width/4,height/4,
                3*width/4,height/4,3*width/4,3*height/4,
                3*width/4,3*height/4,width/4,3*height/4,
                width/4,3*height/4,width/4,height/4},paint);
    }

    void drawv1(Canvas canvas){
        Matrix mMatrix = new Matrix();

        camera.save();
//            canvas.skew(xSkew,ySkew);
//            camera.translate(-1*width/2f,ySkew,0);
        camera.setLocation(xSkew,0,50);
        camera.rotateX(angle);
//            camera.translate(-1*xSkew,-1*ySkew,0);
//            canvas.drawRect(rect,paint);
        camera.getMatrix(mMatrix);
        Log.e(TAG, "onDraw: X = " + camera.getLocationX() + ", Y =  " + camera.getLocationY() + ", Z = " + camera.getLocationZ());
        camera.restore();

        canvas.concat(mMatrix);
        canvas.translate(width/2f,0);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rectBlue,paintBlue);
        canvas.drawRect(rectGreen,paintGreen);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        rectGreen = new Rect(w/5, h/4, 2*w/5, 3*h/4);
        rect = new Rect(2*w/5, h/4, 3*w/5, 3*h/4);
        rectBlue = new Rect(3*w/5, h/4, 4*w/5, 3*h/4);

        bmp = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);

        Arrays.fill(pixels,Color.BLUE);

        bmp.setPixels(pixels,0,w,w/4,h/4,w/2,h/2);


        matrix.setPolyToPoly(new float[]{w/4,h/4,3*w/4,h/4,w/4,3*h/4,3*w/4,3*h/4},0,new float[]{w/4,h/4,3*w/4,h/4,w/4 - 100,3*h/4,3*w/4 + 100,3*h/4},0,4);
    }

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(6f);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paintBlue.setColor(Color.BLUE);
        paintGreen.setColor(Color.GREEN);
        arrowPaint.setColor(Color.BLACK); arrowPaint.setStrokeWidth(10f);
        tractorCabPaint.setColor(Color.GREEN);
        tractorThreeDMeasurements.setThreeDTractorIconPaintColours(Color.GREEN);
        tractorThreeDMeasurements2.setThreeDTractorIconPaintColours(Color.GREEN);
    }

    void increaseXSkew(){
        xSkew+=10;
        invalidate();
    }

    void increaseYSkew(){
        ySkew+=10;
        invalidate();
    }

    void decreaseXSkew(){
        xSkew-=10;
        invalidate();
    }

    void decreaseYSkew(){
        ySkew-=10;
        invalidate();
    }

    public void increaseAngle() {
        angle+=5;
        invalidate();
    }

    public void decreaseAngle() {
        angle-=5;
        invalidate();
    }
}
