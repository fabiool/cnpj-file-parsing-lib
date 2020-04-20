#!/bin/bash

TARGET_FILE=$1
OUTPUT_FOLDER=$2

declare -i ROWS_COUNT=$(wc -l "$TARGET_FILE" | cut -d ' ' -f 1)
declare -i DATA_ROWS=$(( $ROWS_COUNT - 1 ))

echo "Processing file $TARGET_FILE. Please wait." 

tail -$DATA_ROWS "${TARGET_FILE}" \
     | cut -d ',' -f 5-6 \
     | grep "^\"2\"" \
     | cut -d ',' -f 2 \
     | tr -d '"' \
     | ./extract_name_components.sh "${OUTPUT_FOLDER}"

echo "Processing complete!" 