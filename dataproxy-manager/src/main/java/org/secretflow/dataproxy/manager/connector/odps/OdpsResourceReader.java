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
package org.secretflow.dataproxy.manager.connector.odps;

import org.apache.arrow.memory.BufferAllocator;
import org.secretflow.dataproxy.common.model.datasource.conn.OdpsConnConfig;
import org.secretflow.dataproxy.common.model.datasource.location.OdpsTableInfo;
import org.secretflow.dataproxy.manager.DataReader;
import org.secretflow.dataproxy.manager.SplitReader;

import java.util.List;

/**
 * odps Resource Reader
 *
 * @author yuexie
 * @date 2024-06-01 17:08:45
 */
public class OdpsResourceReader implements DataReader {

    private final OdpsConnConfig odpsConnConfig;
    private final BufferAllocator allocator;
    private final OdpsTableInfo tableInfo;

    public OdpsResourceReader(BufferAllocator allocator, OdpsConnConfig odpsConnConfig, OdpsTableInfo tableInfo) {
        this.odpsConnConfig = odpsConnConfig;
        this.allocator = allocator;
        this.tableInfo = tableInfo;
    }

    @Override
    public List<SplitReader> createSplitReader(int splitNumber) {
        return List.of(new OdpsResourceSplitReader(allocator, odpsConnConfig, tableInfo));
    }
}