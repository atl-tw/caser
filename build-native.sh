#!/usr/bin/env bash

set -e
set +x

./mvnw -Pnative -Dagent=true compile compile exec:exec@java-agent test package