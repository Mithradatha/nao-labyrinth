package edu.fit.nao.tutorial;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import edu.fit.nao.helper.ConnectionManager;

public class BareBones {

    // cmd args: --pip <ip> --pport <port>
    public static void main(String[] args) {

        try {

            // parse command line arguments using commons-cli (found in lib folder)
            ConnectionManager.ConnectionInfo connectionInfo = ConnectionManager.ParseOptions(args, true);

            // connecting to Nao remotely requires knowledge of his IP and Port on the network
            String robotUrl = String.format("tcp://%s:%d", connectionInfo.ip, connectionInfo.port);

            // the application object initializes the qi framework
            // see <a href="https://github.com/aldebaran/libqi-java"> for more information
            Application application = new Application(new String[]{"--qi-url", robotUrl});

            // start processing qi event loop
            // and connect to session using robotUrl
            application.start();

            // the session object acts as a broker
            // between user code and the qi messaging layer,
            // providing access to local and remote services
            Session session = application.session();

            //=========================================
            // < insert all task-dependent code here >
            //=========================================

            // join qi event loop thread
            application.run();

        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
