/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.configuration.web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * Created by baidu on 16/8/23.
 */

@Configuration
@PropertySource("classpath:/application.yaml")
//@EnableConfigurationProperties(WebConfiguration.TomcatSslConnectorProperties.class)
public class WebConfiguration extends WebMvcConfigurerAdapter {

    public static final String FORWARD_INDEX_HTML = "forward:/index.html";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/internal/**")
                .addResourceLocations("classpath:/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // TODO: switch to index when index ready.
        registry.addViewController("/").setViewName(FORWARD_INDEX_HTML);
        registry.addViewController("/index").setViewName(FORWARD_INDEX_HTML);
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//
//            @Override
//            protected void postProcessContext(Context context) {
//
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    private Connector initiateHttpConnector() {
//
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer(TomcatSslConnectorProperties properties) {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
//        return tomcat;
//    }
//
//    private Connector createSslConnector(TomcatSslConnectorProperties properties) {
//        Connector connector = new Connector();
//        properties.configureConnector(connector);
//        return connector;
//    }

//    @ConfigurationProperties(prefix = "server.ssl")
//    public static class TomcatSslConnectorProperties {
//        public Boolean getSsl() {
//            return ssl;
//        }
//
//        public TomcatSslConnectorProperties setSsl(Boolean ssl) {
//            this.ssl = ssl;
//            return this;
//        }
//
//        public Boolean getSecure() {
//            return secure;
//        }
//
//        public TomcatSslConnectorProperties setSecure(Boolean secure) {
//            this.secure = secure;
//            return this;
//        }
//
//        public String getScheme() {
//            return scheme;
//        }
//
//        public TomcatSslConnectorProperties setScheme(String scheme) {
//            this.scheme = scheme;
//            return this;
//        }
//
//        public File getKeystore() {
//            return keystore;
//        }
//
//        public TomcatSslConnectorProperties setKeystore(File keystore) {
//            this.keystore = keystore;
//            return this;
//        }
//
//        public String getKeystorePassword() {
//            return keystorePassword;
//        }
//
//        public TomcatSslConnectorProperties setKeystorePassword(String keystorePassword) {
//            this.keystorePassword = keystorePassword;
//            return this;
//        }
//
//        public Integer getPort() {
//            return port;
//        }
//
//        public TomcatSslConnectorProperties setPort(Integer port) {
//            this.port = port;
//            return this;
//        }
//
//        private Integer port;
//        private Boolean ssl = true;
//        private Boolean secure = true;
//        private String scheme = "https";
//        private File keystore;
//        private String keystorePassword;
//        //这里为了节省空间，省略了getters和setters，读者在实践的时候要加上
//
//        public void configureConnector(Connector connector) {
//
//            if (secure != null) {
//                connector.setSecure(secure);
//            }
//            if (scheme != null) {
//                connector.setScheme(scheme);
//            }
//            if (ssl != null) {
//                connector.setProperty("SSLEnabled", ssl.toString());
//            }
//            if (keystore != null && keystore.exists()) {
//                connector.setProperty("keystoreFile", keystore.getAbsolutePath());
//                connector.setProperty("keystorePassword", keystorePassword);
//            }
//        }
//    }
}
