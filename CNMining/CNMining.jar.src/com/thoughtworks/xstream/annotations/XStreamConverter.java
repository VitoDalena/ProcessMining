package com.thoughtworks.xstream.annotations;

import com.thoughtworks.xstream.converters.ConverterMatcher;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD})
@Documented
public @interface XStreamConverter
{
  Class<? extends ConverterMatcher> value();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/XStreamConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */