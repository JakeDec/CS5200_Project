filepath = './idToUserName.txt'

f = open('output.csv', 'w+', encoding="utf8")
with open(filepath, encoding="utf8") as fp:

   line1 = fp.readline().rstrip()
   line2 = fp.readline().rstrip()
   line3 = fp.readline().rstrip()
   cnt = 1
   while line1:
       f.write(line1 + "," + line2 + "\n")
       line1 = fp.readline().rstrip()
       line2 = fp.readline().rstrip()
       line3 = fp.readline().rstrip()
