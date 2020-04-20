#!/bin/bash
INPUT_FILE=$1
TARGET_COUNT=$2
awk "
NR==FNR { next }
FNR==1 {
  srand()
  n=NR-1
  for(i=1; i<=${TARGET_COUNT}; i++) {
    line=0
    while(!line || line in A) line=int(rand()*n)+1
    A[line]
  }
} 
FNR in A
" $INPUT_FILE $INPUT_FILE
