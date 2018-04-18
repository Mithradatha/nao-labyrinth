package edu.fit.nao.module.perception;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;
import edu.fit.nao.helper.ModuleRunner;
import edu.fit.nao.helper.proxies.LandmarkDetectionProxy;
import edu.fit.nao.module.localization.LandmarkLocalization;
import edu.fit.nao.module.perception.camera.Camera;
import edu.fit.nao.module.perception.camera.CameraListener;
import edu.fit.nao.module.perception.inertialunit.InertialUnit;
import edu.fit.nao.module.perception.sonar.Sonar;
import edu.fit.nao.module.perception.sonar.SonarListener;

import java.util.Timer;
import java.util.TimerTask;

public class PerceptionRunner extends ModuleRunner {

    public PerceptionRunner(Session session) { super(session); }

    @Override
    public void run() throws Exception {

        ALAutonomousLife life = new ALAutonomousLife(this.session);
        ALRobotPosture posture = new ALRobotPosture(this.session);
        ALMotion motion = new ALMotion(this.session);

        // life.setState("disabled");

        // motion.wakeUp();
        // posture.goToPosture("StandInit", 0.25f);

        /* SONAR */
        Sonar sonar = new Sonar(new ALSonar(session), new ALMemory(session));

        SonarListener sonarListener = new SonarListener();
        sonarListener.attachTo(sonar);

        // sonar.start();

        /* INERTIAL_UNIT */
        ALMemory memory = new ALMemory(this.session);
        InertialUnit inertialUnit = new InertialUnit(memory);

        /* CAMERA */
        Camera camera = new Camera(new LandmarkDetectionProxy(this.session));

        LandmarkLocalization localization = new LandmarkLocalization(motion, 0.105f);
        CameraListener cameraListener = new CameraListener(localization);
        cameraListener.attachTo(camera);

        camera.start();

        /* Sync */
        final int PERIOD = 3; // seconds
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                // System.out.println(sonarListener);
                // System.out.println(inertialUnit);
                System.out.println(cameraListener);

                // sonarListener.events.clear();
                cameraListener.events.clear();
            }
        }, 0, 1000 * PERIOD);


//        camera.stop();
//        sonar.stop();
//        timer.cancel();
    }
}
