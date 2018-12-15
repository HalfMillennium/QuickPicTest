package org.horaapps.leafpic.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import org.horaapps.leafpic.R;
import org.horaapps.leafpic.activities.MainActivity;
import org.horaapps.leafpic.data.Media;
import org.horaapps.leafpic.util.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.*;

/**
 * A Media Fragment for showing an Image (static)
 */
public class ImageFragment extends BaseMediaFragment {

    @BindView(R.id.subsampling_view) SubsamplingScaleImageView imageView;
    private AdView adView;

    @NonNull
    public static ImageFragment newInstance(@NonNull Media media) {
        return BaseMediaFragment.newInstance(new ImageFragment(), media);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo, container, false);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Uri mediaUri = media.getUri();
        imageView.setOrientation(BitmapUtils.getOrientation(mediaUri, getContext()));
        imageView.setImage(ImageSource.uri(mediaUri));
        setTapListener(imageView);

        // Instantiate an AdView object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
    }

    @Override
    public void onDestroyView() {
        imageView.recycle();
        super.onDestroyView();
    }

    /**
     * Rotate the currently displaying media image.
     *
     * @param rotationInDegrees The rotation in degrees
     */
    public void rotatePicture(int rotationInDegrees) {
        if (rotationInDegrees == -90 && imageView.getOrientation() == 0) imageView.setOrientation(SubsamplingScaleImageView.ORIENTATION_270);
        else imageView.setOrientation(Math.abs(imageView.getOrientation() + rotationInDegrees) % 360);
    }
}
