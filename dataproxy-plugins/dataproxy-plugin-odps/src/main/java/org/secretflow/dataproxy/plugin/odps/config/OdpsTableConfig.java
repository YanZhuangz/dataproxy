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

package org.secretflow.dataproxy.plugin.odps.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.secretflow.v1alpha1.common.Common;

import java.util.List;

/**
 * transform from Kuscia DomainData
 *
 * @param tableName table name
 * @param partition partition
 * @param columns   columns, For the time being, use the DataColumn in pb, and then strip Kuscia from the core part of the design
 *
 * @author yuexie
 * @date 2024/11/7 16:43
 **/
public record OdpsTableConfig(String tableName, String partition, @JsonIgnore List<Common.DataColumn> columns) {
}
