package com.github.pfextentions.BaiduExample;

import com.github.pfextentions.core.factory.DriverFactory;
import com.github.pfextentions.core.page.pageObject.commands.options.GetSelectedOptionText;
import com.github.pfextentions.core.page.pageObject.commands.options.OptionByIndex;
import com.github.pfextentions.core.page.pageObject.commands.options.OptionByText;
import com.github.pfextentions.core.page.pageObject.commands.options.OptionByValue;
import com.github.pfextentions.core.page.pageObject.expectedCondtion.Be;
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

//        p.setting.perform(MoveTo::new);
        sleep();
        p.seniorSearchLink.waitUntil(Be.visible, 10);
        //p.seniorSearchLink.perform(LeftClick::new);
        sleep();
        p.keyword1.should(Be.visible);
        //p.keyword1.perform(new TypeText("fksuww"));
        p.searchYear.select(OptionByText::new, "最近一天");
        p.searchYear.select(OptionByValue::new, "stf");
        System.out.println(p.searchYear.select(GetSelectedOptionText::new));
        p.searchYear.select(OptionByIndex::new, 4);
        System.out.println(p.searchYear.select(GetSelectedOptionText::new));
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





