package com.javaworld.sample.helloservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.javaworld.sample.service.impl.HelloServiceImpl.HelloServiceImpl;

public class HelloServiceActivator implements BundleActivator {

	ServiceRegistration helloServiceRegistration;
    public void start(BundleContext context) throws Exception {
        IHelloService helloService = new HelloServiceImpl();
        helloServiceRegistration =context.registerService(IHelloService.class.getName(), helloService, null);
    }
    public void stop(BundleContext context) throws Exception {
        helloServiceRegistration.unregister();
    }
}
