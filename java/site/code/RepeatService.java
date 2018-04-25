package edu.fit.nao.helper.services;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.helper.Advertise;
import edu.fit.nao.helper.CustomService;

public class RepeatService extends CustomService {

    private ALTextToSpeech tts;

    public RepeatService(ALTextToSpeech tts) throws Exception {

        super("RepeatService");
        this.tts = tts;
    }

    // signature => <method_name>::<return_type>(<parameter_types>)
    @Advertise(signature = "repeat::v(s)", description = "Repeats given string")
    public void repeat(String str) throws InterruptedException, CallError {

        this.tts.say(str);
    }
}
