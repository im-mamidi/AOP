package com.javainuse.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

	@Before(value = "execution(* com.javainuse.service.EmployeeService.*(..)) and args(name,empId)")
	public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("Before method:" + joinPoint.getSignature());

		System.out.println("**********Creating Employee with name - " + name + " and id - " + empId);
	}

	@After(value = "execution(* com.javainuse.service.EmployeeService.*(..)) and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("After method:" + joinPoint.getSignature());
		System.out.println("*******Successfully created Employee with name - " + name + " and id - " + empId);
	}

	@AfterThrowing(value="execution(* com.javainuse.service.EmployeeService.*(..))",throwing="ex")
	public void afterThrowingAdvice(JoinPoint joinPoint,Exception ex){
		System.out.println("&&&&&&&&&&&&&");
		System.out.println("After Throwing exception in method:"+joinPoint.getSignature());
		//System.out.println("Exception is:"+ex.getMessage());
	}
}
