#!/bin/bash

# Перевіряємо, чи вказаний аргумент
if [ -z "$1" ]; then
  echo "Не вказано назву директорії"
  exit 1
fi

# Зчитуємо назву директорії
directory_name=$1

# Перетворюємо назву директорії в шлях
directory_path=$(readlink -f $directory_name)

# Створюємо файл для запису результатів
file_name="dir_contents.txt"
touch $file_name

# Проходимося по директорії
for file in $(find $directory_path); do
  # Визначаємо тип файлу
  file_type=$(file -b $file | cut -d " " -f 2)

  # Записуємо інформацію про файл у файл
  echo "$file_type: $file" >> $file_name
done

# Виводимо шлях до файлу
echo "$file_name"
