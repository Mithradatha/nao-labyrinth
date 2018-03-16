package edu.fit.nao;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;
import edu.fit.nao.module.localization.LocalizationRunner;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String... args) {

        try {

            ConnectionInfo connectionInfo = Util.ParseOptions(args, true);

            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            Application application = new Application(new String[]{"--qi-url", robotUrl});

            Session session = new Session();
            Future<Void> future = session.connect(robotUrl);
            future.get(3, TimeUnit.SECONDS);

            ModuleRunner localization = new LocalizationRunner(session, 0.2f);
            localization.run();

//            String fileName = Paths.get(".", "res", "1.map").normalize().toAbsolutePath().toString();
//            ModuleRunner navigation = new NavigationRunner(session, 9, 9, fileName);
//            navigation.run();

            application.run();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
