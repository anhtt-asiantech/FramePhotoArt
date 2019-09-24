package com.anhttvn.framesphotoart.version_2.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anhttvn.cropphoto_anhtt.Crop;
import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.adapter.FrameAdapter;
import com.anhttvn.framesphotoart.adapter.IconAdapter;
import com.anhttvn.framesphotoart.util.Config;
import com.anhttvn.framesphotoart.util.DialogView;
import com.anhttvn.framesphotoart.util.StickerImageView;
import com.anhttvn.framesphotoart.util.StickerTextView;
import com.anhttvn.framesphotoart.util.TextStickerView;
import com.anhttvn.textinputlibary.Textinput;
import com.anhttvn.textinputlibary.model.Eventbus;
import com.anhttvn.textinputlibary.model.IconText;
import com.google.android.gms.ads.AdView;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhotoArt extends BaseActivity implements FrameAdapter.OnSelectFrame,View.OnTouchListener ,
        IconAdapter.OnclickSelectIcon{
    private int keyValue;
    private ImageView mPhoto, frame;
    private static final int tienanhvn = 123;
    private RecyclerView mList;
    private Button btn_select_frame, btn_select_text, btn_select_icon,btn_save,btn_close;
    private FrameAdapter mAdapterFrame;
    private IconAdapter mAdapterIcon;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> listIcon = new ArrayList<>();
    private AdView ads_qc_3;
    private Bitmap bmp, bmpSend;

    RelativeLayout.LayoutParams parms;
    private int startwidth;
    private int startheight;
    float dx = 0, dy = 0, x = 0, y = 0;
    float angle = 0;
    float scalediff;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float newRot = 0f;
    private float d = 0f;
    private IconText mText;
    private StickerTextView mTextinput;
    private StickerImageView mStickerIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_art);
        Eventbus.getBus().register(this);
        runFullBanner();
        getData();
        init();
        checkText();
        setFont();
    }

    private void getData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            keyValue = extras.getInt("keyValue");
            checkNumber(keyValue);
        }
    }

    private void checkNumber(int position) {
        if (position == 1) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, tienanhvn);
        } else if (position == 2) {
            Crop.pickImage(this);
        }
    }

    private void init() {
        mPhoto = findViewById(R.id.photo);
        mList = findViewById(R.id.list_item);
        btn_select_frame = findViewById(R.id.select_frame);
        ads_qc_3 = findViewById(R.id.ads_qc_3);
        frame = findViewById(R.id.frame);
        btn_select_text = findViewById(R.id.select_text);
        mTextinput = findViewById(R.id.textinput);
        btn_select_icon = findViewById(R.id.select_icon);
        mStickerIcon = findViewById(R.id.stickerIcon);
        btn_save = findViewById(R.id.select_save);
        btn_close = findViewById(R.id.select_close);
        list = new Config(this).getPhoto();
        listIcon = new Config(this).getIcon();
        this.mStickerIcon.setVisibility(View.GONE);
        runAdview(ads_qc_3);
        if (checkInterNet()) {
            mList.setVisibility(View.GONE);
            ads_qc_3.setVisibility(View.VISIBLE);
        } else {
            mList.setVisibility(View.VISIBLE);
            ads_qc_3.setVisibility(View.GONE);
            adapterFrame();
        }
        viewItem(0);
        onClickSelectFrame();
        mPhoto.setOnTouchListener(this);

    }

    private void setFont(){
        Typeface type = Typeface.createFromAsset(getAssets(),"font/Beyond Wonderland.ttf");
        btn_select_frame.setTypeface(type);
        btn_select_text.setTypeface(type);
        btn_select_icon.setTypeface(type);
        btn_save.setTypeface(type);
        btn_close.setTypeface(type);
        mTextinput.tv_main.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
    }
    private void checkText() {
        if (mText != null) {
            mTextinput.setVisibility(View.VISIBLE);
        } else {
            mTextinput.setVisibility(View.GONE);
        }
    }

    /**
     * select frame
     */
    private void onClickSelectFrame() {
        btn_select_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.setVisibility(View.VISIBLE);
                ads_qc_3.setVisibility(View.GONE);
                adapterFrame();

            }
        });
        btn_select_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Textinput.class));
            }
        });
        btn_select_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.setVisibility(View.VISIBLE);
                ads_qc_3.setVisibility(View.GONE);
                adapterIcon();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSave();
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickCancel();
            }
        });
    }

    @Subscribe
    public void getIconText(IconText data) {
        mText = new IconText();
        mText = data;
        mTextinput.setText(data.getName());
        mTextinput.tv_main.setTypeface(data.getFont());
        mTextinput.tv_main.setTextColor(data.getColor());
        mTextinput.tv_main.setTextSize(data.getSize());
        checkText();
    }

    /**
     * adapter frame
     */
    private void adapterFrame() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mList.setLayoutManager(layoutManager);
        mAdapterFrame = new FrameAdapter(list, this, this);
        mList.setAdapter(mAdapterFrame);

    }

    private void adapterIcon() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mList.setLayoutManager(layoutManager);
        mAdapterIcon = new IconAdapter(listIcon,this,this);
        mList.setAdapter(mAdapterIcon);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == tienanhvn && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mPhoto.setImageBitmap(photo);
        }
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(data.getData());

        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        } else {
            finish();
        }
    }

    @Override
    public void onSelectFame(int position) {
        viewItem(position);
    }



    private void viewItem(int position) {

        InputStream inputstream = null;
        try {
            inputstream = this.getAssets().open("image/"
                    + list.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        frame.setImageDrawable(drawable);
        bmp = ((BitmapDrawable) drawable).getBitmap();
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            mPhoto.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_CANCELED) {
            finish();
        }
//        Point point = new Point();
//        point.set(bmp.getWidth(),bmp.getHeight());
//        getTransparentCenter(bmp,point);

    }
    private PointF getTransparentCenter(Bitmap bitmap, Point viewSize){
        List<Point> transparentPonit = new ArrayList<>();
        for(int i= 0 ;i<bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int pixel = bitmap.getPixel(i, j);
                if ((pixel & 0xff000000) == 0) {
                    transparentPonit.add(new Point(i, j));
                }
            }
        }
        int totalX = 0;
        int totalY = 0;
        for(Point transparent : transparentPonit){
            totalX += transparent.x;
            totalY += transparent.y;
        }
        float centerX = (float) totalX/transparentPonit.size();
        float centerY = (float) totalY/transparentPonit.size();
        float x = viewSize.x * centerX / bitmap.getWidth();
        float y = viewSize.y *centerY / bitmap.getHeight();
        mPhoto.setX(x);
        mPhoto.setY(y);
        removeTransparentPixels(bitmap);
        return new PointF(x,y);
    }
    public  Bitmap removeTransparentPixels(Bitmap image) {

        int x1 = image.getWidth();
        int y1 = image.getHeight();

        int width = 0, height = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getPixel(x, y) != Color.TRANSPARENT) {
                    if (x < x1) {
                        x1 = x;
                    } else if (x > width) {
                        width = x;
                    }

                    if (y < y1) {
                        y1 = y;
                    } else if (y > height) {
                        height = y;
                    }
                }
            }
        }

        width = width - x1;
        height = height - y1;
        mPhoto.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        return Bitmap.createBitmap(image, x1, y1, width, height);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final ImageView view = (ImageView) v;
        mPhoto = view;
        ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                startwidth = parms.width;
                startheight = parms.height;
                dx = event.getRawX() - parms.leftMargin;
                dy = event.getRawY() - parms.topMargin;
                mode = DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    mode = ZOOM;
                }

                d = rotation(event);

                break;
            case MotionEvent.ACTION_UP:

                break;

            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;

                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {

                    x = event.getRawX();
                    y = event.getRawY();

                    parms.leftMargin = (int) (x - dx);
                    parms.topMargin = (int) (y - dy);

                    parms.rightMargin = 0;
                    parms.bottomMargin = 0;
                    parms.rightMargin = parms.leftMargin + (5 * parms.width);
                    parms.bottomMargin = parms.topMargin + (10 * parms.height);

                    view.setLayoutParams(parms);

                } else if (mode == ZOOM) {

                    if (event.getPointerCount() == 2) {

                        newRot = rotation(event);
                        float r = newRot - d;
                        angle = r;

                        x = event.getRawX();
                        y = event.getRawY();

                        float newDist = spacing(event);
                        if (newDist > 10f) {
                            float scale = newDist / oldDist * view.getScaleX();
                            if (scale > 0.6) {
                                scalediff = scale;
                                view.setScaleX(scale);
                                view.setScaleY(scale);

                            }
                        }

                        view.animate().rotationBy(angle).setDuration(1000).setInterpolator(new LinearInterpolator()).start();

                        x = event.getRawX();
                        y = event.getRawY();

                        parms.leftMargin = (int) ((x - dx) + scalediff);
                        parms.topMargin = (int) ((y - dy) + scalediff);

                        parms.rightMargin = 0;
                        parms.bottomMargin = 0;
                        parms.rightMargin = parms.leftMargin + (5 * parms.width);
                        parms.bottomMargin = parms.topMargin + (10 * parms.height);
                        view.setLayoutParams(parms);
                    }
                }
                break;
        }
        mPhoto.invalidate();
        return true;


    }
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);

        return (float) Math.toDegrees(radians);
    }
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public void selectIcon(int position) {
        mStickerIcon.setVisibility(View.VISIBLE);
        InputStream inputstream = null;
        try {
            inputstream = this.getAssets().open("icon/"
                    + listIcon.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        mStickerIcon.setImageDrawable(drawable);


    }
    /**
     * click save
     */
    public void onclickSave(){
        //img_view.setControlsVisibility(false);
        final DialogView dialogView = new DialogView(this);
        dialogView.showDialo("Save Photo", getString(R.string.qs_save));
        dialogView.clickCancel();
        dialogView.dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View content = findViewById(R.id.rl_photo);
                content.setDrawingCacheEnabled(true);
                Bitmap bitmap = content.getDrawingCache();
                SaveImage(bitmap);
                content.setDrawingCacheEnabled(false);
                dialogView.dialog.dismiss();
                finish();
            }
        });
        dialogView.dialog.findViewById(R.id.btn_cancel_dg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dialog.dismiss();
            }
        });
    }
    /**
     * save photo in sdcad
     * @param finalBitmap
     */
    private void SaveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PhotoArt");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "PhotoArt-"+  System.currentTimeMillis() +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * click cancel on function
     */
    public void onclickCancel(){
        final DialogView dialogView = new DialogView(this);
        dialogView.showDialo("Cancel", getString(R.string.qs_cancel));
        dialogView.clickCancel();
        dialogView.dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showADS();
                dialogView.dialog.dismiss();
                finish();

            }
        });
        dialogView.dialog.findViewById(R.id.btn_cancel_dg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
       onclickCancel();
    }
}
