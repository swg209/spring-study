package cn.suwg.springframework.context;

import java.util.EventObject;

/**
 * Class to be extended by all application events. Abstract as it
 * doesn't make sense for generic events to be published directly.
 * 所有应用程序事件都应扩展的类。定义为抽象类，因为不直接发布通用事件。
 *
 * @Author: suwg
 * @Date: 2024/10/20
 * 公众号：趣研
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
