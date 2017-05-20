package com.thoughtworks.xstream.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
@Deprecated
public @interface XStreamContainedType {}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/XStreamContainedType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */