package edu.fit.nao;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import edu.fit.nao.module.localization.LocalizationRunner;
import edu.fit.nao.module.navigation.NavigationRunner;
import edu.fit.nao.module.repeat.RepeatRunner;

import java.nio.file.Paths;

public class Main {

    public static void main(String... args) {

        try {

            ConnectionManager.ConnectionInfo connectionInfo = ConnectionManager.ParseOptions(args, true);

            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            Application application = new Application(new String[]{"--qi-url", robotUrl});

            application.start();
            Session session = application.session();

            // Repeat(session);
            // Navigation(session);
            // Localization(session);

            application.run();

        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private static void Repeat(Session session) throws Exception {

        ModuleRunner repeat = new RepeatRunner(session);
        repeat.run();
    }

    private static void Navigation(Session session) throws Exception {

        String fileName = Paths.get(".", "res", "1.map").normalize().toAbsolutePath().toString();
        ModuleRunner navigation = new NavigationRunner(session, 9, 9, fileName);
        navigation.run();
    }

    private static void Localization(Session session) throws Exception {

        ModuleRunner localization = new LocalizationRunner(session, 0.105f);
        localization.run();
    }
}
