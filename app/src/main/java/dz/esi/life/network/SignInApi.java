package dz.esi.life.network;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import dz.service.delivery.DataSaver;
import dz.service.delivery.MainActivity;
import dz.service.delivery.Models.Utilisateur;

/**
 * Created by MEFTAH on 30/04/2015.
 */
public class SignInApi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    MainActivity activity;
    private static boolean mResolvingError = false;
    private GoogleApiClient mGoogleApiClient;
    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;
    private static final String TAG = "SignInApi";
    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private ProgressDialog mConnectionProgressDialog;
    private ConnectionResult mConnectionResult;
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    /**
     * True if the sign-in button was clicked.  When true, we know to resolve all
     * issues preventing sign-in without waiting.
     */
    private boolean mSignInClicked;

    /**
     * True if we are in the process of resolving a ConnectionResult
     */
    private boolean mIntentInProgress;

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mIntentInProgress) {
            if (mSignInClicked && result.hasResolution()) {
                // The user has already clicked 'sign-in' so we attempt to resolve all
                // errors until the user is signed in, or they cancel.
                try {
                    result.startResolutionForResult(activity, RC_SIGN_IN);
                    mIntentInProgress = true;
                } catch (IntentSender.SendIntentException e) {
                    // The intent was canceled before it was sent.  Return to the default
                    // state and attempt to connect to get an updated ConnectionResult.
                    mIntentInProgress = false;
                    mGoogleApiClient.connect();
                }
            }
        }
    }
    public void onResult(int requestCode,int responseCode){
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != activity.RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.reconnect();
            }
        }
    }

    public SignInApi(MainActivity activity) {
        this.activity = activity;
        Plus.PlusOptions options = new Plus.PlusOptions.Builder()
                .addActivityTypes("http://schemas.google.com/AddActivity",
                        "http://schemas.google.com/ReviewActivity")
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(Plus.API, options)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }
    public void connect(){
        Log.d("SignInAPi", "Attempt to sign in");
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            mGoogleApiClient.connect();
        }else Log.d(TAG, "is already connected");
    }
    public void disconnect(){
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }

    public void signOut(){
        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
        Plus.AccountApi
                .revokeAccessAndDisconnect(mGoogleApiClient)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Log.d("SignOut", "Disconnected");
                    }
                });
    }
    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
        Toast.makeText(activity, "User is connected!", Toast.LENGTH_LONG).show();
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName()+currentPerson.getCurrentLocation()
                    +currentPerson.getGender()
                   +currentPerson.getName().getFamilyName()+currentPerson.getName().getGivenName()
                   + currentPerson.getAgeRange().getMin();
            Person.Image personPhoto = currentPerson.getImage();
            String personGooglePlusProfile = currentPerson.getUrl();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            Toast.makeText(activity, "User: " + personName + ", email: " + email + ", id: " + personGooglePlusProfile, Toast.LENGTH_LONG).show();
            Log.d(TAG, personName);
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.username = currentPerson.hasNickname() ?currentPerson.getNickname():"";
            utilisateur.firstName = currentPerson.hasName() ?currentPerson.getName().hasFamilyName()
                    ?currentPerson.getName().getFamilyName():"":"";
            utilisateur.lastName =currentPerson.hasName() ?currentPerson.getName().hasGivenName()
                    ?currentPerson.getName().getGivenName():"":"";
            utilisateur.displayName = currentPerson.hasDisplayName() ? currentPerson.getDisplayName():"";
            utilisateur.email = email;
            utilisateur.address = currentPerson.hasCurrentLocation() ?currentPerson.getCurrentLocation():"";
            utilisateur.sexe = currentPerson.hasGender() ? String.valueOf(currentPerson.getGender()):"0";
            utilisateur.photo = currentPerson.hasImage() ?currentPerson.getImage().toString():"";
            utilisateur.age = currentPerson.hasAgeRange() ?currentPerson.getAgeRange().hasMin()
                    ? String.valueOf(currentPerson.getAgeRange().getMin()):"":"";
            DataSaver.utilisateur = utilisateur;

            Toast.makeText(activity, "Connecté", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    // The rest of this code is all about building the error dialog

    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(activity.getSupportFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public static void onDialogDismissed() {
        mResolvingError = false;
    }
    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            SignInApi.onDialogDismissed();
        }
    }
}
