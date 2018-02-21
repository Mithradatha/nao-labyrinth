package edu.fit.nao;

abstract class NaoRunnable {

    protected ConnectionInfo connectionInfo;

    public NaoRunnable(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    abstract void run() throws Exception;
}
