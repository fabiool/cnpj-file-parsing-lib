#!/bin/bash

OUTPUT_FOLDER=$1
INPUT_FILE=$2

mkdir -p "${OUTPUT_FOLDER}"

while read -r line
do
  echo "$line"
  
  declare -i k=0

  for e in $line; do

  	k=$((k + 1))

  	SUFIX=$(printf "%02d" ${k})

  	FILE_NAME="${OUTPUT_FOLDER}/COMPONENT_${SUFIX}.tmp"

  	echo $e >> "${FILE_NAME}"

  done

done < "${INPUT_FILE:-/dev/stdin}"

for f in $(ls "${OUTPUT_FOLDER}"); do 

	echo $f

	cat "${OUTPUT_FOLDER}/${f}" | sort -u > "${OUTPUT_FOLDER}/$(echo $f | sed 's/tmp/txt/g')"

done

# rm "${OUTPUT_FOLDER}"/*.tmp