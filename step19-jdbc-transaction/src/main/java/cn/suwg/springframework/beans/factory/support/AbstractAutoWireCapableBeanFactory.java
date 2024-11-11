package cn.suwg.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.Aware;
import cn.suwg.springframework.beans.factory.BeanClassLoaderAware;
import cn.suwg.springframework.beans.factory.BeanFactoryAware;
import cn.suwg.springframework.beans.factory.BeanNameAware;
import cn.suwg.springframework.beans.factory.DisposableBean;
import cn.suwg.springframework.beans.factory.InitializingBean;
import cn.suwg.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanPostProcessor;
import cn.suwg.springframework.beans.factory.config.BeanReference;
import cn.suwg.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.suwg.springframework.core.convert.ConversionService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 自动创建bean工厂抽象类.
 *
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {


    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        //判断是否返回代理Bean对象.
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (null != bean) {
            return bean;
        }
        return doCreateBean(beanName, beanDefinition, args);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            // 实例化 Bean
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 处理循环依赖,将实例化的bean对象提前放入缓存，暴露出来
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }

            // 实例化后判断
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(bean, beanName);
            if (!continueWithPropertyPopulation) {
                return bean;
            }

            // 在设置 Bean 属性之前，允许 BeanPostProcessor 修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);

            // 给 Bean 填充属性信息
            applyPropertyValues(beanName, bean, beanDefinition);

            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 注册实现了DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        //判断SCOPE_SINGLETON、SCOPE_PROTOTYPE
        Object exposeObject = bean;
        if (beanDefinition.isSingleton()) {
            //获取代理对象.
            exposeObject = getSingleton(beanName);
            registerSingleton(beanName, exposeObject);
        }
        return exposeObject;
    }

    private Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object finalBean) {
        Object exposedObject = finalBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                if (null == exposedObject) {
                    return exposedObject;
                }

            }
        }
        return exposedObject;
    }

    private boolean applyBeanPostProcessorsAfterInstantiation(Object bean, String beanName) {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if (!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)) {
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }

    /**
     * 在设置 Bean 属性之前，允许 BeanPostProcessor 修改属性值
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(
                        beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }


    }

    private Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (null != result) {
                    return result;
                }
            }
        }
        return null;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非 Singleton 类型的Bean不执行销毁方法.
        if (!beanDefinition.isSingleton()) {
            return;
        }

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    /**
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 处理Aware标识的类. invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
        }


        //1. 执行BeanPostProcessor Before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        //2. 执行Bean的初始化方法
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        //3. 执行BeanPostProcessor After处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private Object applyBeanPostProcessorsBeforeInitialization(Object existBean, String beanName) {
        Object result = existBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }

        return result;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {

        // 1、实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2、 注解配置 init-method {判断是为了避免二次执行初始化}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (Objects.isNull(initMethod)) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    private Object applyBeanPostProcessorsAfterInitialization(Object existBean, String beanName) {
        Object result = existBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }


    private Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declareConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declareConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructor = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }


    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A依赖B, 获取B的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                } else {
                    //处理类型转换
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if (null != conversionService) {
                        if (conversionService.canConvert(sourceType, targetType)) {
                            value = conversionService.convert(value, targetType);
                        }
                    }

                }

                //属性填充.
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for bean: " + beanName);
        }
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
