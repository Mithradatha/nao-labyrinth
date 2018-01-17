import naoqi
from naoqi import ALProxy
tts = ALProxy("ALTextToSpeech", "169.254.113.225", 9559)
tts.say("Hi! Look at how pretty I am!")

