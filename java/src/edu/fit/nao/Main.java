package edu.fit.nao;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import edu.fit.nao.helper.ConnectionManager;
import edu.fit.nao.helper.ModuleRunner;
import edu.fit.nao.module.localization.LocalizationRunner;
import edu.fit.nao.module.motion.MotionRunner;
import edu.fit.nao.module.navigation.NavigationRunner;
import edu.fit.nao.module.perception.PerceptionRunner;

import java.nio.file.Paths;

public class Main {

    public static void main(String... args) {

        try {

            ConnectionManager.ConnectionInfo connectionInfo = ConnectionManager.ParseOptions(args, true);

            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            Application application = new Application(new String[]{"--qi-url", robotUrl});

            application.start();
            Session session = application.session();

            Perception(session);
            // Motion(session);
            // Repeat(session);
            // Navigation(session);
            // Localization(session);

            application.run();

        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private static void Perception(Session session) throws Exception {

        ModuleRunner perception = new PerceptionRunner(session);
        perception.run();
    }

    private static void Motion(Session session) throws  Exception {

        ModuleRunner motion = new MotionRunner(session);
        motion.run();
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
