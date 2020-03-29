import csv

with open('metacriticGameInfo.csv') as csvfile:
    out = open("mcGameInfoGenre.csv","w+")
    readCSV = csv.reader(csvfile, delimiter=',')
    for row in readCSV:
        name = row[1]
        genre = row[4].split(";")
        for g in genre:
            out.write("\"%s\",%s\n" % (name, g.strip()))