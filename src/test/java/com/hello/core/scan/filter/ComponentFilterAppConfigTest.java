package com.hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        // Given
         ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = (BeanA) ac.getBean(BeanA.class);
        assertThat(beanA).isNotNull();

        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> ac.getBean(BeanB.class)
                );

        // When
        // BeanA beanA = ac.getBean("beanA", BeanA.class);
        // BeanB beanB = ac.getBean("beanB", BeanB.class);

        // Then
        // Assertions.assertThat(beanA).isNotNull();
        // Assertions.assertThat(beanB).isNull();
    }

    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                   excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig {
        // This class would typically be annotated with @Configuration
        // and include component scanning configurations to include/exclude
        // certain components based on custom annotations like @MyIncludeComponent
        // and @MyExcludeComponent.
    }
}
