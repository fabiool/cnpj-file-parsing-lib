BEGIN {
    FPAT = "([^,]*)|(\"[^\"]+\")"
    OFS = "\246"
}

$27 ~ /[0-9]{2}/ {
	print $17,$18,$19,$20,$21,$22,$23,$24,$25,$27
}