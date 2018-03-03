package com.demo.my.base.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//��ʾ��ʲô���𱣴��ע����Ϣ
@Retention(RetentionPolicy.RUNTIME)
// ��ʾ��ע������ʲô�ط�
@Target(ElementType.METHOD)
@Documented
public @interface AuthorityRole {
	
	String value();

}
