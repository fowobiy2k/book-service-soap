package com.soap.service.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class Config {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/tos/*");
    }

    @Bean(name = "bookserver")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("BookServiceEndpoint");
        defaultWsdl11Definition.setLocationUri("/tos");
        defaultWsdl11Definition.setTargetNamespace("http://www.fowobi.com/spring/soap/api/bookservice");
        defaultWsdl11Definition.setSchema(schema);

        return defaultWsdl11Definition;

    }

    @Bean
    public XsdSchema schema(){
        return new SimpleXsdSchema(new ClassPathResource("descriptor.xsd"));
    }
}
