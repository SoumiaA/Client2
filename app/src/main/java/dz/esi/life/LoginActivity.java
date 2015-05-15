package dz.esi.life;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dz.esi.life.Model.User;
import dz.esi.life.network.RestClient;


public class LoginActivity extends ActionBarActivity {
    private String redirectUri = "http://frozen-peak-9792.herokuapp.com/auth/google/callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification);
        Button signIn = (Button) findViewById(R.id.signIn);
        ImageView i = (ImageView) findViewById(R.id.image);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        Button signInWithGoogle = (Button) findViewById(R.id.signInWithGoogle);
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(RestClient.ROOT + "/auth/google"));
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = User.signIn(username.getText().toString(), password.getText().toString());
                if (user != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            Log.d("uri", uri.toString());
            String code = uri.getQueryParameter("code");
            if (code != null) {
                User user = User.signInWithGoogle(code);
                if (user != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            } else if (uri.getQueryParameter("error") != null) {
                Toast.makeText(getApplicationContext(), "Authentification echoue", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
