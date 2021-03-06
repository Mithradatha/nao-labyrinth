package edu.fit.nao.helper.proxies;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.ALProxy;

public class RepeatProxy extends ALProxy {

    private AsyncRepeatProxy asyncRepeatProxy = new AsyncRepeatProxy();

    public RepeatProxy(Session session) throws Exception {

        super(session, "RepeatService");
        this.asyncRepeatProxy.setService(this.getService());
    }

    public AsyncRepeatProxy async() {

        return this.asyncRepeatProxy;
    }

    public void repeat(String stringToRepeat) throws CallError, InterruptedException {

        this.call("repeat", stringToRepeat).get();
    }
}
