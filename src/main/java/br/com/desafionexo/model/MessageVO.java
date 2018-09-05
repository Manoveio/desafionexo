package br.com.desafionexo.model;

import java.io.Serializable;


public class MessageVO implements Serializable {

    private String id;
    private String date;
    private InputVO input;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InputVO getInput() {
        return input;
    }

    public void setInput(InputVO input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "{" +
                "'id': '" + id + '\'' +
                ", 'date': '" + date + '\'' +
                ", 'input': {" + input +  " }" +
                "  }";
    }
}
