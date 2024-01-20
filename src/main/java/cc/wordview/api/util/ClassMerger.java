package cc.wordview.api.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassMerger {

        private static final Logger logger = LoggerFactory.getLogger(ClassMerger.class);

        public static <T> T merge(T target, T source) {
                if (target == null || source == null) {
                        throw new IllegalArgumentException("Both target and source must be non-null");
                }

                Class<?> targetClass = target.getClass();
                Field[] fields = targetClass.getDeclaredFields();

                for (Field field : fields) {
                        field.setAccessible(true);
                        try {
                                Object sourceValue = field.get(source);
                                if (sourceValue != null) {
                                        field.set(target, sourceValue);
                                }
                        } catch (IllegalAccessException e) {
                                logger.error(e.getMessage(), e);
                        }
                }

                return target;
        }
}
