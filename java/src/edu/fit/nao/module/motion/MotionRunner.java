package edu.fit.nao.module.motion;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAutonomousLife;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import edu.fit.nao.ModuleRunner;
import edu.fit.nao.module.localization.Pose2D;

public class MotionRunner extends ModuleRunner {

    public MotionRunner(Session session) { super(session); }

    @Override
    public void run() throws Exception {

        ALAutonomousLife life = new ALAutonomousLife(this.session);
        ALRobotPosture posture = new ALRobotPosture(this.session);
        ALMotion motion = new ALMotion(this.session);

        life.setState("disabled");

        motion.wakeUp();
        posture.goToPosture("StandInit", 0.5f);

        motion.moveInit();

        // magneto-resistive element (MRE) sensors
        Pose2D initPose = new Pose2D(motion.getRobotPosition(true));

        motion.moveTo(0.1f, 0.0f, 0.2f);
        motion.waitUntilMoveIsFinished();

        Pose2D finalPose = new Pose2D(motion.getRobotPosition(true));

        System.out.println(initPose.diff(finalPose));

        motion.rest();
    }
}
