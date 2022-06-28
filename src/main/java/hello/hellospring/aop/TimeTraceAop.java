package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component // 스캔에 걸기보단 명확하게 Config에서 정의해주는것도 좋다.
public class TimeTraceAop {

    // hello.hellospring 에 전체 적용
//    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long s = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long f = System.currentTimeMillis();
            long elapsed = f - s;
            System.out.println("END: " + joinPoint.toString() + " " + elapsed + "ms");
        }
    }
}
