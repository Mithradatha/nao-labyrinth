package edu.fit.nao.module;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.ALProxy;

public class RepeatProxy extends ALProxy {

    private AsyncRepeatProxy asyncRepeatProxy = new AsyncRepeatProxy();

    public RepeatProxy(Session session, String serviceName) throws Exception {

        super(session, serviceName);
        this.asyncRepeatProxy.setService(this.getService());
    }

    public AsyncRepeatProxy async() {

        return this.asyncRepeatProxy;
    }

    public void repeat(String str) throws CallError, InterruptedException {

        this.call("repeat", str).get();
    }
}
