package aspect;

import aspect.myannotation.Auditable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@After("@annotation(auditable)")
	public void testAnnotation(JoinPoint joinPoint, Auditable auditable){
		System.out.println(auditable.action());
	}

}