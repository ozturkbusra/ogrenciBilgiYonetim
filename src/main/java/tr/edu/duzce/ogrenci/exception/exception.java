package tr.edu.duzce.ogrenci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class exception {
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {NullPointerException.class, ArithmeticException.class})
    public String handleException(HttpServletRequest request, Exception ex) {
        System.err.println(request.getRequestURL() + " Bu numarada bir öğrenci bulunmamaktadır. Hata mesaji: " + ex.getMessage());

        return "error";
    }
}
