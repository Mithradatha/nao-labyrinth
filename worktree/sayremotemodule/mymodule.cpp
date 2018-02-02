#include "mymodule.h"

#include <iostream>
#include <alcommon/albroker.h>

MyModule::MyModule(boost::shared_ptr<AL::ALBroker> broker, const std::string &name)
    : AL::ALModule(broker, name), ttsProxy(broker) //ttsProxy(broker, "ALTextToSpeech")
{
  // Describe the module here. This will appear on the webpage
  setModuleDescription("My own custom module.");

  /**
   * Define callable methods with their descriptions:
   * This makes the method available to other cpp modules
   * and to python.
   * The name given will be the one visible from outside the module.
   * This method has no parameters or return value to describe
   * functionName(<method_name>, <class_name>, <method_description>);
   * BIND_METHOD(<method_reference>);
   */
  functionName("printHelloWorld", getName(), "Print 'Hello World!' to standard output");
  BIND_METHOD(MyModule::printHelloWorld);

  /**
   * addParam(<attribut_name>, <attribut_descrption>);
   * This enables to document the parameters of the method.
   * It is not compulsory to write this line.
   */
  functionName("sayString", getName(), "Say a given string.");
  addParam("str", "The string to be said.");
  BIND_METHOD(MyModule::sayString);

  /**
   * setReturn(<return_name>, <return_description>);
   * This enables to document the return of the method.
   * It is not compulsory to write this line.
   */
  functionName("returnTrue", getName(), "Just return true");
  setReturn("boolean", "return true");
  BIND_METHOD(MyModule::returnTrue);

  // If you had other methods, you could bind them here...
  /**
   * Bound methods can only take const ref arguments of basic types,
   * or AL::ALValue or return basic types or an AL::ALValue.
   */
}

MyModule::~MyModule()
{
}

void MyModule::init()
{
  /**
   * Init is called just after construction.
   * Do something or not
   */
  std::cout << "Module Loaded: " << returnTrue() << std::endl;
}

void MyModule::printHelloWorld()
{
  std::cout << "Hello World!" << std::endl;
}

void MyModule::sayString(const std::string &str)
{
  //ttsProxy.callVoid("say", str);
  ttsProxy.say(str);
}

bool MyModule::returnTrue()
{
  return true;
}