import csv

with open('videoGameSales2016.csv') as csvfile:
    out = open("videoGameSales2016out.csv","w+")
    readCSV = csv.reader(csvfile, delimiter=',')
    for row in readCSV:
        name = row[0]
        platform = row[1]
        year = row[2]
        genre = row[3]
        publisher = row[4]
        na = row[5]
        eu = row[6]
        jp = row[7]
        other = row[8]
        globalsa = row[9]
        temp = row[10]
        temp = row[11]
        temp = row[12]
        temp = row[13]
        dev = row[14]
        rating = row[15]
        out.write("\"%s\",%s,%s,%s,\"%s\",%s,%s,%s,%s,%s,\"%s\",%s\n" % (name, platform, year, genre, publisher, na, eu, jp,other,globalsa,dev, rating))