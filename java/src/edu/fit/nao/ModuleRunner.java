package edu.fit.nao;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;

import java.util.concurrent.TimeUnit;

public abstract class ModuleRunner {

    protected Session session;
    protected String robotUrl;

    public ModuleRunner(Session session, String robotUrl) {

        this.session = session;
        this.robotUrl = robotUrl;
    }

    public abstract void run() throws Exception;

    // timeout in seconds
    public boolean retryConnect(long timeout, int retryCount) {

        if (session.isConnected()) return true;

        for (int i = 0; i < retryCount; i++) {

            try {

                Future<Void> sessionConnected = session.connect(robotUrl);
                sessionConnected.get(timeout, TimeUnit.SECONDS);

            } catch (Exception ignored) {}

            if (session.isConnected()) return true;
        }

        return false;
    }
}
