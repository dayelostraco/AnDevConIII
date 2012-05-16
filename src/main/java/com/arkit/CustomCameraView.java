package com.arkit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CustomCameraView extends SurfaceView {
    Camera camera;
    SurfaceHolder previewHolder;

    //Callback for the surfaceholder
    SurfaceHolder.Callback surfaceHolderListener = new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();

            try {
                camera.setPreviewDisplay(previewHolder);
            } catch (Throwable t) {

            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h) {
            //Parameters params = camera.getParameters();
            //params.setPreviewSize(w, h);
            //params.setPictureFormat(PixelFormat.JPEG);
            //camera.setParameters(params);
            camera.startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder arg0) {
            // TODO Auto-generated method stub
            camera.stopPreview();
            camera.release();

        }
    };

    public CustomCameraView(Context ctx) {
        super(ctx);

        previewHolder = this.getHolder();
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        previewHolder.addCallback(surfaceHolderListener);
        setBackgroundColor(Color.TRANSPARENT);
    }

    public CustomCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //CompassListener.sensorMan.get
        //Paint p = new Paint();
        //p.setColor(Color.WHITE);
        //canvas.drawText(String.valueOf(CompassListener.direction), 170, 0, new Paint());
    }

    public void closeCamera() {
        if (camera != null)
            camera.release();
    }

    public void dispatchDraw(Canvas c) {
        super.dispatchDraw(c);
        //Log.i("Drawing","Got Dispatch!");
    }
}
