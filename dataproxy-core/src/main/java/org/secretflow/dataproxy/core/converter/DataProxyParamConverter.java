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

package org.secretflow.dataproxy.core.converter;

import org.secretflow.v1alpha1.kusciaapi.Flightinner;

/**
 * @author yuexie
 * @date 2024/10/31 19:38
 **/
public interface DataProxyParamConverter<T, U, V> {

    T convert(Flightinner.CommandDataMeshSqlQuery request);

    U convert(Flightinner.CommandDataMeshQuery request);

    V convert(Flightinner.CommandDataMeshUpdate request);

}
