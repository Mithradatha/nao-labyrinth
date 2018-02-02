
All compilation projects are to be placed in the worktree folder.

In order to write C++ executable binaries or libraries for local/remote 
    modules you will need the following:
    1. Python 2.7
    2. CMake
    3. qiBuild
    4. C++ Compiler
    5. Nao SDK

To setup a new project:
    1. qibuild config --wizard
    2. cd worktree
    3. qibuild init
    4. `qitoolchain create <YOUR_TOOLCHAIN_NAME> 
       <PATH_TO_NAO_SDK>/toolchain.xml`
    5. qibuild add-config <YOUR_CONFIG_NAME> -t <YOUR_TOOLCHAIN_NAME>
    6. qibuild set-default <YOUR_CONFIG_NAME>
    6. qisrc create <YOUR_PROJECT_NAME>
    7. cd <YOUR_PROJECT_NAME>
    8. qibuild configure
    9. qibuild make

To open the project with your selected IDE, type `qibuild open`.

To execute a binary in 
    <YOUR_PROJECT_NAME>/build-<YOUR_CONFIG_NAME>/sdk/bin,
    type `qibuild run <BINARY_NAME>`.

Please do not version your .qi configuration folder.
Please do not version your build-<YOUR_CONFIG_NAME> folder.

More information can be found at:
    http://doc.aldebaran.com/2-1/index.html