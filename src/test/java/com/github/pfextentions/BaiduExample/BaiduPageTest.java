package com.github.pfextentions.BaiduExample;

import com.github.pfextentions.core.driverContext.DriverFactory;
import com.github.pfextentions.core.page.pageObject.Commands;
import com.github.pfextentions.core.page.pageObject.expectedCondtion.Be;
import com.github.pfextentions.core.page.pageObject.functions.Action;
import com.github.pfextentions.core.page.pageObject.functions.Option;
import com.github.pfextentions.core.page.pageObject.functions.Radio;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class BaiduPageTest {


    @BeforeClass
    public static void before() {
        DriverFactory.setUp();
        Commands.getPropertiesInstance().add("extext", ExText.class);
    }

    @Test
    public void test() {
        var p = new BaiduPage();
        p.open();
        p.maximizeWindow();

        p.searchBoxEx.exText();
        System.out.println(p.all.get(0));
        System.out.println(p.exAll.size());
        p.exAll.get(0).exText();
        System.out.println(p.exAll.isEmpty());
        p.exAll.get(0).getText();
        p.searchBox.waitUntil(Be.visible, 10);
        p.assertPageOpened();

        p.setting.perform(Action.MOVE_TO);
        sleep();
        p.seniorSearchLink.waitUntil(Be.visible, 10);
        p.seniorSearchLink.perform(Action.CLICK);
        sleep();
        p.keyword1.should(Be.visible);
        p.keyword1.perform(Action.type_text("fksuww"));
        p.searchYear.select(Option.byText("最近一天"));
        System.out.println(p.searchYear.select(Option.GET_SELECTED_TEXT));
        p.searchYear.select(Option.byIndex(4));
        System.out.println(p.searchYear.select(Option.GET_SELECTED_TEXT));
        sleep();
        p.keywordsPosition.select(Radio.byValue("6"));
        p.keywordsPosition.select(Radio.byLabelText("仅网页的标题中"));
        p.keywordsPosition.select(Radio.byIndex(0));
        sleep();
    }

    @AfterClass
    public static void after() {
        DriverFactory.tearDown();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}





