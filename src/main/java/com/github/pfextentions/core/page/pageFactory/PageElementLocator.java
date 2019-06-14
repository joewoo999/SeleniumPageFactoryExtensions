/*
 * Licensed to the Software Freedom Conservancy (SFC) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SFC licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.pfextentions.core.page.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

public class PageElementLocator implements ElementLocator {
    protected final SearchContext searchContext;
    protected final boolean shouldCache;
    protected final By by;
    protected WebElement cachedElement;
    protected List<WebElement> cachedElementList;
    protected int index = 0;

    /**
     * Creates a new element locator.
     *
     * @param searchContext The context to use when finding the element
     * @param field         The field on the Page Object that will hold the located value
     */
    public PageElementLocator(SearchContext searchContext, Field field) {
        this(searchContext, new Annotations(field));
    }

    /**
     * Use this constructor in order to process custom annotaions.
     *
     * @param searchContext The context to use when finding the element
     * @param annotations   AbstractAnnotations class implementation
     */
    public PageElementLocator(SearchContext searchContext, AbstractAnnotations annotations) {
        this(searchContext, annotations.isLookupCached(), annotations.buildBy());
    }

    public PageElementLocator(SearchContext searchContext, boolean shouldCache, By by) {
        this.searchContext = searchContext;
        this.shouldCache = shouldCache;
        this.by = by;
    }

    protected void setIndex(int index) {
        this.index = index;
    }

    /**
     * Find the element.
     */
    public WebElement findElement() {
        if (0 == index) {
            if (cachedElement != null && shouldCache()) {
                return cachedElement;
            }

            WebElement element = searchContext.findElement(by);
            if (shouldCache()) {
                cachedElement = element;
            }

            return element;
        } else {
            return findElements().get(index);
        }
    }

    /**
     * Find the element list.
     */
    public List<WebElement> findElements() {
        if (cachedElementList != null && shouldCache()) {
            return cachedElementList;
        }

        List<WebElement> elements = searchContext.findElements(by);
        if (shouldCache()) {
            cachedElementList = elements;
        }

        return elements;
    }

    /**
     * Returns whether the element should be cached.
     *
     * @return {@code true} if the element should be cached
     */
    protected boolean shouldCache() {
        return shouldCache;
    }

    @Override
    public String toString() {
        return "'" + by + "'";
    }
}
