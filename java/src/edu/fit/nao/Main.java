package edu.fit.nao;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.DynamicObjectBuilder;
import com.aldebaran.qi.QiService;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.module.landmarkdetection.LandMarkDetection;
import edu.fit.nao.module.landmarkdetection.LandMarkDetectionProxy;
import edu.fit.nao.module.landmarkdetection.MarkInfo;
import edu.fit.nao.module.repeat.RepeatProxy;
import edu.fit.nao.module.repeat.RepeatService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {

        try {



            ConnectionInfo connectionInfo = Util.ParseOptions(args, true);

            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            Application application = new Application(new String[]{"--qi-url", robotUrl});
            application.start();

            Session session = application.session();

            // ModuleRunner landmarkTest = new LandMarkDetectionTest(session, robotUrl);
            // landmarkTest.run();

            ALTextToSpeech tts = new ALTextToSpeech(session);
            Map<Integer, MarkInfo> landmarks = new HashMap();

            LandMarkDetectionProxy proxy = new LandMarkDetectionProxy(session);
            long eventId = proxy.setEventCallback(alValue -> {

                if (alValue.size() > 0) {
                    LandMarkDetection lmd = new LandMarkDetection(alValue);
                    if (!landmarks.containsKey(lmd.markInfo.get(0).markID)) {
                        landmarks.put(lmd.markInfo.get(0).markID, lmd.markInfo.get(0));
                        tts.say("New Landmark Detected");
                        System.out.println(lmd);
                    }
                }
            });

            proxy.subscribe();

//            AnyObject lmd = session.service("ALLandMarkDetection");
//            lmd.call("subscribe", "Test_LandmarkDetected");

//            ALMemory memory = new ALMemory(session);
//            memory.subscribeToEvent("LandmarkDetected", new EventCallback() {
//                @Override
//                public void onEvent(Object o) throws InterruptedException, CallError {
//                    List alValue = (List)o;
//
//                    if (alValue.size() > 0) {
//
//                        tts.say("Landmark Detected");
//                        LandMarkDetection landmarkValue = new LandMarkDetection(alValue);
//                        landmarkValue.markInfo.forEach(markInfo -> markInfo.markID);
//                        System.out.println(landmarkValue);
//                    }
//                }
//            });

//            RepeatService service = new RepeatService(tts);
//
//            long id = service.registerService(session, new DynamicObjectBuilder());
//            RepeatProxy proxy = new RepeatProxy(session);
//
//            boolean running = true;
//            System.out.println("Type 'exit' to leave...");
//
//            Scanner reader = new Scanner(System.in);
//
//            do {
//
//                System.out.print(">> ");
//                String str = reader.nextLine();
//
//                if (str.equals("exit")) running = false;
//                else {
//
//                    proxy.repeat(str);
//                    System.out.println();
//                }
//
//            } while (running);
//
//            proxy.repeat("Bye, Bye.");
//            reader.close();

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
