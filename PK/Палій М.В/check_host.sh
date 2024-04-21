#!/bin/bash
# Перевіряємо, чи вказано IP-адресу
if [ -z "$1" ]; then
  echo "Не вказано IP-адресу"
  exit 1
fi

# Зчитуємо IP-адресу
ip_address=$1

# Створюємо файл з результатами перевірки
file_name="check_host.log"
touch $file_name

# Перевіряємо доступність хоста
ping -c 1 $ip_address >/dev/null
if [ $? -eq 0 ]; then
  # Хост доступний
  # Записуєм результати перевірки в файл
  echo "IP-адреса: $ip_address" >>$file_name
  echo "Час перевірки: $(date)" >>$file_name
  echo "Статус: доступний" >>$file_name

  # Виводимо шлях до файлу
  echo "$file_name"
else
  # Хост недоступний
  echo "IP-адреса: $ip_address" >>$file_name
  echo "Час перевірки: $(date)" >>$file_name
  echo "Статус: не доступний" >>$file_name
  echo "Хост недоступний"
fi
