/*
 * Copyright 2012, Ufuk Uzun (http://www.ufukuzun.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ufukuzun.myth.dialect.processor;

import com.ufukuzun.myth.dialect.builder.AjaxEventBinding;
import com.ufukuzun.myth.dialect.builder.AjaxEventBindingBuilder;
import com.ufukuzun.myth.dialect.util.ElementAndAttrUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;

import java.util.HashMap;
import java.util.Map;

public class MythUpdateAttrProcessor extends AbstractAttributeModifierAttrProcessor {

    public static final String ATTR_NAME = "update";

    public static final String ATTR_NAME_WITH_PREFIX = ElementAndAttrUtils.getPrefixedName(ATTR_NAME);

    public MythUpdateAttrProcessor() {
        super(ATTR_NAME);
    }

    @Override
    public int getPrecedence() {
        return 12000;
    }

    @Override
    protected Map<String, String> getModifiedAttributeValues(Arguments arguments, Element element, String attributeName) {
        Map<String, String> values = new HashMap<String, String>();

        AjaxEventBinding ajaxEventBinding = AjaxEventBindingBuilder.build(arguments, element);
        values.put(ajaxEventBinding.getEventAttributeName(), ajaxEventBinding.getEventAttributeValue());

        return values;
    }

    @Override
    protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName, String newAttributeName) {
        return ModificationType.SUBSTITUTION;
    }

    @Override
    protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName, String newAttributeName) {
        return true;
    }

    @Override
    protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element, String attributeName) {
        return false;
    }

}
