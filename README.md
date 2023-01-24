Filed as [Spring Boot issue #33969](https://github.com/spring-projects/spring-boot/issues/33969).

The service itself works as expected:
```
$ ./gradlew bootRun
$ curl -s localhost:8080 | jq
{
  "first": {
    "someProperty": 1
  }
}
```

However, the test fails with Spring Boot 3.0.2, but works with 3.0.0, and 3.0.1:
```
$ ./gradlew test
```

```
***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to bind properties under 'app.my-config.first' to com.example.demo.SomeConfig$ChildConfig:

    Property: app.my-config.first.some-property
    Value: "1"
    Origin: class path resource [application.yml] - 4:21
    Reason: java.lang.IllegalStateException: No setter found for property: some-property


2023-01-24T22:44:22.453+01:00 ERROR 71630 --- [    Test worker] o.s.test.context.TestContextManager      : Caught exception while allowing TestExecutionListener [org.springframework.test.context.support.DependencyInjectionTestExecutionListener] to prepare test instance [com.example.demo.ControllerTest@2e7af36e]

java.lang.IllegalStateException: Failed to load ApplicationContext for [WebMergedContextConfiguration@4a058df8 testClass = com.example.demo.ControllerTest, locations = [], classes = [com.example.demo.DemoApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = ["org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTestContextBootstrapper=true"], contextCustomizers = [org.springframework.boot.test.autoconfigure.OverrideAutoConfigurationContextCustomizerFactory$DisableAutoConfigurationContextCustomizer@6138e79a, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@9da1, org.springframework.boot.test.autoconfigure.filter.TypeExcludeFiltersContextCustomizer@7e546387, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@4c20d68, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@5f0e9815, [ImportsContextCustomizer@4b56b031 key = [com.example.demo.SomeTestConfig, org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration, org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration, org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration, org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration, org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration, org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration, org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration, org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration, org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration, org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration, org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration, org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration, org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration, org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration, org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration, org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration, org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebDriverAutoConfiguration, org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration, org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityConfiguration, org.springframework.boot.test.autoconfigure.web.reactive.WebTestClientAutoConfiguration]], org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@37091312, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@550a1967, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.context.SpringBootTestAnnotation@19aa2405], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:142) ~[spring-test-6.0.4.jar:6.0.4]
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127) ~[spring-test-6.0.4.jar:6.0.4]
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:141) ~[spring-test-6.0.4.jar:6.0.4]
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:97) ~[spring-test-6.0.4.jar:6.0.4]
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:241) ~[spring-test-6.0.4.jar:6.0.4]
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:138) ~[spring-test-6.0.4.jar:6.0.4]
  …
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'controller' defined in file [/Users/chris/code/demo/build/classes/kotlin/main/com/example/demo/Controller.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'someConfig': Could not bind properties to 'SomeConfig' : prefix=app.my-config, ignoreInvalidFields=false, ignoreUnknownFields=true
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:798) ~[spring-beans-6.0.4.jar:6.0.4]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:245) ~[spring-beans-6.0.4.jar:6.0.4]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1344) ~[spring-beans-6.0.4.jar:6.0.4]
  …
Caused by: org.springframework.boot.context.properties.ConfigurationPropertiesBindException: Error creating bean with name 'someConfig': Could not bind properties to 'SomeConfig' : prefix=app.my-config, ignoreInvalidFields=false, ignoreUnknownFields=true
	at org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.bind(ConfigurationPropertiesBindingPostProcessor.java:99) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.postProcessBeforeInitialization(ConfigurationPropertiesBindingPostProcessor.java:79) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:420) ~[spring-beans-6.0.4.jar:6.0.4]
  …
Caused by: org.springframework.boot.context.properties.bind.BindException: Failed to bind properties under 'app.my-config.first' to com.example.demo.SomeConfig$ChildConfig
	at org.springframework.boot.context.properties.bind.Binder.handleBindError(Binder.java:387) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder.bind(Binder.java:347) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder.lambda$bindDataObject$4(Binder.java:472) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.JavaBeanBinder.bind(JavaBeanBinder.java:98) ~[spring-boot-3.0.2.jar:3.0.2]
  …
Caused by: java.lang.IllegalStateException: No setter found for property: some-property
	at org.springframework.boot.context.properties.bind.JavaBeanBinder.bind(JavaBeanBinder.java:107) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.JavaBeanBinder.bind(JavaBeanBinder.java:86) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.JavaBeanBinder.bind(JavaBeanBinder.java:62) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder.lambda$bindDataObject$5(Binder.java:476) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder$Context.withIncreasedDepth(Binder.java:590) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder$Context.withDataObject(Binder.java:576) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder.bindDataObject(Binder.java:474) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder.bindObject(Binder.java:414) ~[spring-boot-3.0.2.jar:3.0.2]
	at org.springframework.boot.context.properties.bind.Binder.bind(Binder.java:343) ~[spring-boot-3.0.2.jar:3.0.2]
	... 140 common frames omitted
```
