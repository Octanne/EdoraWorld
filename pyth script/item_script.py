from os import listdir, rename
from os.path import isfile, join
import os as os
from PIL import Image, ImageDraw, ImageFont

modelsPath = "gen\\assets\\edora\\models\\item\\"
texturesPath = "gen\\assets\\edora\\textures\\item\\"
font = ImageFont.truetype("C:\\Windows\\Fonts\\Arial.ttf", 12)

# create folder of path
if not os.path.exists(modelsPath):
  os.makedirs(modelsPath)
if not os.path.exists(texturesPath):
  os.makedirs(texturesPath+"\\mineral")
  
  
# ingot, compressed, dusted & crystal
itemsData = [["silver",True,False,True,False],
			 ["lead",True,False,True,False],
			 ["lithium",False,True,True,True],
             ["magnesium",False,True,True,False],
			 ["calcium",False,True,True,True]]

templateFile = open("json_template\\basic_item_template.txt","r")
linesTemplate = templateFile.readlines(-1)
templateFile.close()

javaAttributDef = []
javaAttributAsign = []


for itemData in itemsData:
  print("generation of : "+itemData[0]+" begin...")
  
  # Gen Textureplaceholder
  img = Image.new('RGB', (64, 64), color = (73, 109, 137))
  d = ImageDraw.Draw(img)
  d.text((4,8), itemData[0], font=font, fill=(255,255,0))

  if itemData[1] == True :
    # Java
    localName = "ingot_"+itemData[0]
    javaAttributDef.append("public static IngotMineralItem "+localName.upper()+";")
    javaAttributAsign.append(localName.upper()+" = new IngotMineralItem(new Settings().group(MINERAL_GROUP));")
    javaAttributAsign.append("Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, \""+localName.lower()+"\"), "+localName.upper()+");")
    print(localName.lower()+" java integration generate!")
    # Json
    f = open(modelsPath+localName.lower()+".json", "w")
    for line in linesTemplate:
      f.write(line.replace("{$item_name}",localName.lower()))
    f.close()
    print(localName.lower()+".json generate!")
    # TEXTURE
    img.save(texturesPath+"mineral\\"+localName.lower()+'.png')
    print(localName.lower() + ".png generate!")
  if itemData[2] == True :
    # Java
    localName = "compressed_"+itemData[0]
    javaAttributDef.append("public static CompressedMineralItem "+localName.upper()+";")
    javaAttributAsign.append(localName.upper()+" = new CompressedMineralItem(new Settings().group(MINERAL_GROUP));")
    javaAttributAsign.append("Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, \""+localName.lower()+"\"), "+localName.upper()+");")
    print(localName.lower()+" java integration generate!")
    # Json
    f = open(modelsPath+localName.lower()+".json", "w")
    for line in linesTemplate:
      f.write(line.replace("{$item_name}",localName.lower()))
    f.close()
    print(localName.lower()+".json generate!")
    # TEXTURE
    img.save(texturesPath+"mineral\\"+localName.lower()+'.png')
    print(localName.lower() + ".png generate!")
  if itemData[3] == True :
    # Java
    localName = "dusted_"+itemData[0]
    javaAttributDef.append("public static DustedMineralItem "+localName.upper()+";")
    javaAttributAsign.append(localName.upper()+" = new DustedMineralItem(new Settings().group(MINERAL_GROUP));")
    javaAttributAsign.append("Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, \""+localName.lower()+"\"), "+localName.upper()+");")
    print(localName.lower()+" java integration generate!")
    # Json
    f = open(modelsPath+localName.lower()+".json", "w")
    for line in linesTemplate:
      f.write(line.replace("{$item_name}",localName.lower()))
    f.close()
    print(localName.lower()+".json generate!")
    # TEXTURE
    img.save(texturesPath+"mineral\\"+localName.lower()+'.png')
    print(localName.lower() + ".png generate!")
  if itemData[4] == True :
    # Java
    localName = "crystal_"+itemData[0]
    javaAttributDef.append("public static CrystalMineralItem "+localName.upper()+";")
    javaAttributAsign.append(localName.upper()+" = new CrystalMineralItem(new Settings().group(MINERAL_GROUP));")
    javaAttributAsign.append("Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, \""+localName.lower()+"\"), "+localName.upper()+");")
    print(localName.lower()+" java integration generate!")
    # Json
    f = open(modelsPath+localName.lower()+".json", "w")
    for line in linesTemplate:
      f.write(line.replace("{$item_name}",localName.lower()))
    f.close()
    print(localName.lower()+".json generate!")
    # TEXTURE
    img.save(texturesPath+"mineral\\"+localName.lower()+'.png')
    print(localName.lower() + ".png generate!")

# Write java code on file
print("Write java on file...")
codeJavaFile = open("gen\\java_code.txt","w")
codeJavaFile.write("\n")
for line in javaAttributDef:
  codeJavaFile.write(line+"\n")
codeJavaFile.write("\n")
for line in javaAttributAsign:
  codeJavaFile.write(line+"\n")
codeJavaFile.close()

print("Job done!")