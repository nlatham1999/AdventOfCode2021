
i = open("DayTwoInput.txt", "r")

lines = i.readlines()

output = ""

for line in lines:
    arr = line.split()
    output+= ("new Pair<String,Integer>(\"" + arr[0] + "\"," + arr[1] + "),") 

o = open("DayTwoOutput.txt", "w")
o.write(str(output))

print(len(output))


i.close()
o.close()
