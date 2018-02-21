package edu.fit.nao;

import com.aldebaran.qi.Session;
import edu.fit.nao.module.RepeatProxy;

import java.util.Scanner;

public class Main {

    public static void main(String... args) {

        try {

            ConnectionInfo connectionInfo = NaoUtil.ParseOptions(args);
            NaoRunnable test = new LandmarkTest(connectionInfo);
            test.run();

        } catch (Exception ex) {

            ex.printStackTrace();
            System.exit(1);
        }


//        try {
//
//
//
////            Session session = new Session();
////            Future<Void> future = session.connect("tcp:://192.168.1.5:9559");
////            future.get(1, TimeUnit.SECONDS);
//
//            //String robotUrl = "tcp://" + connectionInfo.ip + ":" + connectionInfo.port;
//            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
//            System.out.println(robotUrl);
//
//            Application app = new Application(robotUrl);
//
//            app.start();
//            Session session = app.session();
//
//            QiService repeatService = new RepeatService(session);
//
//            DynamicObjectBuilder objBuilder = new DynamicObjectBuilder();
//            objBuilder.advertiseMethod("repeat::v(s)", repeatService, "Repeats string");
//
//            repeatService.init(objBuilder.object());
//
//
//            session.registerService("RepeatService", objBuilder.object());
//
//            Main.run(session);
//
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//            System.exit(1);
//        }
    }

    private static void run(Session session) throws Exception {

        RepeatProxy proxy = new RepeatProxy(session, "RepeatService");

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

//        AnyObject tts = session.service("ALTextToSpeech");
//        boolean ping = tts.<Boolean>call("ping").get();
//        if (!ping) {
//            System.out.println("Could not ping TTS");
//        } else {
//            System.out.println("Ping ok");
//        }

        reader.close();
    }
}
