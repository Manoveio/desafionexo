package br.com.desafionexo.model;

import java.io.Serializable;

public class TokenVO implements Serializable {

    String authorizationType;
    String token;
    String expires;

    public TokenVO(String authorizationType, String token, String expires) {

        this.authorizationType = authorizationType;
        this.token = token;
        this.expires = expires;
    }

    public String getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return  "authorizationType:'" + authorizationType + "\'," +
                "token: '" + token + "\'," +
                "expires: '" + expires + "\'" ;
    }
}
