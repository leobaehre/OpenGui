# OpenGUI

#### A Minecraft plugin that serves as a one-stop-shop for GUIs that can handle basically any sort of GUI, from shops, custom enderchests, GUI-based minigames, and more.

## Quick Installation
1. Download the latest version of the plugin from the [releases page](https://github.com/leobaehre/OpenGui/releases).
2. Put the plugin in your `plugins` folder.
3. Start the server.
4. Create or edit a guis files in the `guis` folder to your liking.
5. Run the command `/opengui reload` to reload the plugin. Or restart the server.
6. Run the command `/open <id>` to open a GUI.
7. Enjoy!

## Commands
* `/open <id>` - Opens a GUI
  * `id` - Put here the ID of the GUI you want to open
* `opengui <reload>` - Reloads the plugin
  * `reload` - Reload the plugin
* `opengui <getvariable> <name>` - Gets a variable
  * `getvariable` - Get a variable
  * `name` - The name of the variable to get

## Configuration

This plugin is build to be configurable. Almost every string that can be seen by a player has support for color codes. For the list of color codes, please see [this page](https://www.digminecraft.com/lists/color_list_pc.php). § is replaced with &. so its easier to type. For example, red would be &c.

### Setting up a GUI
To set up a GUI, you need to create a new file in the `guis` folder. The file should be in YAML format. The following is an example of a GUI file:
```yaml
id: 'example' # The ID of the GUI
title: 'Some Title' # The title of the GUI
size: 27 # The size of the GUI (must be a multiple of 9 e.g. 9 or 27)
items: # The items in the GUI
  item1: # This doesn't matter, it's just a name
    slot: 0 # The slot of the item
    material: DIAMOND_SWORD # The material of the item
    display-name: '&cSpecial Sword' # The display name of the item
    lore: # The lore of the item
      - '&fLine 1' 
      - '&fLine 2'
    actions: # The actions of the item. The different types of actions are later explained
      - type: close
      - type: command
        command: say Hello World!
```

### Data files
Data files are there to store data of the plugin. This data can be set manually in the data files but it is recommended to use the commands to set the data. The following are the data files:

### File: variables.yml
This file stores the variables that can be used in GUIs. For more information on variables, please see the variables section.

## Items
Items work are a bit more restrictive than the normal spigot items you may be used to. There are currently a limited properties than can be set (more will be added in the future). The following are the properties of an item:
* `Slot`: The slot of the item
* `Material`: The material of the item
* `Display Name`: The display name of the item
* `Lore`: The lore of the item each item in the list is a new line
* `Actions`: The actions of the items

## Actions
Actions are what happens when a player clicks on an item in a GUI. The following are the different types of actions:

### Close
This action closes the GUI after clicked on the item.   

#### Data
This action has no data.

#### Example
```yaml
actions:
  - type: close
```
In this example, the GUI will be closed when the item is clicked.

### Open
This action opens another GUI after clicked on the item.

#### Data
* `ID`: The ID of the GUI to open

#### Example
```yaml
actions:
  - type: open
    id: example
```
In this example, the GUI with the ID `example` will be opened when the item is clicked.

### Command
This action runs a command as the server console after clicked on the item.

#### Data
* `Command`: The command to run

#### Example
```yaml
actions:
  - type: command
    command: say Hello World!
```
In this example, the command `say Hello World!` will be run when the item is clicked.

### Conversation
This action starts a conversation after clicked on the item. If the player is in a conversation then the player can chat a message to input. This input can then be used in a variable.

#### Data
* `Question`: The question to ask the player

#### Example
```yaml
actions:
  - type: close # Optional please see the explanation for the example below
  - type: conversation
    question: "Do you like sushi?"
    variable-name: "answer"
    answers:
      - answer: "Yes"
        actions:
          - type: command
            command: give %player% minecraft:cod 1
      - answer: "No"
        actions:
          - type: command
            command: "say Well that sucks :("
    other-answer:
    - type: command
      command: "say That's not a valid answer!"
```
In this example, the player will be asked the question `Do you like sushi?`. If the player answers `Yes` then the player will be given a cod. If the player answers `No` then the player will be told `Well that sucks :(`. If the player answers anything else then the player will be told `This is not a valid answer!`. I recommend putting the action `close` GUI is closed when the conversation starts. The variable `%player%` is explained below in the variables section.

## Variables
Variables are used to replace text with other text. A variable is surrounded by `%` signs. For example, `%player%` will be replaced with the player's name. There are a number of that are supported by default. These variables are not saved in the datafile.

### Default Variables
* `player`: The player's name

### Custom Variables

#### Variable types
| Type    | Example        |
|---------|----------------|
| String  | "Hello World!" |
| Integer | 34             |
| Double  | 12.53          |
| Boolean | true           |

#### Creating a custom variable

Custom variables can be created by using the `variable-name` property in the `conversation` action. For example, if you have the following action:
```yaml
actions:
  - type: conversation
    question: "What is your name?"
    variable-name: "name"
```
Then you can use the variable `%name%` in any action in the GUI. For example:

```yaml
actions:
  - type: command
    command: "say Hello %name%!"
```

## Credits
* Main Developer: [Leo Bähre](https://github.com/leobaehre)

## Source
https://docs.google.com/document/d/1nQJ-Dr6mMGQUVWeMqmsWmRDZJ7XLZp5VbmtrZCS0kR0