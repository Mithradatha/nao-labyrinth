/*
* Copyright (c) 2012-2015 Aldebaran Robotics. All rights reserved.
* Use of this source code is governed by a BSD-style license that can be
* found in the COPYING file.
*/

#include <boost/program_options.hpp>
#include <iostream>

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
    description.add_options()
        ("pip", options::value<string>()->required(), "IP of Nao Robot")
        ("pport", options::value<int>()->required(), "Port of Nao Robot");

    try
    {
        const options::basic_parsed_options<char> parsedOptions = options::parse_command_line(argc, argv, description);

        options::variables_map parsedOptionsMap;
        options::store(parsedOptions, parsedOptionsMap);
        options::notify(parsedOptionsMap);

        ConnectionInfo connectionInfo = 
        {
            parsedOptionsMap["pip"].as<string>(),
            parsedOptionsMap["pport"].as<int>()
        };

        return connectionInfo;
    }
    catch (const options::error &ex)
    {
        cerr << "Exception Raised: " << ex.what() << endl;
        cerr << endl << description << endl << endl;
        
        system("pause");
        exit(1);
    }
}

int main(int argc, const char *argv[])
{
    const ConnectionInfo connectionInfo = parseOptions(argc, argv);

    cout << "Parent Broker Connection Information:" << endl << endl;
    cout << "IP: " << connectionInfo.ip << endl;
    cout << "Port: " << connectionInfo.port << endl << endl;

    system("pause");
    return 0;
}
