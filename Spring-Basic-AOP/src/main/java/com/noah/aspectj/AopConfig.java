package com.noah.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.noah.aspectj")
@EnableAspectJAutoProxy //this means enable spring support for aspectJ
public class AopConfig {

}
