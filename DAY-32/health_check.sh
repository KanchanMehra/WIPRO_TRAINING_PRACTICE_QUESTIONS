#!/bin/bash

echo "========================================"
echo "       SYSTEM HEALTH CHECK REPORT       "
echo "========================================"
echo "Timestamp: $(date)"
echo "Hostname: $(hostname)"
echo "========================================"

echo ""
echo ">>> DISK USAGE <<<"
echo "----------------------------------------"
df -h
echo ""

echo ">>> MEMORY USAGE <<<"
echo "----------------------------------------"
free -h
echo ""

echo ">>> RUNNING JAVA PROCESSES <<<"
echo "----------------------------------------"
ps aux | grep java | grep -v grep
if [ $? -ne 0 ]; then
    echo "No Java processes currently running."
fi
echo ""

echo "========================================"
echo "       HEALTH CHECK COMPLETE           "
echo "========================================"
