#!/bin/bash

touch contents.txt

ls -l -a >contents.txt

sort -o contents-sorted.txt contents.txt

touch ~/Documents/last_10_lines.txt
tail -n 10 /etc/passwd >~/Documents/last_10_lines.txt

word_count=$(wc -w <contents.txt)
echo "Кількість слів у файлі contents.txt: $word_count" >>field2.txt

head -n 5 /etc/passwd | sort -r

tail -n 9 contents.txt | wc -c

find /usr/share -type f -name "test" | wc -l
