package pl.softpartner.hangman;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnGameStatusListener, OnEndGameDialogClickListener {

    private GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameFragment = new GameFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, gameFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLetterClick(View v) {
        if (v instanceof TextView) {
            gameFragment.showCharacter(((TextView) v).getText().toString().charAt(0));
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onGameStatus(int status) {
        EndGameDialogFragment endGameDialogFragment = EndGameDialogFragment.newInstance(status, GameFragment.word);
        endGameDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onPositiveClickListener() {
        gameFragment = null;
        gameFragment = new GameFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, gameFragment).commit();
    }
}
