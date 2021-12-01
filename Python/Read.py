
i = open("DayOneInput.txt", "r")

lines = i.readlines()

output = []

for line in lines:
    arr = line.split()
    for x in arr:
        try:
            output.append(int(x))
        except:
            pass

o = open("DayOneOutput.txt", "w")
o.write(str(output))

i.close()
o.close()
