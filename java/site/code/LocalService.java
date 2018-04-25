package edu.fit.nao.tutorial;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.helper.ConnectionManager;
import edu.fit.nao.helper.proxies.RepeatProxy;
import edu.fit.nao.helper.services.RepeatService;

public class LocalService {

    public static void main(String[] args) {

        try {

            ConnectionManager.ConnectionInfo connectionInfo = ConnectionManager.ParseOptions(args, true);

            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            Application application = new Application(new String[]{"--qi-url", robotUrl});

            application.start();
            Session session = application.session();

            //=========================================

            // create a proxy to remote TextToSpeech service
            ALTextToSpeech tts = new ALTextToSpeech(session);

            // create a local Repeat service
            RepeatService service = new RepeatService(tts);

            // register the local service with session
            service.registerService(session);

            // create a proxy to local Repeat service
            RepeatProxy r = new RepeatProxy(session);

            // call advertised 'repeat' method
            r.repeat("Hello World!");

            //=========================================

            application.run();

        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
