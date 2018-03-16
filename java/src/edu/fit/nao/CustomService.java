package edu.fit.nao;

import com.aldebaran.qi.*;

import java.lang.reflect.Method;

public abstract class CustomService extends QiService {

    private final String name;

    public CustomService(String name) {

        super();
        this.name = name;
    }

    public int registerService(Session session, DynamicObjectBuilder objectBuilder) throws QiException {

        Class<?> oClass = this.getClass();
        Method[] oMethods = oClass.getMethods();

        for (Method method : oMethods) {

            if (method.isAnnotationPresent(Advertise.class)) {

                Advertise annotation = method.getAnnotation(Advertise.class);
                objectBuilder.advertiseMethod(annotation.signature(), this, annotation.description());
            }
        }

        AnyObject service = objectBuilder.object();
        this.init(service);

        return session.registerService(name, service);
    }
}
