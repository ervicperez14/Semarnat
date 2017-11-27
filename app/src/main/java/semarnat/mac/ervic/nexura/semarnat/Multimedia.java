package semarnat.mac.ervic.nexura.semarnat;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Multimedia.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Multimedia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Multimedia extends Fragment {
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context context;
    private OnFragmentInteractionListener mListener;
    View v;
    VideoView videoView;

    public Multimedia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Multimedia.
     */
    // TODO: Rename and change types and number of parameters
    public static Multimedia newInstance() {
        Multimedia fragment = new Multimedia();
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
        context = getContext();
        v = inflater.inflate(R.layout.fragment_multimedia_chedrahui,container,false);
        videoView = (VideoView) v.findViewById(R.id.vw_multimedia);
        setUpVideoView();
        // Inflate the layout for this fragment
        return v;
    }
    private void setUpVideoView() {
        // Prepara la URI del vídeo que será reproducido.
        String uriPath = "android.resource://" + getActivity().getPackageName()
                + "/" + R.raw.elektra_aniversario_sonrisas;
        Uri uri = Uri.parse(uriPath);

        // Se crean los controles multimedia.

        CustomMediaController mediaController = new CustomMediaController(getActivity());

        // Inicializa la VideoView.
        // Asigna los controles multimedia a la VideoView.
        videoView.setMediaController(mediaController);
        try {
            // Asigna la URI del vídeo que será reproducido a la vista.
            videoView.setVideoURI(uri);
            // Se asigna el foco a la VideoView.
            videoView.requestFocus();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        /*
         * Se asigna un listener que nos informa cuando el vídeo
         * está listo para ser reproducido.
         */
        videoView.setOnPreparedListener(videoViewListener);
    }

    private MediaPlayer.OnPreparedListener videoViewListener =
            new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
            /*
             * Se indica al reproductor multimedia que el vídeo
             * se reproducirá en un loop (on repeat).
             */
                    mediaPlayer.setLooping(true);

                    if (0 == 0) {
                /*
                 * Si tenemos una posición en savedInstanceState,
                 * el vídeo debería comenzar desde aquí.
                 */
                        videoView.start();
                    } else {
                /*
                 * Si venimos de un Activity "resumed",
                 * la reproducción del vídeo será pausada.
                 */
                        videoView.pause();
                    }
                }
            };


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
}
