package org.test.selenium.constants.json;

public interface PageObjectsConfig {
    String BASE_PAGE_OBJECT_PATH = "src/test/resources/page_objects/json";
    String ERROR_MSG_PAGE = BASE_PAGE_OBJECT_PATH + "/error_message_page_objects.json";
    String LANDING_PAGE = BASE_PAGE_OBJECT_PATH + "/landing_page_objects.json";
    String INVALID_PATH_PAGE = BASE_PAGE_OBJECT_PATH + "/does_not_exist.json";
}
