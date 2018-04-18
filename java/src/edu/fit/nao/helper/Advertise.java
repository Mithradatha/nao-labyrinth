package edu.fit.nao.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Advertise {

    String signature();
    String description();
}

/*
 * signature corresponds to Libqi mapper.
 * <see="https://github.com/aldebaran/libqi-java/blob/master/
 * qimessaging/src/main/java/com/aldebaran/qi/serialization/SignatureUtilities.java">
 *
 * Void -> v
 * Object -> o
 * AnyObject -> m
 * Boolean -> b
 * Integer -> i
 * Float -> f
 * Double -> d
 * Character -> c
 * String -> s
 * List -> []
 * Map -> {}
 * Tuple -> ()
 *
 * sample signatures:
 * void prop(String name) -> prop::v(s)
 * HashMap<String, Integer> meth(List<Object> list, Integer integer) -> meth::{si}([o]i)
 * Tuple<Integer, Character, Double> func(CustomType type) -> func::(icd)(m)
 */
