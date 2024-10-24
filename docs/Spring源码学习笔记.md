# Spring手写专栏介绍

## 前言

### Spring是什么？

Spring框架是一个轻量级的开源Java框架，旨在提高开发效率和系统可维护性。其核心是IOC（控制反转）和AOP（面向切面编程），提供了丰富的功能模块，如数据访问、Web支持、集成等。Spring使Java企业级开发更加简单，并为POJO对象提供企业级服务。



### 为什么要学习Spring源码？

1. **深入理解框架**：通过学习源码，可以深入了解Spring框架的内部机制和工作原理，从而更加全面地掌握其提供的各种功能和特性。
2. **提升编程能力**：Spring源码中蕴含了丰富的编程思想和设计模式，如依赖注入、面向切面编程等。学习源码有助于掌握这些先进的编程理念，并能在实际开发中灵活运用，提升个人的编程能力和代码质量。
3. **增强问题解决能力**：在遇到问题时，通过查阅源码，可以更准确地定位问题所在，并找到有效的解决方案。这不仅能提高开发效率，还能增强解决复杂问题的能力。
4. **提升职业竞争力**：掌握Spring源码的开发者在求职或晋升时更具优势，因为企业往往更倾向于招聘具备深厚技术功底和问题解决能力的候选人。



## 目标

依托小傅哥的《手写Spring：渐进式源码实践》书籍，以 Spring 源码学习为目的，跟随书中的指导，一点点实现简化版 Spring 框架，了解 Spring 核心原理，为后续再深入学习 Spring 打下基础。

手写简化版Spring的过程中，会重点摘取Spring整体框架中的核心逻辑，简化代码实现过程，保留核心功能，例如：IOC、AOP、Bean生命周期、上下文、作用域、资源处理等内容实现。

内容分章节交付，每一节对应一个功能实现点，跟着一步步实现，完成开发一个相对完整的small-spring框架。希望这个过程，你能跟着一起打卡学习，最后你对spring框架的理解一定可以更进一层。





## 计划

《手写*Spring渐进式*源码实践》共21章，1-10章实现IOC功能，11-15实现AOP功能，16-21实现进阶设计功能，22章提供常见笔试、面试题。后续会不断更新章节的学习笔记，敬请期待，目录结构如下：

- [ ] 第1章 开始实现一个简单的Spring Bean容器 | step1-bean-init
- [ ] 第2章 基于模板模式实现对象定义、注册和获取 | step2-spring-bean
- [ ] 第3章 基于策略模式实现含构造函数的类实例化  | step3-cglib-constructor
- [ ] 第4章 注入属性和依赖对象 | step4-property-beanreference
- [ ] 第5章 将资源文件中定义的对象注册到容器中
- [ ] 第6章 实现ApplicationContext
- [ ] 第7章 Bean对象的初始化和销毁
- [ ] 第8章 感知容器对象
- [ ] 第9章 对象Scope和FactoryBean
- [ ] 第10章 基于观察者模式的容器事件发布
- [ ] 第11章 基于JDK、Cglib实现对象动态代理
- [ ] 第12章 将AOP融入Bean声明周期
- [ ] 第13章 自动扫描注册Bean对象
- [ ] 第14&15章 通过注解注入属性信息
- [ ] 第16章 通过三级缓存解决循环依赖
- [ ] 第17章 类型转换
- [ ] 第18章 JDBC功能整合
- [ ] 第19章 事务处理
- [ ] 第20章 简单ORM框架实现
- [ ] 第21章 将ORM框架整合到Spring容器中
- [ ] 第22章 常见笔面试题



参考着书中的思路指导，保持1天撸完对应章节代码，然后补充对应章节的笔记，包括类图、实现步骤、重点代码解析、运行结果截图等。在这个过程中我也会阅读不少资料以及官网上的文档，最终把相对那些符合当前章节有价值的内容，一并展示给读者学习，同时也满足个人对技术内容的积累。





## 源码

整个 `Spring手写`专栏的源码地址、学习笔记的开展思路，提前给到大家，方便大家对后续章节内容的结构，有清晰的认知，后续可以顺利跟着学习到这部分内容。

学习笔记输出结构：参考STAR法则（S：背景描述   T: 任务描述  A:  行动步骤   R: 测试验证结果）

文章结构： 前言+目标+设计+实现+测试

源码实现：https://github.com/swg209/spring-study.git





## 总结

- Spring经历了多个版本的迭代，直接上手看源码，会让人一脸懵。通过做一个简易版的Spring框架，对代码剥丝抽茧，体现核心功能，把最直接相干的设计思路呈现出来，我们再进行学习，才更容易理解。
- 在源码学习的过程中，笔者会和你一起从最简单、最简单的Bean容器开始，逐步建立一些知识关联，从而使我们在这个学习过程中，收获更多。
- 关于 `Spring 手写`专栏的开篇介绍，整体计划愿景，已经给到你啦。接下来你可以阅读到文章、获取到源码，直至我们把所有的内容全部完成，到时候就可以开发出一个相对完整的 Spring 框架了。希望在这个过程中你能和我一直坚持学习打卡！



> 参考书籍：《手写*Spring渐进式*源码实践》
>
> 书籍源代码：https://github.com/fuzhengwei/small-spring