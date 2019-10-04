package tr.edu.duzce.ogrenci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import tr.edu.duzce.ogrenci.interceptor.Interceptor;

@Configuration //Konfigürasyon sınıfı olduğunu belirtir
@EnableWebMvc // Web isteklerinin yürütülmesi MVC mimarisi ile olur
@ComponentScan(basePackages = {"tr.edu.duzce.ogrenci.config"}) //Konfigürasyon sınıflarının bulunduğu paket bilgisi
public class WebConfiguration implements WebMvcConfigurer {

    //Interceptorı tanıttık
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/saveOrUpdateOgrenci.ajax");
    }

    //Kullanmadık
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new
                InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

}
