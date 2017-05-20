package com.thoughtworks.xstream.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface XStreamImplicitCollection
{
  String value();
  
  String item() default "";
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/XStreamImplicitCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */