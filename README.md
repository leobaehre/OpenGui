# OpenGUI

## Description
A Minecraft plugin that serves as a one-stop-shop for GUIs that can handle basically any sort of GUI, from shops, custom enderchests, GUI-based minigames, and more.

## Commands
* `/open <id>` - Opens a GUI
  * `id` - Put here the ID of the GUI you want to open
* `opengui <reload>` - Reloads the plugin
  * `reload` - Reload the plugin

## Configuration

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

### Command

This action runs a command after clicked on the item.

#### Data
* `Command`: The command to run

#### Example
```yaml
actions:
  - type: command
    command: say Hello World!
```
This example will run the command `/say Hello World!` when the item is clicked. The dispatcher of the command is the server console.


## Source
https://docs.google.com/document/d/1nQJ-Dr6mMGQUVWeMqmsWmRDZJ7XLZp5VbmtrZCS0kR0