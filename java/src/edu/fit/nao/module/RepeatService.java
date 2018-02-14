package edu.fit.nao.module;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.QiService;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class RepeatService extends QiService {

    private ALTextToSpeech tts;

    public RepeatService(Session session) throws Exception {

       this.tts = new ALTextToSpeech(session);
    }

    public void repeat(String str) throws InterruptedException, CallError {

        this.tts.say(str);
    }
}
