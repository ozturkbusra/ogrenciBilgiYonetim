package tr.edu.duzce.ogrenci.interceptor;

import org.hibernate.Session;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

//Controller sınıfına uygulanır
public class Interceptor extends HandlerInterceptorAdapter {

    //HTTP isteği yürütülmeden önce çağrılır.
   /* @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {

        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method=handlerMethod.getMethod();

        System.out.println(method.getName()+" isimli metodun yürütümünden önce");
        request.setAttribute("startTime", System.currentTimeMillis());
        request.setAttribute("method", method.getName());

        return super.preHandle(request,response,handler);
    }*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        HttpSession session = request.getSession();
        if (session==null) return false;

        else
            {
                String username = (String) session.getAttribute("username");
                if(username==null) return false;
                else return true;
            }
    }

    //HTTP isteğinin karşılanması ilgili metod tarafından tamamlanınca çağrılır
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws  Exception {
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method=handlerMethod.getMethod();

        System.out.println(method.getName()+" isimli metodun yürütümünden sonra");

        super.postHandle(request,response,handler,modelAndView);
    }

    //HTTP isteğinin yürütümü tamamen sonlanıp, istemciye geri dönüş yapıldıktan sonra
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long starTime = Long.parseLong(request.getAttribute("startTime").toString());
        Long endTime = System.currentTimeMillis();
        System.out.println(request.getAttribute("method") + " isteği " + (endTime-starTime) + " ms. sürede işletildi");

        super.afterCompletion(request, response, handler, ex);
    }


}
