#!/bin/bash
jemalloc/bin/jeprof /usr/bin/java --show_bytes --svg  jeprof*.heap > pcap/organization-profiling.svg
