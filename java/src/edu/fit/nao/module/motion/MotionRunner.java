package edu.fit.nao.module.motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAutonomousLife;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import edu.fit.nao.ModuleRunner;
import edu.fit.nao.module.geometry.Pose2D;
import edu.fit.nao.module.perception.inertialunit.InertialUnit;

import java.util.Timer;
import java.util.TimerTask;

public class MotionRunner extends ModuleRunner {

    public MotionRunner(Session session) { super(session); }

    public void move(ALMotion motion, float x, float y, float theta) throws InterruptedException, CallError {

        // magneto-resistive element (MRE) sensors
        Pose2D initPose = new Pose2D(motion.getRobotPosition(true));

        motion.moveTo(x, y, theta);
        motion.waitUntilMoveIsFinished();

        Pose2D finalPose = new Pose2D(motion.getRobotPosition(true));

        System.out.print("InitialPose="); System.out.println(initPose);
        System.out.print("FinalPose=");  System.out.println(finalPose);
        System.out.print("EstimatedPoseDifference=");
        System.out.println(initPose.diff(finalPose));
    }

    @Override
    public void run() throws Exception {

        ALAutonomousLife life = new ALAutonomousLife(this.session);
        ALRobotPosture posture = new ALRobotPosture(this.session);
        ALMotion motion = new ALMotion(this.session);

        // life.setState("disabled");

        // motion.wakeUp();
        // posture.goToPosture("StandInit", 0.8f);

        /* INERTIAL_UNIT */
        ALMemory memory = new ALMemory(this.session);
        InertialUnit inertialUnit = new InertialUnit(memory);

        /* Sync */
        final int PERIOD = 3; // seconds
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() { System.out.println(inertialUnit); }
        }, 0, 1000 * PERIOD);


//        motion.moveInit();
//
//        move(motion, 0.1f, 0.0f, 0.2f);
//
//        motion.rest();
//        timer.cancel();
    }
}
