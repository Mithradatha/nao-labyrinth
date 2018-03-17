package edu.fit.nao.module.repeat;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.helper.ALProxy;

class AsyncRepeatProxy extends ALProxy {

    protected AsyncRepeatProxy() {}

    public Future<Void> repeat(String str) throws CallError {

        return this.call("repeat", str);
    }
}
