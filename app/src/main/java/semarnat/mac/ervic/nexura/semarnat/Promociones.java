package semarnat.mac.ervic.nexura.semarnat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.widget.ZoomControls;

import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Promociones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Promociones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Promociones extends Fragment {
    ViewFlipper viewFlipper;
    ImageView iv_promo1, iv_promo2, iv_promo3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Promociones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Promociones.
     */
    // TODO: Rename and change types and number of parameters
    public static Promociones newInstance() {
        Promociones fragment = new Promociones();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_promociones, container, false);
        viewFlipper = (ViewFlipper)v.findViewById(R.id.vf);
        final GestureDetector gestureDetector;
        gestureDetector = new GestureDetector(new MyGestureDetector());
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (gestureDetector.onTouchEvent(motionEvent)) {
                    return false;
                } else {
                    return true;
                }

            }
        });
        iv_promo1 = (ImageView) v.findViewById(R.id.iv_promocion1);
        iv_promo2 = (ImageView) v.findViewById(R.id.iv_promocion2);
        iv_promo3 = (ImageView) v.findViewById(R.id.iv_promocion3);

        iv_promo1.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.catalogo_elektra01,300,300));
        iv_promo2.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.catalogo_elektra02,300,300));
        iv_promo3.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.catalogo_elektra03,300,300));
        final ZoomControls zc;



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private Bitmap getScaledBitMapBaseOnScreenSize(Bitmap bitmapOriginal){

        Bitmap scaledBitmap=null;
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);


            int width = bitmapOriginal.getWidth();
            int height = bitmapOriginal.getHeight();

            float scaleWidth = metrics.scaledDensity;
            float scaleHeight = metrics.scaledDensity;

            // create a matrix for the manipulation
            Matrix matrix = new Matrix();
            // resize the bit map
            matrix.postScale(scaleWidth, scaleHeight);

            // recreate the new Bitmap
            scaledBitmap = Bitmap.createBitmap(bitmapOriginal, 0, 0, width, height, matrix, true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return scaledBitmap;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 250;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            System.out.println(" in onFling() :: ");
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                viewFlipper.setInAnimation(getContext(),android.R.anim.fade_in);
                viewFlipper.setOutAnimation(getContext(), android.R.anim.fade_out);
                viewFlipper.showNext();
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                viewFlipper.setInAnimation(getContext(),android.R.anim.fade_in);
                viewFlipper.setOutAnimation(getContext(), android.R.anim.fade_out);
                viewFlipper.showPrevious();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
