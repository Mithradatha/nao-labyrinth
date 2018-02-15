package edu.fit.nao;

import com.aldebaran.qi.*;
import edu.fit.nao.module.RepeatProxy;
import edu.fit.nao.module.RepeatService;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.Scanner;

class ConnectionInfo {

    public String ip;
    public int port;
}

public class Main {

    private static ConnectionInfo parseOptions(final String... args) {

        final Option pipOption = Option.builder()
                .longOpt("pip")
                .required(true)
                .hasArg(true)
                .desc("IP of Nao Robot")
                .build();

        final Option pportOption = Option.builder()
                .longOpt("pport")
                .required(true)
                .hasArg(true)
                .desc("Port of Nao Robot")
                .build();

        final Options options = new Options();
        options.addOption(pipOption);
        options.addOption(pportOption);

        ConnectionInfo connectionInfo = new ConnectionInfo();

        try {

            final CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(options, args);

            connectionInfo.ip = line.getOptionValue("pip");
            connectionInfo.port = Integer.parseInt(line.getOptionValue("pport"));

        } catch (ParseException ex) {

            System.err.println(String.format("ERROR: Unable to parse command-line arguments.\n%s\n", ex));

            final HelpFormatter formatter = new HelpFormatter();
            final PrintWriter writer = new PrintWriter(System.err);

            formatter.printUsage(writer, 80, "Main", options);
            writer.flush();
            writer.close();

            System.exit(1);
        }

        return connectionInfo;
    }

    public static void main(String... args) {

        try {

            ConnectionInfo connectionInfo = parseOptions(args);

            //String robotUrl = "tcp://" + connectionInfo.ip + ":" + connectionInfo.port;
            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);
            System.out.println(robotUrl);
            Application app = new Application(args, robotUrl);

            app.start();
            Session session = app.session();

            QiService repeatService = new RepeatService(session);

            DynamicObjectBuilder objBuilder = new DynamicObjectBuilder();
            repeatService.init(objBuilder.object());

            objBuilder.advertiseMethod("repeat::v(s)", repeatService, "Repeats string");
            session.registerService("RepeatService", objBuilder.object());

            Main.run(session);

        } catch (Exception ex) {

            ex.printStackTrace();
            System.exit(1);
        }
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
        reader.close();
    }
}
