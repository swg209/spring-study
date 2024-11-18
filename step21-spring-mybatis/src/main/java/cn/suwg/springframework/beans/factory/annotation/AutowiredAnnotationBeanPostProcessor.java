package cn.suwg.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.beans.factory.BeanFactoryAware;
import cn.suwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.suwg.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.suwg.springframework.core.convert.ConversionService;
import cn.suwg.springframework.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * 处理 @Value、@Autowired，注解的 BeanPostProcessor
 *
 * @Author: suwg
 * @Date: 2024/10/31
 * 公众号：趣研
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {

        // 获取类信息.
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        // 拿到字段信息
        Field[] declaredField = clazz.getDeclaredFields();

        // 处理 @Value注解
        for (Field field : declaredField) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                Object value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue((String) value);

                //类型转换
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                ConversionService conversionService = beanFactory.getConversionService();
                if (conversionService != null) {
                    if (conversionService.canConvert(sourceType, targetType)) {
                        value = conversionService.convert(value, targetType);
                    }
                }


                // 设置字段值
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 处理 @Autowired 注解
        for (Field field : declaredField) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                //获取字段类型
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                // 获取字段上的Qualifier注解
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }


}
