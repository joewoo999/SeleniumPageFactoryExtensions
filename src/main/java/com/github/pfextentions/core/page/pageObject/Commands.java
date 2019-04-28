package com.github.pfextentions.core.page.pageObject;

import com.github.pfextentions.core.page.pageObject.commands.*;
import org.openqa.selenium.UnsupportedCommandException;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Commands {

    private Map<String, Class<? extends Command>> commands = new ConcurrentHashMap<>();
    private Map<String, Command> cachedCommands = new ConcurrentHashMap<>();


    public static Commands getInstance() {
        return StaticCommands.INSTANCE;
    }

    private static class StaticCommands {
        public static Commands INSTANCE = new Commands();
    }

    private Commands() {
        addAll();
    }

    public Command get(String methodName) {
        Command command = cachedCommands.get(methodName);
        if (null != command)
            return command;

        try {
            Class<? extends Command> cmdClass = commands.get(methodName.toLowerCase());

            if (null == cmdClass)
                throw new UnsupportedCommandException("Unsupported command: " + methodName);

            command = cmdClass.getDeclaredConstructor().newInstance();
            cachedCommands.put(methodName, command);
            return command;

        } catch (InstantiationException | InvocationTargetException
                | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Class<? extends Command> clazz) {
        commands.put(clazz.getSimpleName().toLowerCase(), clazz);
    }

    public void remove(Class clazz) {
        commands.remove(clazz.getSimpleName().toLowerCase());
    }

    public void removeAll() {
        commands.clear();
        cachedCommands.clear();
    }

    private void addAll() {
        add(Clear.class);
        add(Click.class);
        add(Sendkeys.class);
        add(Submit.class);
        add(FindElement.class);
        add(FindElements.class);

        add(GetText.class);
        add(GetValue.class);
        add(GetAttribute.class);
        add(GetCssValue.class);
        add(GetTagName.class);
        add(GetLocation.class);
        add(GetSize.class);
        add(GetRect.class);
        add(GetWebElement.class);
        add(GetScreenshotAs.class);
        add(GetScreenshot.class);

        add(IsDisplayed.class);
        add(IsEnabled.class);
        add(IsSelected.class);
        add(IsPresent.class);

        add(UploadFile.class);
        add(Should.class);
        add(Perform.class);
        add(Select.class);
        add(DoubleClick.class);
        add(WaitUntil.class);
        add(WaitWhile.class);

        add(ScrollToCenter.class);
        add(ClickByJS.class);
        add(SetValue.class);
    }
}
