from os import listdir, rename
from os.path import isfile, join
mypath = "blockstates\\"
onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]

templateF = open("slab_template.txt")
linesTemplate = templateF.readlines()
templateF.close()

for fileName in onlyfiles: 
	f = open("blockstates//"+fileName, "r")

	name = fileName.replace("_slab.json","")

	lines = f.readlines()
	f.close()

	new_file = open(fileName, "w+")
	for line in lines:
		if line.strip("\n") != "    }" and line.strip("\n") != "  }" and line.strip("\n") != "}" :
			new_file.write(line)

	for line in linesTemplate:
		new_file.write(line.replace("type_block",name))

	new_file.close()
