package ninjawar.oauth2;

import com.badlogic.gdx.Gdx;
import java.io.InputStream;
import ninjawar.myscr.Res;
import ninjawar.screen.LoginScr;

public class AppleAuth implements AppleLogin {
    private AppleSigninUtil appleSignin;

    public AppleAuth(String keyId, String teamId, String clientId, String redirectUri, InputStream privateKeyStream, InputStream publicKeyStream) throws Exception {
        this.appleSignin = new AppleSigninUtil(keyId, teamId, clientId, redirectUri, privateKeyStream, publicKeyStream);
    }

    public void signInApple() {
        String state = Res.getTextRandom(18);
        Gdx.net.openURI("https://appleccng.teamobi.com/LoginApple.html" + "?state=" + state);
        LoginScr.gI().continueLoginOAuth2(state, state);
    }

    public void signOutApple() {
    }

    public boolean isLoggedInApple() {
        return false;
    }
}
