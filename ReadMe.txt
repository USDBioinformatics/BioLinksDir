suppose:
I have a input json file: C:\\Download\\a.json
I want the output to be in the folder: D:\\myoutput
So, in command line we can type:
java -jar BioLinksDir.jar -o C:\\Download\\a.json  D:\\myoutput
for more information:
java -jar BioLinksDir.jar -help

The Bioinformatics Links Directory repository (http://bioinformatics.ca/links_directory/) divides their tools into 11 categories. Select each category and download the JSON file (‘list as JSON’). Run BioLinksDir with each JSON file. Keep the output folder consistent between runs to unsure that all files end up in one directory. 
