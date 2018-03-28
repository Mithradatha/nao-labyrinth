############################################################
# Users -> see ModuleRunner for testing existing libraries #
#                                                          #
# Developers -> see CustomService, ALProxy for extension   #
############################################################

# Java Project: java/
=====================>

# Include Libraries: lib/
=========================
>> commons-cli-1.4.jar
Command line interface library.
Used to parse connection arguments.
<see href="ConnectionManager" for usage>

# Resources: res/
=================
>> 1.map
Sample grid map.
x => blocked.
o => free.
Start  (s) at [5, 5].
Finish (f) at [0, 3].

# Source Code: src/
===================>


# Common Module: edu.fit.nao
=======================================================================>
>> Advertise: Annotation Interface
Runtime method annotation for CustomService advertised methods.
<see href="RepeatService" for usage example>
String signature => serialization instructions for method types.
String description => summary of method functionality.

>> ALValue: Abstract Class
Provides toString() implementation for ALValue types.
An ALValue is a custom type that can contain recursive definitions.
<see href="http://doc.aldebaran.com/2-1/ref/libalvalue/a00001.html">

>> ConnectionManager: Abstract Class
Utility class for parsing command line options using Commons CLI.

>> CustomService: Abstract QiService
Provides helper function registerService() that exposes Advertised 
methods to broker via the underlying service directory.
<see href="RepeatRunner" for registration example>
Extend if you have modular, reusable functionality that should be 
accessible from other sessions.

>> Main: Class
Access point for code execution.
Initializes an Application object using the robot's url.
Calling application.start() connects a Session object to the broker.
Executes the ModuleRunner of choice.
Calling application.run() blocks for the event loop.

>> ModuleRunner: Abstract Class
Base class for atomic blocks of executable code.
Extend if you want to run code for a specific module in a tidy way.
=======================================================================>


# Landmark Detection Module: module.landmarkdetection
========================================================================
>> LandmarkDetected: ALValue
<see 
href="http://doc.aldebaran.com/2-1/naoqi/vision/allandmarkdetection-api.
html#allandmarkdetection">

>> LandmarkDetectionProxy: ALProxy
Proxy for ALLandMarkDetection QiService.
Provides subscribe() method which starts the process of listening 
to LandmarkDetected events and handles the event via given callback.

>> MarkInfo: ALValue
<see 
href="http://doc.aldebaran.com/2-1/naoqi/vision/allandmarkdetection-api.
html#markinfo">

>> Position6D: ALValue
<see href="http://doc.aldebaran.com/2-1/glossary.html#term-position6d">

>> ShapeInfo: ALValue
<see 
href="http://doc.aldebaran.com/2-1/naoqi/vision/allandmarkdetection-api.
html#shapeinfo">

>> TimeStamp: ALValue
<see 
href="http://doc.aldebaran.com/2-1/naoqi/vision/allandmarkdetection-api.
html#timestamp">
========================================================================


# Localization Module: module.localization
========================================================================
>> Frame: Enum
<see 
href="http://doc.aldebaran.com/2-1/naoqi/motion/control-cartesian.
html#motion-effectors-space">

>> LandmarkLocalization: Class
Localizes landmarks relative to Nao's robot Frame.
Method localize() returns [x, y, z] in meters.
<see 
href="http://doc.aldebaran.com/2-1/dev/python/examples/vision/landmark.
html#landmark-localization">

>> LocalizationRunner: Module Runner
Subscribes to LandmarkDetected events, and handles the events by 
calculating an estimated distance from the robot to the detected 
landmark. The distance and landmark ID are printed to the console.

>> Position3D: Class
Float x, y, z position in meters.

>> Transform: Class
Homogenous transformation matrix.
Contains translation vector and rotation matrix.
<see href="http://doc.aldebaran.com/2-1/ref/libalmath/a00002.html">
========================================================================


# Navigation Module: module.navigation
========================================================================
>> AStar: Class
Pathfinding algorithm for grid-style map.
<see href="https://en.wikipedia.org/wiki/A*_search_algorithm">

>> Grid: Class
2D-array of Nodes.
Helper function to retrieve neighbors of given cell.

>> NavigationRunner: ModuleRunner
Parse grid map and solve for route using AStar search.
Print the contents of the map and the path from start to goal.

>> Node: Class
Stores its own position in the grid, whether or not it is accessible, a 
pointer to the parent cell if it has already been traveled, the
accumulated cost so far through the graph, and an estimate of the 
predicted cost of the path from its current position to some other cell.

>> Point2D: Class
Integer x, y position in cartesian coordinates.
========================================================================


# Repeat Module: module.repeat
========================================================================
>> AsyncRepeatProxy: ALProxy
Exposes asynchronous calls to the referenced CustomService.

>> RepeatProxy: ALProxy
Acts as an interface between the advertised methods of RepeatService 
and a client application. It can optionally expose an asynchronous 
version via async().

>> RepeatRunner: Module Runner
Command line interface for calling the RepeatService.
Repeats whatever text is submitted, and exits on 'exit' being typed.

>> RepeatService: CustomService
Wraps the ALTextToSpeech QiService and exposes a repeat() method, which
simply calls the text-to-speech service with the given string.
Used mostly as a demonstration of extending the CustomService class.
========================================================================
