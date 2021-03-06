package com.github.randoapp.camera;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.github.randoapp.Constants;
import com.github.randoapp.R;
import com.github.randoapp.db.RandoDAO;
import com.github.randoapp.db.model.RandoUpload;
import com.github.randoapp.preferences.Preferences;
import com.github.randoapp.util.BitmapUtil;
import com.makeramen.RoundedImageView;

import java.util.Date;

import static com.github.randoapp.Constants.CAMERA_BROADCAST_EVENT;

public class CameraUploadFragment extends SherlockFragment {

    private String picFileName;
    private RoundedImageView preview;
    private ImageView uploadButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.camera_upload, container, false);

        preview = (RoundedImageView) rootView.findViewById(R.id.preview);

        WindowManager windowManager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int previewSize = Math.min(display.getWidth(), display.getHeight());

        Bundle bundle = getArguments();
        picFileName = bundle.getString(Constants.FILEPATH);
        preview.setImageBitmap(BitmapUtil.decodeSampledBitmap(picFileName, previewSize, previewSize));

        uploadButton = (ImageView) rootView.findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new UploadButtonListner());
        return rootView;
    }

    private class UploadButtonListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (picFileName == null) {
                return;
            }

            Location location = Preferences.getLocation();
            RandoDAO.addToUpload(new RandoUpload(picFileName, location.getLatitude(), location.getLongitude(), new Date()));

            Intent intent = new Intent(CAMERA_BROADCAST_EVENT);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        }
    }
}
