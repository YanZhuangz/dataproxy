#!/bin/bash
#
# Copyright 2023 Ant Group Co., Ltd.
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
#

set -e

VERSION_TAG=0.1
DATETIME=$(date +"%Y%m%d%H%M%S")
tag=${VERSION_TAG}-${DATETIME}
local_image=dataproxy:$tag
echo "$commit_id"
docker build -f ./build/Dockerfiles/dataproxy.Dockerfile --platform linux/amd64 -t "$local_image" .
