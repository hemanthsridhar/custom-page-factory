# json-page-factory

JSON Page Factory, to read locators from JSON and Property File

[![Maven Central](https://img.shields.io/maven-central/v/com.github.hemanthsridhar/custom-page-factory.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.github.hemanthsridhar/custom-page-factory)

Please refer WIKI section for full tutorial

```java
<dependency>
  <groupId>com.github.hemanthsridhar</groupId>
  <artifactId>custom-page-factory</artifactId>
  <version>3.3.2</version>
</dependency>
```

**PageFactory Initialization**

```java
PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
```

* **If we have a JSON for example**

```java
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

* **If we have a Property file for example**

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

**Handling Dynamic Locators**

* **If we have a JSON for example**
```java
[
  {
    "name": "multipleParamsLink",
    "type": "xpath",
    "value": "//a[normalize-space()='%1$s']/following-sibling::a[normalize-space()='%2$s']/following-sibling::a[normalize-space()='%3$s']/following-sibling::a[normalize-space()='%4$s']"
  }
]
```
Observe %s / %1$s, %2$s and so on are important

**PageFactory Initialization**

```java
PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);

protected ByLocators byLocators;

public InitPageObjects(WebDriver driver) {
        this.driver = driver;
        byLocators = PageFactoryLoader.newInstance().initElements(ByLocators.class);
    }

byLocators = PageFactoryLoader.newInstance().initElements(ByLocators.class);


    @FilePath(value = "classpath:page_objects/json/page_objects.json")
    protected interface ByLocators {

        @SearchBy
        By multipleParamsLink(String about, String advertising, String business, String howSearchWorks);

    }
```
* **Invocation**
```java
By imagesRelativeLink = RelativeLocator.with(byLocators.linkByText("Images")).toRightOf(byLocators.linkByText("Gmail"));
        driver.findElement(imagesRelativeLink).click();
```
