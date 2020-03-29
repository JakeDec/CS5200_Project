import csv

with open('GPDGrowth.txt') as csvfile:
    out = open("GPDGrowth.csv","w+")
    lines = csvfile.readlines()
    for line in lines:
        print(line[0:-6])
        out.write(line[0:-6]+"\n")