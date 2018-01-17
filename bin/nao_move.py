#!/usr/bin/python
import naoqi
from naoqi import ALProxy
from nao_conf import *

#IP="192.168.1.5"
#IP="169.254.242.9"
tts = ALProxy("ALTextToSpeech", IP, 9559)
motion = ALProxy("ALMotion", IP, 9559)
motion.setStiffnesses("Body", 1.0)
motion.moveInit()
tts.say("Catch me! I will walk!")
id = motion.post.moveTo(0.5, 0, 0)
motion.wait(id, 0)
tts.say("I am done!")

