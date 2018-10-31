#!/bin/bash
jemalloc/bin/pprof --show_bytes --gif /usr/bin/java jeprof*.out > pcap/app-profiling.gif


#jeprof --show_bytes --gif /path/to/jvm/bin/java jeprof*.out > /tmp/app-profiling.gif