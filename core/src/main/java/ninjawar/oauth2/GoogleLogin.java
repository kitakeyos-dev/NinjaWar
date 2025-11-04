package ninjawar.oauth2;

public interface GoogleLogin {
    boolean isLoggedInGoogle();

    void signInGoogle();

    void signOutGoogle();
}
