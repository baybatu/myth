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

package com.ufukuzun.myth.dialect.util;

import com.ufukuzun.myth.dialect.MythDialect;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

public final class ElementAndAttrUtils {

    private ElementAndAttrUtils() {
    }

    public static String getPrefixedName(String elementName) {
        return MythDialect.DIALECT_PREFIX + ":" + elementName;
    }

    public static String getProcessedAttributeValue(final Arguments arguments, final Element element, final String attributeName) {
        String attributeValue = element.getAttributeValue(attributeName);
        return getProcessedAttributeValue(arguments, attributeValue);
    }

    public static String getProcessedAttributeValue(final Arguments arguments, final String attributeValue) {
        Object result = null;

        try {
            IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(arguments.getConfiguration());
            IStandardExpression expression = expressionParser.parseExpression(arguments.getConfiguration(), arguments, attributeValue);
            result = expression.execute(arguments.getConfiguration(), arguments);
        } catch (Exception e) {
        }

        return result == null ? attributeValue : result.toString();
    }

}
