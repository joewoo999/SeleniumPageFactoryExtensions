package com.github.pfextentions.BaiduExample;

import com.github.pfextentions.core.page.AbstractPage;
import com.github.pfextentions.core.page.pageObject.PageElement;
import com.github.pfextentions.core.page.pageObject.expectedCondtion.Be;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;


public class BaiduPage extends AbstractPage {

    @Override
    public String url() {
        return "https://www.baidu.com/";
    }

    @Override
    public String title() {
        return "百度一下，你就知道";
    }

    @Override
    public void assertPageOpened() {
        searchBox.should(Be.visible);
    }

    @FindBy(id = "kw")
    PageElement searchBox;

    @FindBy(name = "tj_briicon")
    PageElement more;

    @FindBy(name = "tj_more")
    PageElement allProducts;

    @FindBy(xpath = "//a[.='贴吧']")
    PageElement tieba;

    @FindBy(id = "su")
    PageElement searchBtn;

    @FindBy(css = "div#u1 a[name='tj_settingicon']")
    PageElement setting;

    @FindBy(xpath = "//div[@class='bdpfmenu']/a[.='高级搜索']")
    PageElement seniorSearchLink;

    @FindBy(name = "q1")
    PageElement keyword1;

    @FindBy(name = "gpc")
    PageElement searchYear;

    @FindBy(name = "q5")
    PageElement keywordsPosition;

    @FindBy(id = "xx")
    PageElement wr;

    @FindAll({@FindBy(tagName = "span")})
    List<PageElement> all;

    @FindBys({@FindBy(css = "#form"), @FindBy(id = "kw")})
    PageElement bys;

}
