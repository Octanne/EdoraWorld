from os import listdir, rename
from os.path import isfile, join
import os as os
from PIL import Image, ImageDraw, ImageFont

modelsItemPath = "gen\\assets\\edora\\models\\item\\"
modelsBlockPath = "gen\\assets\\edora\\models\\block\\"
texturesItemPath = "gen\\assets\\edora\\textures\\item\\"
texturesBlockPath = "gen\\assets\\edora\\textures\\block\\"
blockstatesPath = "gen\\assets\\edora\\blockstates\\"
loottablesPath = "gen\\data\\edora\\loot_tables\\blocks\\"
font = ImageFont.truetype("C:\\Windows\\Fonts\\Arial.ttf", 12)

# create folder of path
if not os.path.exists(modelsItemPath):
  os.makedirs(modelsItemPath)
if not os.path.exists(loottablesPath):
  os.makedirs(loottablesPath)
if not os.path.exists(modelsBlockPath):
  os.makedirs(modelsBlockPath)
if not os.path.exists(texturesBlockPath):
  os.makedirs(texturesBlockPath+"\\mineral")
if not os.path.exists(texturesItemPath):
  os.makedirs(texturesItemPath+"\\mineral")
if not os.path.exists(blockstatesPath):
  os.makedirs(blockstatesPath)
  
# name, fortune?, min drop?, max drop? & mining level 
# (0 -> Wooden / Golden Pickaxe 1 -> Stone Pickaxe 2 -> Iron Pickaxe 3 -> Diamond Pickaxe 4 -> Netherite Pickaxe)
oresData = [["silver",True,1,2,2],
             ["magnesium",True,2,6,2],
             ["oricalc",True,4,5,2],
             ["transformium",True,3,5,2],
             ["bauxyte",True,1,3,2]]
             
# Load Templates

# Model raw Item
templateFile = open("json_template\\basic_item_template.txt","r") # {$item_name}
item_template = templateFile.readlines(-1)
templateFile.close()
# Model itemBlock
templateFile = open("json_template\\block_item_template.txt","r") # {$block_name}
blockItem_template = templateFile.readlines(-1)
templateFile.close()
# Model block
templateFile = open("json_template\\block_template.txt","r") # {$block_name}
block_template = templateFile.readlines(-1)
templateFile.close()
# Blockstates
templateFile = open("json_template\\blockstates_template.txt","r") # {$block_name}
blockstates_template = templateFile.readlines(-1)
templateFile.close()

# loot_tables
templateFile = open("json_template\\ore_loot_tables\\normal.txt","r") # {$block_name} {$drop_item} {$loot_min} {$loot_max}
normal_loottables = templateFile.readlines(-1)
templateFile.close()

templateFile = open("json_template\\ore_loot_tables\\normal_one_drop.txt","r") # {$block_name} {$drop_item}
normal_one_loottables = templateFile.readlines(-1)
templateFile.close()

templateFile = open("json_template\\ore_loot_tables\\with_fortune.txt","r") # {$block_name} {$drop_item} {$loot_min} {$loot_max}
fortune_loottables = templateFile.readlines(-1)
templateFile.close()

templateFile = open("json_template\\ore_loot_tables\\with_fortune_one_drop.txt","r") # {$block_name} {$drop_item}
fortune_one_loottables = templateFile.readlines(-1)
templateFile.close()

# Java code
javaAttributDef = []
javaAttributAsign = []

for oreData in oresData:
  print("generation of : "+oreData[0]+" begin...")
  
  block_name = "ore_"+oreData[0]
  
  # Gen Texture placeholder
  img = Image.new('RGB', (64, 64), color = (73, 109, 137))
  d = ImageDraw.Draw(img)
  d.text((4,8), oreData[0], font=font, fill=(255,255,0))
  
  # Item raw mineral
  raw_item_name = "raw_"+oreData[0]
  javaAttributDef.append("public static RawMineralItem "+raw_item_name.upper()+";")
  javaAttributAsign.append(raw_item_name.upper()+" = new RawMineralItem(new Settings().group(EdoraItems.MINERAL_GROUP));")
  javaAttributAsign.append("Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, \""+raw_item_name.lower()+"\"), "+raw_item_name.upper()+");")
  print(raw_item_name.lower()+" java integration generate!")
  # Json
  f = open(modelsItemPath+raw_item_name.lower()+".json", "w")
  for line in item_template:
    f.write(line.replace("{$item_name}",raw_item_name.lower()))
  f.close()
  print(raw_item_name.lower()+".json generate!")
  # TEXTURE
  img.save(texturesItemPath+"mineral\\"+raw_item_name.lower()+'.png')
  print(raw_item_name.lower() + ".png generate!")

  # Block & Item Block
  javaAttributDef.append("public static EdoraOreBlock "+block_name.upper()+";")
  javaAttributAsign.append(block_name.upper()+" = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).breakByTool(FabricToolTags.PICKAXES, "+str(oreData[4])+").requiresTool());")
  javaAttributAsign.append("Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, \""+block_name.lower()+"\"), "+block_name.upper()+");")
  javaAttributAsign.append("Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, \""+block_name.lower()+"\"), new BlockItem("+block_name.upper()+", new Item.Settings().group(EdoraItems.ORE_GROUP)));")
  print(block_name.lower()+" java integration generate!")
  # Json Block Item
  f = open(modelsItemPath+block_name.lower()+".json", "w")
  for line in blockItem_template:
    f.write(line.replace("{$block_name}",block_name.lower()))
  f.close()
  print(block_name.lower()+".json generate!")
  # TEXTURE
  img.save(texturesItemPath+"mineral\\"+block_name.lower()+'.png')
  print(block_name.lower() + ".png generate!")
  # Json Block
  f = open(modelsBlockPath+block_name.lower()+".json", "w")
  for line in block_template:
    f.write(line.replace("{$block_name}",block_name.lower()))
  f.close()
  print(block_name.lower()+".json generate!")
  # TEXTURE
  img.save(texturesBlockPath+"mineral\\"+block_name.lower()+'.png')
  print(block_name.lower() + ".png generate!")
  # Json Blockstates
  f = open(blockstatesPath+block_name.lower()+".json", "w")
  for line in blockstates_template:
    f.write(line.replace("{$block_name}",block_name.lower()))
  f.close()
  print(block_name.lower()+".json generate!")
  
  # Loottables
  minLoot = oreData[2]
  maxLoot = oreData[3]
  fortune = oreData[1]
  # Si one drop & no fortune
  if (not fortune) and minLoot == maxLoot :
    f = open(loottablesPath+block_name.lower()+".json", "w")
    for line in normal_one_loottables:
      formatLine = line.replace("{$block_name}",block_name.lower())
      formatLine = formatLine.replace("{$drop_item}",raw_item_name.lower())
      f.write(formatLine)
    f.close()
    print(block_name.lower()+".json generate!")
  # Si one drop & fortune
  if fortune and minLoot == maxLoot :
    f = open(loottablesPath+block_name.lower()+".json", "w")
    for line in fortune_one_loottables:
      formatLine = line.replace("{$block_name}",block_name.lower())
      formatLine = formatLine.replace("{$drop_item}",raw_item_name.lower())
      f.write(formatLine)
    f.close()
    print(block_name.lower()+".json generate!")
  # Si multiple drop & fortune
  if fortune and maxLoot > minLoot :
    f = open(loottablesPath+block_name.lower()+".json", "w")
    for line in fortune_loottables:
      formatLine = line.replace("{$block_name}",block_name.lower())
      formatLine = formatLine.replace("{$drop_item}",raw_item_name.lower())
      formatLine = formatLine.replace("{$loot_min}",str(minLoot))
      formatLine = formatLine.replace("{$loot_max}",str(maxLoot))
      f.write(formatLine)
    f.close()
    print(block_name.lower()+".json generate!")
  # Si multiple drop & pas fortune
  if (not fortune) and maxLoot > minLoot :
    f = open(loottablesPath+block_name.lower()+".json", "w")
    for line in normal_loottables:
      formatLine = line.replace("{$block_name}",block_name.lower())
      formatLine = formatLine.replace("{$drop_item}",raw_item_name.lower())
      formatLine = formatLine.replace("{$loot_min}",str(minLoot))
      formatLine = formatLine.replace("{$loot_max}",str(maxLoot))
      f.write(formatLine)
    f.close()
    print(block_name.lower()+".json generate!")

# Write java code on file
print("Write java on file...")
codeJavaFile = open("gen\\java_code.txt","w+")
codeJavaFile.write("\n")
for line in javaAttributDef:
  codeJavaFile.write(line+"\n")
codeJavaFile.write("\n")
for line in javaAttributAsign:
  codeJavaFile.write(line+"\n")
codeJavaFile.close()

print("Job done!")

  
