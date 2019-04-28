package com.github.pfextentions.core.page.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.Objects;

public enum PageElementType {

    INPUT("input", null),
    INPUT_TEXT("input", "text"),
    INPUT_FILE("input", "file"),
    RADIO("input", "radio"),
    CHECKBOX("input", "checkbox"),

    TEXTAREA("textarea", null),
    BUTTON("button", "button"),
    SELECT("select", null),
    LINK("a", null);


    private String tagName;
    private String type;

    PageElementType(String tagName, String type) {
        this.tagName = tagName;
        this.type = type;
    }

    public WebElement findAndAssertElementType(ElementLocator locator) {
        StringBuffer b = new StringBuffer();
        WebElement element = locator.findElement();
        String tag = element.getTagName();
        String tp = element.getAttribute("type");

        if (Objects.nonNull(tagName)) {
            if (!tagName.equalsIgnoreCase(tag))
                b.append(String.format("\n\t\ttag name: actual '%s' is not expected '%s'.", tag, tagName));
        }

        if (Objects.nonNull(type)) {
            if (!this.type.equalsIgnoreCase(tp))
                b.append(String.format("\n\t\tattribute type: actual '%s' is not expected '%s'.", tp, type));
        }

        if (b.length() > 0)
            throw new AssertionError(String.format("ELement:%s type is not matched, %s", element, b));

        return element;
    }

}

