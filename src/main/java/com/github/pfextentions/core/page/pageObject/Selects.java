package com.github.pfextentions.core.page.pageObject;

import com.github.pfextentions.core.page.pageObject.commands.options.OptionByText;
import com.github.pfextentions.core.page.pageObject.function.CommandFunction;

public class Selects {

    public static CommandFunction optionByText(String text) {
        return new OptionByText();
    }
}
