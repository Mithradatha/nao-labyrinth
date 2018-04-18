package edu.fit.nao.tutorial;

        import com.aldebaran.qi.Application;
        import com.aldebaran.qi.Session;
        import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
        import edu.fit.nao.helper.ConnectionManager;

public class HelloWorld {

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

            // call advertised 'say' method
            tts.say("Hello World!");

            //=========================================

            application.run();

        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
