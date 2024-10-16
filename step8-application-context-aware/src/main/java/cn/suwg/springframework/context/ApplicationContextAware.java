package cn.suwg.springframework.context;

import cn.suwg.springframework.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 * <p>
 * 实现此接口，能感知到所属的 ApplicationContext.
 *
 * @Author: suwg
 * @Date: 2024/10/16
 * 公众号： 趣研
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext);
}
