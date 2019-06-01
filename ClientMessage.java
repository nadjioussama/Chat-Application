/*
 * Copyright (c) ITKAN.corp
 */

import java.io.Serializable;

public class ClientMessage implements Serializable {

    private String message;
    private String name;
    private Thread assosiatedThread;


    ClientMessage(String name, String message, Thread assosiatedThread) {
        this.name = name;
        this.message = message;
        this.assosiatedThread = assosiatedThread;
    }

    String getMessage() {
        return message;
    }
    void setMessage(String message) {
        this.message = message;
    }


    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }


    public Thread getAssosiatedThread() {
        return assosiatedThread;
    }
    void setAssosiatedThread(Thread assosiatedThread) {
        this.assosiatedThread = assosiatedThread;
    }
}
