package edu.fit.nao.module.repeat;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.ModuleRunner;

import java.util.Scanner;

public class RepeatRunner extends ModuleRunner {

    public RepeatRunner(Session session) { super(session); }

    @Override
    public void run() throws Exception {

        ALTextToSpeech tts = new ALTextToSpeech(session);

        RepeatService service = new RepeatService(tts);
        service.registerService(session);

        RepeatProxy repeat = new RepeatProxy(session);

        boolean running = true;
        System.out.println("Type 'exit' to leave...");

        try (Scanner reader = new Scanner(System.in)) {

            do {

                System.out.print(">> ");
                String str = reader.nextLine();

                if (str.equals("exit")) running = false;
                else {

                    repeat.repeat(str);
                    System.out.println();
                }

            } while (running);

            repeat.repeat("Bye, Bye.");
        }

        System.exit(0);
    }
}
