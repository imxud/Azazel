# Azazel
A better player tab list library for Minecraft 1.7-1.8+

#### Author's Note
A lot of you have been giving me shit for Kraken, which granted, it's crap. That being said, it was the first publicly available tab API and I made it in an afternoon.
I made this one awhile ago too, and used it on VeltPvP. There's definently some work that can be done on it, but the implementation is clean and you won't run into any performance issues running this bad boy.
This should be good enough to keep you guys from buying shitty APIs from shitty developers. You're always welcome to clean up some shit and make a pull request, I'll actually be maintaining this project since I noticed my GitHub is starting to look a little bit dry.
Also, if you're having trouble setting this up, please don't spam me on Twitter or Telegram. It's extremely simple and if you can't do it without help you probably shouldn't be making plugins in the first place.
P.S: Thanks to Rhian for the new GitHub username.

### Installation

#### Option 1: Maven repository 
    *Coming soon*
#### Option 2: JAR
  1. Download the [latest release](https://github.com/bizarre/azazel/releases).
  2. Add the JAR to your project.
    + For Eclipse users, see [here](http://stackoverflow.com/questions/11033603/how-to-create-a-jar-with-external-libraries-included-in-eclipse).
    + For IntelliJ users, see [here](http://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project).

Instantiate Azazel in your onEnable:

  ```java
public void onEnable() {
      //All your other stuff
      new Azazel(this, tabadapter);
}
  
  ```
  
### Example Usage

see [ExampleTabAdapter.java](ExampleTabAdapter.java)

#### Result
![result](https://i.gyazo.com/0d4d4ae6fb58a00f57cee614d8600727.png)
