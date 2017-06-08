package com.javaworld.sample.helloworld1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.javaworld.sample.helloservice.IHelloService;

public class Activator implements BundleActivator {

	ServiceReference helloServiceReference;
    public void start(BundleContext context) throws Exception {
        System.out.println("Hello World!!");
        helloServiceReference= context.getServiceReference(IHelloService.class.getName());
        IHelloService helloService =(IHelloService)context.getService(helloServiceReference);
        System.out.println(helloService.sayHello());

    }
    public void stop(BundleContext context) throws Exception {
        System.out.println("Goodbye World!!");
        context.ungetService(helloServiceReference);
    }

}
