package it.jaschke.alexandria.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by saj on 11/01/15.
 */
public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    Bitmap bmPlaceHolder;

    public DownloadImage(ImageView bmImage, Bitmap placeHolderImage) {
        this.bmImage = bmImage;
        this.bmPlaceHolder = placeHolderImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap bookCover = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            bookCover = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bookCover;
    }

    protected void onPostExecute(Bitmap result) {
        if (bmImage != null)
        {
            if (result != null) {
                bmImage.setImageBitmap(result);
            } else {
                bmImage.setImageBitmap(bmPlaceHolder);
            }
        }

    }
}

