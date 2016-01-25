import time
import sys
from pyspark import SparkContext

time.sleep(5)
logFile = sys.argv[1] + "/README.md"
sc = SparkContext("local", "Simple App")
logData = sc.textFile(logFile).cache()

numAs = logData.filter(lambda s: 'a' in s).count()
numBs = logData.filter(lambda s: 'b' in s).count()
time.sleep(5)
print("Lines with a: %i, lines with b: %i" % (numAs, numBs))
