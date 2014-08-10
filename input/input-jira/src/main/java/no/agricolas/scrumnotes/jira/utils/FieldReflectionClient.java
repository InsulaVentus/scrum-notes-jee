package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.domain.Field;

import java.lang.reflect.Method;

/**
 * @author Øyvind Strømmen
 */
public class FieldReflectionClient {

    public void extractFields(Class clazz) {


        Class obj = clazz;


        for (Method method : obj.getDeclaredMethods()) {
//            method.
        }


        clazz.isAnnotationPresent(Field.class);

        try {

            clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
