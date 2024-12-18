/*
 * Copyright 2024 Ant Group Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.secretflow.dataproxy.core.visitor;

/**
 * @author yuexie
 * @date 2024/11/1 20:24
 **/
public class ShortValueVisitor implements ValueVisitor<Short> {
    @Override
    public Short visit(boolean value) {
        return value ? (short) 1 : (short) 0;
    }

    @Override
    public Short visit(Short value) {
        return value;
    }

    @Override
    public Short visit(Integer value) {
        return value.shortValue();
    }

    @Override
    public Short visit(Long value) {
        return value.shortValue();
    }

    @Override
    public Short visit(Float value) {
        return value.shortValue();
    }

    @Override
    public Short visit(Double value) {
        return value.shortValue();
    }

    @Override
    public Short visit(String value) {
        return Short.valueOf(value);
    }

    @Override
    public Short visit(Object value) {

        if (value instanceof Short shortValue) {
            return this.visit(shortValue);
        }

        return this.visit(value.toString());
    }
}
