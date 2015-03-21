package pl.softpartner.hangman;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndGameDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndGameDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String GAME_STATUS = "game_status";
    private OnEndGameDialogClickListener onEndGameDialogClickListener;

    // TODO: Rename and change types of parameters
    private int status;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param status Game end status.
     * @return A new instance of fragment EndGameDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EndGameDialogFragment newInstance(int status, String word) {
        EndGameDialogFragment fragment = new EndGameDialogFragment();
        Bundle args = new Bundle();
        args.putInt(GAME_STATUS, status);
        args.putString("word", word);
        fragment.setArguments(args);
        return fragment;
    }

    public EndGameDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onEndGameDialogClickListener = (OnEndGameDialogClickListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onEndGameDialogClickListener = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (getArguments() != null) {
            status = getArguments().getInt(GAME_STATUS);
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(status == GameFragment.SUCCESS ? "Wygrałeś" : "Przegrałeś")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onEndGameDialogClickListener.onPositiveClickListener();
                        dialog.cancel();
                    }
                });
        if (status == GameFragment.FAILURE)
            builder.setMessage("Prawidłowe hasło: " + getArguments().getString("word", ""));
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
