from os import listdir, rename
from os.path import isfile, join
from PIL import Image, ImageDraw, ImageFont
mypath = "..\\src\\main\\resources\\assets\\edora\\models\\item\\"

itemNames = ["silver","lead","transformium","lithium","magnesium","calcium","radium","uranium","plutonium","rhodium",
			 "titane","manganese","nickel","palladium","copper","oricalc"]

templateF = open("item_template.txt")
linesTemplate = templateF.readlines()
templateF.close()

fnt = ImageFont.truetype("C:\\Windows\\Fonts\\Arial.ttf", 10)

for itemName in itemNames:
  print("file for item : "+itemName + " in proccess...")
  f = open(mypath+itemName+".json", "w")
  for line in linesTemplate:
    new_file.write(line.replace("{$item_name}",itemName))

  new_file.close()
  print(itemName + ".json generate!")
  img = Image.new('RGB', (32, 32), color = (73, 109, 137))
  d.text((1,16), itemName, font=fnt, fill=(255,255,0))
  img.save("..\\src\\main\\resources\\assets\\edora\\textures\\item\\mineral\\"+itemName+'.png')
  print(itemName + ".png generate!"

print("Done !")