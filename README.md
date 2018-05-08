# mind-sport-intellij-plugin [![Build Status](https://travis-ci.org/tgvdinesh/mind-sport-intellij-plugin.svg?branch=master)](https://travis-ci.org/tgvdinesh/mind-sport-intellij-plugin)
Competitive programming helper. Fills test cases in your java project with project name as `java-template`

# Setup:
1. Install [Mind Sport - IntelliJ Plugin](https://plugins.jetbrains.com/plugin/10688-mind-sport)
2. Install Competitive Companion [Chrome Extension](https://chrome.google.com/webstore/detail/competitive-companion/cjnmckjndlpiamhfimnnjmnckgghkjbl) / [Firefox Add-on](https://addons.mozilla.org/en-US/firefox/addon/competitive-companion/)
3. Clone and open [Java Template](https://github.com/tgvdinesh/java-template)

_*NOTE:* This step will be removed in [future release](https://github.com/tgvdinesh/mind-sport-intellij-plugin/issues/2)_

# How to use:
1. Keep [Java Template](https://github.com/tgvdinesh/java-template) project open / It should be at least in recent history in IntelliJ
2. Go to [Supported Competitive Programming websites's](https://github.com/jmerle/competitive-companion#supported-websites) problem statement
3. Click on Competitive Companion plugin on right top corner of your browser.

That's it. You can start coding and click on run with *AppTest.runTestCases* as your run configuration.
What happens in the background is, your `io.json` file will be updated with the test cases.

# Interdependency Reference:
1. [IntelliJ Plugin](https://github.com/tgvdinesh/mind-sport-intellij-plugin)
2. [Java project template](https://github.com/tgvdinesh/java-template) used by IntelliJ Plugin
3. Competitive Companion [Chrome Extension](https://chrome.google.com/webstore/detail/competitive-companion/cjnmckjndlpiamhfimnnjmnckgghkjbl) / [Firefox Add-on](https://addons.mozilla.org/en-US/firefox/addon/competitive-companion/) 
 
# Reference
1. [IntelliJ Platform SDK DevGuide](http://www.jetbrains.org/intellij/sdk/docs/welcome.html)
2. [Chrome plugin](https://github.com/jmerle/chelper-companion/tree/feature/universal)
3. [IntelliJ Plugin](http://plugins.jetbrains.com/plugin/10652-competitive-program-parser)
4. [Gitter IntelliJ Plugin Developers](https://gitter.im/IntelliJ-Plugin-Developers/Lobby)
4. [How to use IntelliJ](https://www.jetbrains.com/idea/documentation/)
5. [IntelliJ Community Find](https://github.com/JetBrains/intellij-community/find/master)
