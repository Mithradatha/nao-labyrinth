#ifndef MY_MODULE_H
#define MY_MODULE_H

#include <iostream>
#include <alcommon/almodule.h>
//#include <alcommon/alproxy.h>
#include <alproxies/altexttospeechproxy.h>

namespace AL
{
// This is a forward declaration of AL:ALBroker which
// avoids including <alcommon/albroker.h> in this header
class ALBroker;
}

/**
 * This class inherits AL::ALModule. This allows it to bind methods
 * and be run as a remote executable within NAOqi
 */
class MyModule : public AL::ALModule
{
public:
  MyModule(boost::shared_ptr<AL::ALBroker> broker,
           const std::string &name);

  virtual ~MyModule();

  /**
   * Overloading ALModule::init().
   * This is called right after the module has been loaded
   */
  virtual void init();

  // After that you may add all your bind method.

  // Function which prints "Hello World!" on standard output
  void printHelloWorld();
  // Function which prints the string given on parameters
  void sayString(const std::string &str);
  // Function which returns true
  bool returnTrue();

private:
  AL::ALTextToSpeechProxy ttsProxy;
};
#endif // MY_MODULE_H