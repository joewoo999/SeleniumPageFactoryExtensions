package com.github.pfextentions.BaiduExample;

import com.github.pfextentions.core.driverContext.DriverFactory;
import com.github.pfextentions.core.page.pageObject.functions.Actions;
import com.github.pfextentions.core.page.pageObject.functions.Options;
import com.github.pfextentions.core.page.pageObject.expectedCondtion.Be;
import com.github.pfextentions.core.page.pageObject.functions.Radios;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class BaiduPageTest {


    @BeforeClass
    public static void before() {
        DriverFactory.setUp();
    }

    @Test
    public void test() {
        BaiduPage p = new BaiduPage();
        p.open();
        p.maximizeWindow();

        p.searchBox.waitUntil(Be.visible, 10);
        p.assertPageOpened();

        p.setting.perform(Actions.MOVE_TO);
        sleep();
        p.seniorSearchLink.waitUntil(Be.visible, 10);
        p.seniorSearchLink.perform(Actions.CLICK);
        sleep();
        p.keyword1.should(Be.visible);
        p.keyword1.perform(Actions.type_text("fksuww"));
        p.searchYear.select(Options.byText("最近一天"));
//        p.searchYear.select(Options.byText("stf"));
        System.out.println(p.searchYear.select(Options.SELECTED_TEXT));
        p.searchYear.select(Options.byIndex(4));
        System.out.println(p.searchYear.select(Options.SELECTED_TEXT));
        sleep();
        p.keywordsPosition.select(Radios.byValue("2"));
        p.keywordsPosition.select(Radios.byLabelText("仅网页的标题中"));
        p.keywordsPosition.select(Radios.byIndex(0));
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


    public static void main(String[] args) {
    }
}





