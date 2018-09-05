package br.com.desafionexo.model;

import java.io.Serializable;

public class ResponseCredencialsVO  implements Serializable {


    AccountCredentialsVO accountCredentials;
    TokenVO token;

    public ResponseCredencialsVO(AccountCredentialsVO accountCredentials, TokenVO token) {
        this.accountCredentials = accountCredentials;
        this.token = token;
    }

    public AccountCredentialsVO getAccountCredentials() {
        return accountCredentials;
    }

    public void setAccountCredentials(AccountCredentialsVO accountCredentials) {
        this.accountCredentials = accountCredentials;
    }

    public TokenVO getToken() {
        return token;
    }

    public void setToken(TokenVO token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" + accountCredentials.toString() + "}" +
                "{" + token.toString() + "}";
    }
}
