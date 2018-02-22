package edu.fit.nao;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.DynamicObjectBuilder;
import com.aldebaran.qi.QiService;
import com.aldebaran.qi.Session;
import edu.fit.nao.module.landmarkdetection.LandMarkDetectionTest;
import edu.fit.nao.module.repeat.RepeatProxy;
import edu.fit.nao.module.repeat.RepeatService;

import java.util.Scanner;

public class Main {

    public static void main(String... args) {

        try {

            ConnectionInfo connectionInfo = Util.ParseOptions(args, true);

            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            Application application = new Application(new String[]{"--qi-url", robotUrl});
            application.start();

            Session session = application.session();

            ModuleRunner landmarkTest = new LandMarkDetectionTest(session, robotUrl);
            landmarkTest.run();

            // Main.run(session);

            application.run();

        } catch (Exception ex) {

            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void run(Session session) throws Exception {

        QiService repeatService = new RepeatService(null);

        DynamicObjectBuilder objBuilder = new DynamicObjectBuilder();
        objBuilder.advertiseMethod("repeat::v(s)", repeatService, "Repeats string");

        repeatService.init(objBuilder.object());
        session.registerService("RepeatService", objBuilder.object());

        RepeatProxy proxy = new RepeatProxy(session);

        boolean running = true;
        System.out.println("Type 'exit' to leave...");

        Scanner reader = new Scanner(System.in);

        do {

            System.out.print(">> ");
            String str = reader.nextLine();

            if (str.equals("exit")) running = false;
            else {

                proxy.repeat(str);
                System.out.println();
            }

        } while (running);

        proxy.repeat("Bye, Bye.");
        reader.close();
    }
}
