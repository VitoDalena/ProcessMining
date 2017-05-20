package com.thoughtworks.xstream.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface XStreamOmitField {}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/XStreamOmitField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */