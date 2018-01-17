#!/usr/bin/python
import naoqi
import almath
import time
from nao_conf import *
from naoqi import ALProxy
from naoqi import ALModule
from naoqi import ALBroker

SIDE = 0.2

tts = ALProxy("ALTextToSpeech", IP, 9559)

motionProxy = ALProxy("ALMotion", IP, PORT)

tts.say("Give me something!");
motionProxy.openHand("RHand");

time.sleep(5);

motionProxy.closeHand("RHand");


tts.say("Starting North!")
motionProxy.moveTo(SIDE,0.0,0.0)
tts.say("Turn Eastward!")
motionProxy.moveTo(0.0,0.0,1.57)
tts.say("Go East!")
motionProxy.moveTo(SIDE,0.0,0.0)
tts.say("Turn Southward!")
motionProxy.moveTo(0.0,0.0,1.57)
tts.say("Go South!")
motionProxy.moveTo(SIDE,0.0,0.0)
tts.say("Turn Westward!")
motionProxy.moveTo(0.0,0.0,1.57)
tts.say("Go West!")
motionProxy.moveTo(SIDE,0.0,0.0)
tts.say("Turn Northward!")
motionProxy.moveTo(0.0,0.0,1.57)

