#!/bin/bash

# Перевіряємо, чи вказана кількість
if [ -z "$1" ]; then
    echo "Не вказано кількість рядків"
    exit 1
fi

# Зчитуємо кількість рядків
N=$1

ps -eo pid,ppid,cmd,stat,time --sort=time | head -n $N
