#!/usr/bin/python
import naoqi
from naoqi import ALProxy
from nao_conf import *

tts = ALProxy("ALTextToSpeech", IP, 9559)
motion = ALProxy("ALMotion", IP, 9559)
motion.setStiffnesses("Body", 1.0)
motion.moveInit()
for x in range(2):
	tts.say("Catch me! I will walk!")
	id = motion.post.moveTo(0, 0, 0.5)
# 1.5707)
	motion.wait(id, 0)
	tts.say("I am done!")

