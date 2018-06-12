package quotebook.theoneandonly.com.eveskillstracker;

public class SyncPack {

    private String code;
    private String host;
    private String auth;
    private String content;
    private String grant;


    SyncPack(String code, String host, String auth,
    String content, String grant){

        this.code = code;
        this.host = host;
        this.auth = auth;
        this.content = content;
        this.grant = grant;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGrant() {
        return grant;
    }

    public void setGrant(String grant) {
        this.grant = grant;
    }
}
