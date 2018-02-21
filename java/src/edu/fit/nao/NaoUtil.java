package edu.fit.nao;

import org.apache.commons.cli.*;

import java.io.PrintWriter;

public abstract class NaoUtil {

    public static ConnectionInfo ParseOptions(final String[] args) {

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
}
