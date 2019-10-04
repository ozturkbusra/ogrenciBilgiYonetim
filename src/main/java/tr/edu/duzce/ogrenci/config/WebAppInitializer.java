package tr.edu.duzce.ogrenci.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
        @Override
        public void onStartup(ServletContext servletContext) {
                //DispatcherServlet
                //Uygulamaya gelen bütün isteklerin karşılanmasından ve Spring uygulama
                //çatısı içinde yönlendirilmesinden sorumludur.
                AnnotationConfigWebApplicationContext context = getContext();
                servletContext.addListener(new ContextLoaderListener(context));
                ServletRegistration.Dynamic dispatcherServlet =
                        servletContext.addServlet("DispatcherServlet",new DispatcherServlet(context));
                dispatcherServlet.setLoadOnStartup(1);
                dispatcherServlet.addMapping("/");

                //Web Filter
                // Karakter kodlaması ve sıkıştırma gibi genel işlemler, belirtilen HTTP isteklerin tamamına
                CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
                characterEncodingFilter.setEncoding("UTF-8");
                characterEncodingFilter.setForceEncoding(true);
                characterEncodingFilter.setForceRequestEncoding(true);
                characterEncodingFilter.setForceResponseEncoding(true);
                servletContext.addFilter("characterEncodingFilter",
                        characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");
        }

        private AnnotationConfigWebApplicationContext getContext() {
                AnnotationConfigWebApplicationContext context = new
                        AnnotationConfigWebApplicationContext();
                context.setConfigLocation("tr.edu.duzce.ogrenci.config");
                return context;
        }

        }
