
i = open("DayFiveInput.txt", "r")

lines = i.readlines()

output = ""

data = []

for line in lines:
    arr = line.split(" ")
    data.append((int(arr[0].split(",")[0]),int(arr[0].split(",")[1]),int(arr[2].split(",")[0]),int(arr[2].split(",")[1])))


print(len(data))

def part_one():
    grid = [[0 for x in range(0, 1000)] for y in range(0, 1000)]
    for x0, y0, x1, y1 in data:
        if x0 == x1:
            for y in range(min(y0, y1),max(y0, y1)+1):
                grid[x0][y]+=1
        if y0 == y1:
            for x in range(min(x0, x1),max(x0, x1)+1):
                grid[x][y0]+=1

    count = 0
    for row in grid:
        for x in row:
            if x > 1:
                count += 1
    print(count)

def part_two():
    grid = [[0 for x in range(0, 1000)] for y in range(0, 1000)]
    for x0, y0, x1, y1 in data:
        x = x0
        y = y0
        grid[x][y] += 1
        while x != x1 or y != y1:
            if x1 > x:
                x += 1
            if x1 < x:
                x -= 1
            if y1 > y:
                y += 1
            if y1 < y:
                y -= 1
            grid[x][y] += 1

    count = 0
    for row in grid:
        for x in row:
            if x > 1:
                count += 1
    print(count)


# part_one()
part_two()

i.close()
