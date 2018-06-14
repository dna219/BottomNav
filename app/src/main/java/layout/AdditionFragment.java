package layout;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.bottomnav.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AdditionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdditionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthStateListener;
    CallbackManager mCallbackManager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG = "Addition Fragment";
    private TextView fb_text;
    private LoginButton loginButton;
    private TextView fb_name, fb_email;
    private ImageView fb_avatar;
    private Button btn_logout;

    public AdditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment AdditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdditionFragment newInstance() {
        AdditionFragment fragment = new AdditionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addition, container, false);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        loginButton = (LoginButton) view.findViewById(R.id.button_facebook_login);
        btn_logout = (Button) view.findViewById(R.id.fb_logout);

        fb_name = (TextView) view.findViewById(R.id.fb_name);
        fb_email = (TextView) view.findViewById(R.id.fb_email);
        fb_avatar = (ImageView) view.findViewById(R.id.fb_avatar);

        // Initialize Facebook Login button
        initFBFacebookLogIn();
        showDataUser();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                fb_name.setText("Đăng nhập bằng Facebook");
                fb_email.setText("Để trải nghiệm nhiều hơn");
                Picasso.get().load("https://media.lamsao.com//Resources/Data/News/Auto/trangbh/201303/33078647e34895a80aa12e4f1464a82c.jpg").into(fb_avatar);
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    // [START auth_with_facebook]
    private void initFBFacebookLogIn() {

        mCallbackManager = CallbackManager.Factory.create();
        loginButton.setFragment(this);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }

        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(AdditionFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String message;
                        if (task.isSuccessful()) {
                            message = "success firebaseAuthWithFacebook";
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            message = "fail firebaseAuthWithFacebook";
                        }
                    }
                });
    }

    private void showDataUser() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri urlAvatar = user.getPhotoUrl();

                    fb_name.setText(name);
                    fb_email.setText(email);
                    Picasso.get().load(urlAvatar).resize(80, 80).into(fb_avatar);
                }
            }
        };
    }

}
