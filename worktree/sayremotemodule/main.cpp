/*
* Copyright (c) 2012-2015 Aldebaran Robotics. All rights reserved.
* Use of this source code is governed by a BSD-style license that can be
* found in the COPYING file.
*/
#include <iostream>

#include <boost/program_options.hpp>
#include <boost/shared_ptr.hpp>

#include "mymodule.h"

#include <alcommon/alproxy.h>
#include <alcommon/almodule.h>
#include <alcommon/albroker.h>
#include <alcommon/albrokermanager.h>

#include <alerror/alerror.h>

using namespace std;

struct ConnectionInfo
{
    string ip;
    int port;
};

const ConnectionInfo parseOptions(int argc, const char *argv[])
{
    namespace options = boost::program_options;

    boost::program_options::options_description description("Command Line Options");
    description.add_options()("pip", options::value<string>()->required(), "IP of Nao Robot")("pport", options::value<int>()->required(), "Port of Nao Robot");

    try
    {
        const options::basic_parsed_options<char> parsedOptions = options::parse_command_line(argc, argv, description);

        options::variables_map parsedOptionsMap;
        options::store(parsedOptions, parsedOptionsMap);
        options::notify(parsedOptionsMap);

        ConnectionInfo connectionInfo =
            {
                parsedOptionsMap["pip"].as<string>(),
                parsedOptionsMap["pport"].as<int>()};

        return connectionInfo;
    }
    catch (const options::error &ex)
    {
        cerr << "Exception Raised: " << ex.what() << endl;
        cerr << endl
             << description << endl
             << endl;

        system("pause");
        exit(1);
    }
}

int main(int argc, const char *argv[])
{

    const ConnectionInfo connectionInfo = parseOptions(argc, argv);

    const string parentIP = connectionInfo.ip;
    const int parentPort = connectionInfo.port;

    cout << "Parent Broker Connection Information:" << endl
         << endl;
    cout << "IP: " << parentIP << endl;
    cout << "Port: " << parentPort << endl
         << endl;

    setlocale(LC_NUMERIC, "C");

    try
    {
        boost::shared_ptr<AL::ALBroker> broker = AL::ALBroker::createBroker(
            "mybroker",
            "0.0.0.0",
            0,
            parentIP,
            parentPort,
            0);

        AL::ALBrokerManager::setInstance(broker->fBrokerManager.lock());
        AL::ALBrokerManager::getInstance()->addBroker(broker);

        AL::ALModule::createModule<MyModule>(broker, "MyModule");

        AL::ALProxy proxy(broker, "MyModule");
        proxy.callVoid("printHelloWorld");
        proxy.callVoid("sayString", string("Hello World!"));

        // boost::shared_ptr<AL::ALTextToSpeechProxy> ttsProxy(new AL::ALTextToSpeechProxy(broker));
        // ttsProxy->say(string("Hello World!"));
    }
    catch (const AL::ALError &ex)
    {
        std::cerr << ex.what() << std::endl;
        AL::ALBrokerManager::getInstance()->killAllBroker();
        AL::ALBrokerManager::kill();
        system("pause");
        return 1;
    }

    system("pause");
    return 0;
}