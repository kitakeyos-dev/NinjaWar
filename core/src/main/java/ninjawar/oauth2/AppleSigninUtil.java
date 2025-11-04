package ninjawar.oauth2;

import java.io.InputStream;

public class AppleSigninUtil {
    private String clientId;
    private String keyId;
    private String redirectUri;
    private String teamId;

    public AppleSigninUtil(String keyId2, String teamId2, String clientId2, String redirectUri2, InputStream privateKeyStream, InputStream publicKeyStream) throws Exception {
        this.keyId = keyId2;
        this.teamId = teamId2;
        this.clientId = clientId2;
        this.redirectUri = redirectUri2;
    }
}
