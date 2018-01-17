#!/usr/bin/python
import naoqi
import almath
from nao_conf import *
from naoqi import ALProxy
from naoqi import ALModule
from naoqi import ALBroker

'''
class GoDougModule(ALModule) :
    """ Learn Faces """

    def __init__(self,name):
        """ """
        self.memoryProxy = ALProxy("ALMemory")
        self.memoryProxy.subscribeToEvent("FaceDetected", self.getName(), "onFaceDetected")
        pass

    def exit(self):
        self.memoryProxy.unsubscribeToEvent("FaceDetected", self.getName(), "onFaceDetected")
        ALModule.exit(self)
        pass

    def onFaceDetected(self, key, value, message):
        """ """
        print "Face Detected val = " + str(value)
        pass
    
    def getCount(self):
        """ returns count """
        pass

myBroker = ALBroker("myBroker", "0.0.0.0", 0, IP, 9559)
global GoDoug
GoDoug = GoDougModule("GoDoug")
'''


tts = ALProxy("ALTextToSpeech", IP, 9559)
#tts.say("Hi Dougg! I 'm looking forward to seeing you on campus for homecoming. We have a lot to talk about. I've been working really hard.")

automove = ALProxy("ALAutonomousMoves", IP, 9559)
if (automove.getBackgroundStrategy() != "none") :
    tts.say("Will Stop!")
    automove.setBackgroundStrategy("none");
    automove.setExpressiveListeningEnabled(False);
else :
    tts.say("Will Breath!")
    automove.setBackgroundStrategy("backToNeutral");
    automove.setExpressiveListeningEnabled(True);



'''
try:
    posture = ALProxy("ALRobotPosture", IP, 9559)
except Exception, e:
    print "Could not create proxy to ALRobotPosture"
    print "Error was: ", e
posture.setMaxTryNumber(3)
print posture.getPostureList()
posture.goToPosture("StandInit", 1.0)
#posture.goToPosture("SitRelax", 1.0)
#posture.goToPosture("StandZero", 1.0)
#posture.goToPosture("LyingBelly", 1.0)
#posture.goToPosture("LyingBack", 1.0)
#print posture.goToPosture("Stand", 1.0)
#posture.goToPosture("Crouch", 1.0)
#posture.goToPosture("Sit", 1.0)
#posture.goToPosture("SitOnChair", 1.0)
#posture.applyPosture("StandZero", 1.0); # dangerous, not intelligent
#posture.stopMove()
print posture.getPostureFamily()
'''

'''
nav = ALProxy("ALNavigationProxy", IP, 9559)
nav.navigateTo(2.0, 0.0, [["SpeedFactor", 0.5]])
nav.moveAlong(["Composed", ["Holonomic", ["Line", [1.0, 0.0]], 0.0, 5.0], ["Holonomic", ["Line", [-1.0, 0.0]], 0.0, 10.0]])
'''

'''
video = ALProxy("ALVideoDevice", IP, 9559)
subscriber = video.subscribeCamera("demo", 0,3,13,1)
imageNAO = video.getImageRemote(subscriber)
#myDisplayImageFunction(imageNAO)
'''


motionProxy = ALProxy("ALMotion", IP, PORT)
#motionProxy.rest(); #setStiffness(0.0);
#motionProxy.wakeUp();
#motionProxy.setStiffnesses("Body", 0);
print "Awake=", motionProxy.robotIsWakeUp()
print motionProxy.getBodyNames("Body")
# security static
motionProxy.setTangentialSecurityDistance(0.2)
#security during move
motionProxy.setOrthogonalSecurityDistance(0.5)

motionProxy.openHand("RHand");
motionProxy.closeHand("RHand");

useSensors   = False;
commandAngles = motionProxy.getAngles("Body", useSensors);
print "Command angles:"
print str(commandAngles)
print ""

#leftArmEnable  = False
#rightArmEnable  = False
#motionProxy.setMoveArmsEnabled(leftArmEnable, rightArmEnable)

#motionProxy.move(0.0,0.0,0)
#motionProxy.moveTo(1.0,0.0,0.7)



'''
motionProxy.move(0.01, 0.01, 0,
    [
    ["LeftStepHeight", 0.02],
    ["RightStepHeight", 0.005],
    ["RightTorsoWx", -7.0*almath.TO_RAD],
    ["TorsoWy", 5.0*almath.TO_RAD] ] )

# Wait for it to finish
proxy.waitUntilMoveIsFinished()

while proxy.moveIsActive():
   time.sleep(1)
# End the walk suddenly (~20ms)
proxy.killMove()

# End the walk cleanly (~0.8s)
proxy.moveToward(0.0, 0.0, 0.0)

# Example showing the use of moveToward
# The parameters are fractions of the maximum Step parameters
# Here we are asking for full speed forwards
# with maximum step frequency
x  = 1.0
y  = 0.0
theta  = 0.0
moveConfig = [["Frequency", 1.0]]
proxy.moveToward(x, y, theta, moveConfig)
# If we don't send another command, he will walk forever
# Lets make him slow down (step length) and turn after 10 seconds
time.sleep(10)
x = 0.5
theta = 0.6
proxy.moveToward(x, y, theta, moveConfig)
# Lets make him slow down(frequency) after 5 seconds
time.sleep(5)
moveConfig = [["Frequency", 0.5]]
proxy.moveToward(x, y, theta, moveConfig)
# After another 10 seconds, we'll make him stop
time.sleep(10)
proxy.moveToward(0.0, 0.0, 0.0)
# Note that at any time, you can use a moveTo command
# to move a precise distance. The last command received,
# of velocity or position always wins

'''
