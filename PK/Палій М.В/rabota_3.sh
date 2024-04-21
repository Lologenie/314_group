#!/bin/bash

# Перевіряємо, чи вказано матеріали
if [ -z "$1" ]; then
  echo "Не вказано матеріали"
  exit 1
fi

materials=$@

# Перебираємо матеріали
for material in $materials; do

  # Визначаємо вироб, до складу якого входить матеріал
  case $material in
  m1 | m2 | m3)
    echo "Виріб: bomba"
    count_bomba=$((count_bomba + 1))
    ;;
  s4 | s5 | s6)
    echo "Виріб: granata"
    count_granata=$((count_granata + 1))
    ;;
  *)
    echo "Невідповідний: $material"
    count_nevidpovidnih=$((count_nevidpovidnih + 1))
    ;;
  esac
done

echo "Summary:"
echo "- bomba: $count_bomba"
echo "- granata: $count_granata"
echo "- Невідповідних: $count_nevidpovidnih"
echo "Goodbay"
