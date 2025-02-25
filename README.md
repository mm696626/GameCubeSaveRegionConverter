# GameCubeSaveRegionConverter

### GameCube Save Region Converter
* A tool to convert GameCube GCI saves to other regions

### Setup to get this tool working
* Have a GameCube save in gci format
* Open the tool (you'll need the latest JDK installed for that)
    * Link to JDK: https://www.oracle.com/java/technologies/downloads/
* Use Header From Another Save
    * This option will directly write the first 64 bytes of the save (the header) from one save to another
    * Pick two saves (ideally ones from different regions) and press Convert Save
* Replace Region In Header
    * This option will directly replace the region byte in the save (the fourth byte in the file)
* Have fun using your saves on your different region games!

### Note for Japanese Saves
* If you're using a Japanese Memory Card, this tool won't convert the save encoding, so some games will wipe the memory card

### Resources Used
* F-Zero GX Docs for documenting the GCI file header
* Link: https://github.com/JoselleAstrid/fzerogx-docs/blob/master/file_formats/gci.md