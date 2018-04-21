# cp-parser [![Build Status](https://travis-ci.org/tgvdinesh/cp-parser.svg?branch=master)](https://travis-ci.org/tgvdinesh/cp-parser)
DOM Parser for CP


# Setup
1. Download [IntelliJ Plugin](http://plugins.jetbrains.com/plugin/10652-competitive-program-parser)
2. Download [Chrome Plugin](https://github.com/jmerle/chelper-companion/tree/feature/universal)
3. [Manually upload Chrome plugin](https://developer.chrome.com/extensions/getstarted#manifest)
4. [Manually upload Intellij Plugin](https://www.jetbrains.com/help/idea/installing-a-plugin-from-disk.html)


# Road Map
- [X] Implement parser for multiple sample input/output [Sample 1](https://www.hackerrank.com/challenges/30-conditional-statements/problem)
- [X] Implement parser for all Competitive Programming websites
- [X] [Publish IntelliJ plugin](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/publishing_plugin.html)
- [X] If possible display build status with [IntelliJ](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
- [ ] Add property file
- [ ] Add a task tracker for parser project like Jira
- [ ] Implement quality assurance
- [ ] Show [Progress Indicator](https://github.com/JetBrains/intellij-community/search?utf8=%E2%9C%93&q=ProgressIndicator&type=)
- [ ] RECENT CHANGE NOTES - template integration
    ```
    .ignore plugin updated to v2.6.0 
     If you find my plugin helpful, [Donate with PayPal](https://www.paypal.me/tgvdinesh)
     
     Implemented enhancements:
     - Multirow tabs for outer rules panel (#501) Fixes:
     - The following plugins are incompatible with the current IDE build (#530)
     - NullPointerException on File indexing (IgnoreFilesIndex), infinite loop of failing re-indexes
     - thanks to @nicity ! (#527) Closed:
     - Have you considered Open Collective? opencollective.com/ignore (#497)  
    
    
     If you find my plugin helpful, donate me using [Donate with PayPal](https://www.paypal.me/tgvdinesh)
     ```
- [ ] ```patchPluginXml > changeNotes``` in *build.gradle* should be loaded from template 
# Reference
1. [IntelliJ Platform SDK DevGuide](http://www.jetbrains.org/intellij/sdk/docs/welcome.html)
2. [Chrome plugin](https://github.com/jmerle/chelper-companion/tree/feature/universal)
3. [IntelliJ Plugin](http://plugins.jetbrains.com/plugin/10652-competitive-program-parser)