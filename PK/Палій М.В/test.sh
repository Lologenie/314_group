#!/bin/bash
if [ -z "$1" ]; then
    echo "Не вказано матеріали"
    exit 1
fi

materials=($1)

# Перевіряємо, чи введено слово стоп
while [ "$materials" != "стоп" ]; do
    echo $materials

done
