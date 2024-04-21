#!/bin/bash

# Виводимо динамічний список процесів
top -o %MEM
# Виводимо на екран вміст файлу збереженої конфігурації
cat /home/kostia/.config/procps/toprc
