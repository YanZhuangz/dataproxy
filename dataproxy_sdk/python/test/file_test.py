# Copyright 2024 Ant Group Co., Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from dataproxy_sdk.python.dataproxy import (
    DataProxyConfig,
    UploadInfo,
    DownloadInfo,
    DataColumn,
    FileAdapter,
    FileFormat,
)
from dataproxy_sdk.python.test.dm_mock import DataMesh
from pyarrow.orc import write_table, read_table
import pyarrow as pa
import unittest


class TestFile(unittest.TestCase):
    def __init__(self, methodName: str = "runTest") -> None:
        super().__init__(methodName)
        self.dm = DataMesh()
        self.dm_ip = "127.0.0.1:24001"
        self.dm.start(self.dm_ip)

    def test_file(self):
        x = pa.array([2, 2, 4, 4, 5, 100])
        y = pa.array(
            ["Flamingo", "Parrot", "Dog", "Horse", "Brittle stars", "Centipede"]
        )
        schema = pa.schema([("x", pa.int32()), ("y", pa.string())])
        batch = pa.RecordBatch.from_arrays([x, y], schema=schema)

        test_file = "py_file_test.orc"
        table = pa.Table.from_batches([batch])
        write_table(test_file, table)

        config = DataProxyConfig(data_proxy_addr=self.dm_ip)
        file_adapter = FileAdapter(config)

        columns = []
        schema = batch.schema
        for name, type in zip(schema.names, schema.types):
            columns.append(DataColumn(name=str(name), type=str(type)))

        upload_info = UploadInfo(type="table", columns=columns)
        file_adapter.upload_file(upload_info, test_file, FileFormat.ORC)

        download_info = DownloadInfo(domaindata_id="test")
        result_file = "py_file_result.orc"
        file_adapter.download_file(download_info, result_file, FileFormat.ORC)

        result_table = read_table(result_file)

        self.assertTrue(result_table.equals(table))


if __name__ == "__main__":
    unittest.main()