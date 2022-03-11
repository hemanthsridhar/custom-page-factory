# json-page-factory

JSON Page Factory, to read locators from JSON and Property File

[![Maven Central](https://img.shields.io/maven-central/v/com.github.hemanthsridhar/custom-page-factory.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.github.hemanthsridhar/custom-page-factory)

Please refer WIKI section for full tutorial

```java
<dependency>
  <groupId>com.github.hemanthsridhar</groupId>
  <artifactId>custom-page-factory</artifactId>
  <version>3.0.0</version>
</dependency>
```

**PageFactory Initialization**
```java
PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
```

```java
If we have a JSON for example
[
 {
"name": "forgottenPasswordLinkInAlert",
"type": "xpath",
"value": "//div[@role='alert']/descendant::a"
},
{
"name": "error_msg",
"type": "xpath",
"value": "//div[@role='alert']"
}
]
```

```java
@FilePath(value = PageObjectsConfig.ERROR_MSG_PAGE)
public class ErrorPopupPage extends PageInitializer {

@SearchBy
private WebElement forgottenPasswordLinkInAlert;

@SearchBy(nameOfTheLocator="error_msg")
private WebElement errorMsg;

}
```

```java
password_id=pass
user_name_id=email
loginButton_css=button[name='login']
allTextboxes_xpath=//input[@type='text']
something1_xpath=bla
```

```java
@FilePath(value = PageObjectsConfig.ERROR_MSG_PAGE)
public class ErrorPopupPage extends PageInitializer {

@SearchBy
private WebElement password;

@SearchBy(nameOfTheLocator="user_name")
private WebElement userName;

}
```

**SearchAll and SearchBys**

```java
@FilePath(value = PageObjectsConfig.LANDING_PAGE)
public class LandingPage extends PageInitializer {

@SearchAll(value = {    
@SearchBy( nameOfTheLocator = "allTextboxes"),
@SearchBy( nameOfTheLocator = "something1")})
private List<WebElement> allTextboxes;

@SearchBys(value = {    
@SearchBy( nameOfTheLocator = "allTextboxes"),
@SearchBy( nameOfTheLocator = "something1")})
private List<WebElement> allTextboxes;

}
```

