package ie.gypsy.canvasmatrixexamples;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TractorThreeDMeasurements {
    Rect tractorCabRect = new Rect();
    int tractorCabHeight = 20;
    int tractorCabWidth = 28;

    Rect tractorBackRect = new Rect();
    int tractorBackHeight = 9;
    int tractorBackWidth = 28;

    Rect tractorWindowRect = new Rect();
    int tractorWindowHeight = 7;
    int tractorWindowWidth = 26;
    int tractorWindowYOffset = 1;

    Rect tractorBaseRect = new Rect();
    int tractorBackBaseHeight = 4;
    int tractorBackBaseWidth = 15;
    int tractorBaseYOffset = tractorBackHeight;

    Rect tractorFrontShadedRect = new Rect();
    int tractorFrontShadedHeight = 4;
    int tractorFrontShadedWidth = 18;
    int tractorFrontShadedYOffset = tractorCabHeight;

    Rect tractorFrontUnshadedRect = new Rect();
    int tractorFrontUnshadedHeight = 8;
    int tractorFrontUnshadedWidth = 18;
    int tractorFrontUnshadedYOffset = tractorFrontShadedYOffset + tractorFrontShadedHeight;

    Rect leftFrontWheelRect = new Rect();
    Rect rightFrontWheelRect = new Rect();
    int frontWheelHeight = 9;
    int frontWheelWidth = 4;
    int frontWheelYOffset = 18;
    int frontWheelXOffset = 11;

    Rect leftBackWheelRect = new Rect();
    Rect rightBackWheelRect = new Rect();
    int backWheelHeight = 15;
    int backWheelWidth = 5;
    int backWheelYOffset = 4;
    int backWheelXOffset = 15;

    Paint tractorCabPaint = new Paint();
    Paint tractorBackPaint = new Paint();
    Paint tractorBackWindowPaint = new Paint();
    Paint tractorBasePaint = new Paint();
    Paint tractorWheelPaint = new Paint();
    Paint tractorFrontShadedPaint = new Paint();
    Paint tractorFrontUnshadedPaint = new Paint();

    int cachedZoomedNumberOfPixelsPerMetre = 0;
    int pSF = 1;//pixel scaling factor

    public TractorThreeDMeasurements() {
    }

    void setThreeDTractorIconPaintColours(int tractorCabColor){

//        int unShadedTractorCabColor = lightenColor(tractorCabColor);
        int shadedTractorCabColor = 0xff008000;

        tractorCabPaint.setColor(Color.GRAY);
        tractorBackPaint.setColor(Color.LTGRAY);
        tractorBackWindowPaint.setColor(Color.CYAN);
        tractorBasePaint.setColor(Color.BLACK);
        tractorWheelPaint.setColor(Color.BLACK);
        tractorFrontShadedPaint.setColor(shadedTractorCabColor);
        tractorFrontUnshadedPaint.setColor(Color.GREEN);
    }

    //To lighten a colour, you have to increase the amount of light being emitted (i.e. 0xff00ff00 is much lighter than 0xff008000)
    int lightenColor(int color){
        int r = (int) ( (color & 0x00ff0000) >> 16);
        int g = (int) ( (color & 0x0000ff00) >> 8);
        int b = (int) ((color & 0x000000ff));

        int rPrime = r / 2;
        int gPrime = g / 2;
        int bPrime = b / 2;

//        int lighenedColor = (color & 0xff<<24) + (rPrime << );
    return 0;
    }


    void drawThreeDTractor(Canvas canvas, int[] centreOfTractor, int zoomedNumberOfPixelsPerMetre){
        int x = centreOfTractor[0];
        int y = centreOfTractor[1];

        if(zoomedNumberOfPixelsPerMetre != cachedZoomedNumberOfPixelsPerMetre){
            pSF = zoomedNumberOfPixelsPerMetre;
            cachedZoomedNumberOfPixelsPerMetre = zoomedNumberOfPixelsPerMetre;
            updateRects( x, y );
        }

        canvas.drawRect(leftFrontWheelRect,tractorWheelPaint);
        canvas.drawRect(rightFrontWheelRect, tractorWheelPaint);
        canvas.drawRect(tractorCabRect, tractorCabPaint);
        canvas.drawRect(tractorBackRect, tractorBackPaint);
        canvas.drawRect(tractorBaseRect, tractorBasePaint);
        canvas.drawRect(tractorFrontShadedRect, tractorFrontShadedPaint);
        canvas.drawRect(tractorFrontUnshadedRect, tractorFrontUnshadedPaint);
        canvas.drawRect(leftBackWheelRect,tractorWheelPaint);
        canvas.drawRect(rightBackWheelRect,tractorWheelPaint);
        canvas.drawRect(tractorWindowRect, tractorBackWindowPaint);

    }

    void updateRects( int x, int y){
        tractorCabRect.set(x - pSF * tractorCabWidth/2, y - pSF * tractorCabHeight, x + pSF * tractorCabWidth/2, y);
        tractorBackRect.set( x - pSF * tractorBackWidth/2, y, x + pSF * tractorBackWidth/2, y + pSF * tractorBackHeight);
        tractorWindowRect.set( x - pSF * tractorWindowWidth/2, y + pSF * tractorWindowYOffset, x + pSF * tractorWindowWidth/2, y + pSF * (tractorWindowYOffset + tractorWindowHeight));
        tractorBaseRect.set(x - pSF * tractorBackBaseWidth/2, y + pSF * tractorBaseYOffset, x + pSF * tractorBackBaseWidth/2, y + pSF * (tractorBaseYOffset + tractorBackBaseHeight));
        tractorFrontShadedRect.set(x - pSF * tractorFrontShadedWidth/2, y - pSF * tractorFrontShadedYOffset, x + pSF * tractorFrontShadedWidth/2, y - pSF * (tractorFrontShadedHeight + tractorFrontShadedYOffset));
        tractorFrontUnshadedRect.set( x - pSF * tractorFrontUnshadedWidth/2, y - pSF * tractorFrontUnshadedYOffset, x + pSF * tractorFrontUnshadedWidth/2, y - pSF * (tractorFrontUnshadedYOffset + tractorFrontUnshadedHeight));
        leftFrontWheelRect.set(x - pSF * frontWheelXOffset, y - pSF * frontWheelYOffset, x - pSF * (frontWheelXOffset + frontWheelWidth), y - pSF * (frontWheelYOffset + frontWheelHeight));
        rightFrontWheelRect.set(x + pSF * frontWheelXOffset, y - pSF * frontWheelYOffset, x + pSF * (frontWheelXOffset + frontWheelWidth), y - pSF * (frontWheelYOffset + frontWheelHeight));
        leftBackWheelRect.set(x - pSF * backWheelXOffset, y - pSF * backWheelYOffset, x - pSF * (backWheelXOffset + backWheelWidth), y - pSF * (backWheelYOffset - backWheelHeight));
        rightBackWheelRect.set(x + pSF * backWheelXOffset, y - pSF * backWheelYOffset, x + pSF * (backWheelXOffset + backWheelWidth), y - pSF * (backWheelYOffset - backWheelHeight));
    }

}
