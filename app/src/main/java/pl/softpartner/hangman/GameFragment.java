package pl.softpartner.hangman;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameFragment extends Fragment {

    private TextView wordTextView;
    private ImageView image;
    public static String word = null;
    private int imageNumber = 0;

    public static final int SUCCESS = 1;
    public static final int FAILURE = -1;

    private OnGameStatusListener onGameStatusListener;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wordTextView = (TextView) view.findViewById(R.id.wordTextView);
        image = (ImageView) view.findViewById(R.id.hangman_image);
        String word = randomWord();
        prepareGame(word);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onGameStatusListener = (OnGameStatusListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onGameStatusListener = null;
        image = null;
    }

    private void prepareGame(String word) {
        // Toast.makeText(getActivity(),"dupa2",Toast.LENGTH_SHORT).show();
        imageNumber = 0;
        this.word = word;
        image.setImageDrawable(null);
        int wordLength = word.length();
        String temp = "";
        for (int i = 0; i < wordLength; i++)
            temp += "_";
        wordTextView.setText(temp);
    }

    public void showCharacter(char s) {
        char temp[] = word.toCharArray();
        char tempTextView[] = wordTextView.getText().toString().toCharArray();
        if (word.contains("" + s))
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == s)
                    tempTextView[i] = s;
            }
        else
            nextImage();
        if (!new String(tempTextView).contains("_"))
            onGameStatusListener.onGameStatus(SUCCESS);
        wordTextView.setText(new String(tempTextView));
    }

    private void nextImage() {
        image.setImageDrawable(null);
        imageNumber++;
        switch (imageNumber) {
            case 1:
                image.setImageResource(R.drawable.hangman1);
                break;
            case 2:
                image.setImageResource(R.drawable.hangman2);
                break;
            case 3:
                image.setImageResource(R.drawable.hangman3);
                break;
            case 4:
                image.setImageResource(R.drawable.hangman4);
                break;
            case 5:
                image.setImageResource(R.drawable.hangman5);
                break;
            case 6:
                image.setImageResource(R.drawable.hangman6);
                break;
            case 7:
                image.setImageResource(R.drawable.hangman7);
                break;
            case 8:
                image.setImageResource(R.drawable.hangman8);
                break;
            case 9:
                image.setImageResource(R.drawable.hangman9);
                wordTextView.setText(word);
                onGameStatusListener.onGameStatus(FAILURE);
                break;
        }
    }

    public void reset() {
        word = randomWord();
    }

    private String randomWord() {
        String words[] = getResources().getStringArray(R.array.words);
        return words[new Random().nextInt(words.length)].toUpperCase();
    }
}
