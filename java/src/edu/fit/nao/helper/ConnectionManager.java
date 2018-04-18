package edu.fit.nao.helper;

import org.apache.commons.cli.*;

import java.io.PrintWriter;

public abstract class ConnectionManager {

    public static class ConnectionInfo {

        public String ip;
        public int port;
    }

    public static ConnectionInfo ParseOptions(String[] args, boolean exitOnFail) {

        Option pipOption = Option.builder()
                .longOpt("pip")
                .required(true)
                .hasArg(true)
                .desc("IP of Nao Robot")
                .build();

        Option pportOption = Option.builder()
                .longOpt("pport")
                .required(true)
                .hasArg(true)
                .desc("Port of Nao Robot")
                .build();

        Options options = new Options();
        options.addOption(pipOption);
        options.addOption(pportOption);

        ConnectionInfo connectionInfo = new ConnectionInfo();

        try {

            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(options, args);

            connectionInfo.ip = line.getOptionValue("pip");
            connectionInfo.port = Integer.parseInt(line.getOptionValue("pport"));

        } catch (ParseException ex) {

            System.err.println(String.format("ERROR: Unable to parse command-line arguments.\n%s\n", ex));

            HelpFormatter formatter = new HelpFormatter();
            PrintWriter writer = new PrintWriter(System.err);

            formatter.printUsage(writer, 80, "Main", options);
            writer.flush();
            writer.close();

            if (exitOnFail)
                System.exit(1);
        }

        return connectionInfo;
    }
}
