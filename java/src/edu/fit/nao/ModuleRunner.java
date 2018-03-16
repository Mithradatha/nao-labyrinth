package edu.fit.nao;

import com.aldebaran.qi.Session;

public abstract class ModuleRunner {

    protected final Session session;

    public ModuleRunner(Session session) { this.session = session; }

    public abstract void run() throws Exception;
}
