package edu.fit.nao.module.repeat;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.Advertise;
import edu.fit.nao.CustomService;

public class RepeatService extends CustomService {

    private ALTextToSpeech tts;

    public RepeatService(ALTextToSpeech tts) {

        super("RepeatService");
        this.tts = tts;
    }

    @Advertise(signature = "repeat::v(s)", description = "Repeats given string")
    public void repeat(String str) throws InterruptedException, CallError {

        this.tts.say(str);
    }
}
