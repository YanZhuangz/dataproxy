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

import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.secretflow.dataproxy.common.utils.ArrowUtil;
import org.secretflow.dataproxy.plugin.odps.constant.OdpsTypeEnum;

import java.util.stream.Collectors;

/**
 * @author yuexie
 * @date 2024/11/10 21:59
 **/
public class OdpsWriteConfig extends OdpsCommandConfig<OdpsTableConfig> {

    public OdpsWriteConfig(OdpsConnectConfig odpsConnectConfig, OdpsTableConfig readConfig) {
        super(odpsConnectConfig, OdpsTypeEnum.TABLE, readConfig);
    }

    public OdpsWriteConfig(OdpsConnectConfig odpsConnectConfig, OdpsTypeEnum typeEnum, OdpsTableConfig readConfig) {
        super(odpsConnectConfig, typeEnum, readConfig);
    }

    @Override
    public String taskRunSQL() {
        return "";
    }

    @Override
    public Schema getResultSchema() {
        return new Schema(commandConfig.columns().stream()
                .map(column ->
                        Field.nullable(column.getName(), ArrowUtil.parseKusciaColumnType(column.getType())))
                .collect(Collectors.toList()));
    }
}