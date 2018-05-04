# mind-sport [![Build Status](https://travis-ci.org/tgvdinesh/mind-sport.svg?branch=master)](https://travis-ci.org/tgvdinesh/mind-sport)
Competitive programming helper.

# Setup
1. Install [Mind Sport - IntelliJ Plugin](https://plugins.jetbrains.com/plugin/10688-mind-sport)
2. Install [Mind Sport - Chrome Plugin](https://chrome.google.com/webstore/detail/mind-sport/epnaneaijfpjdfpbbnllhcflnammlcnh)
3. Clone and open [Java Template](https://github.com/tgvdinesh/java-template)

# Road Map
- [X] Implement parser for multiple sample input/output [Sample 1](https://www.hackerrank.com/challenges/30-conditional-statements/problem)
- [X] Implement parser for all Competitive Programming websites
- [X] [Publish IntelliJ plugin](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/publishing_plugin.html)
- [X] If possible display build status with [IntelliJ](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
- [ ] Add property file
- [ ] Change listening port in plugin
- [ ] Adopt to standards
	- Changelog
		- https://blog.github.com/2018-05-03-introducing-the-github-changelog/
		- https://keepachangelog.com/en/1.0.0/
	- Contributing guidelines
		- https://blog.github.com/2012-09-17-contributing-guidelines/
	- Rally
		- https://www.openstack.org/
- [ ] Marketing
	- https://graphicdesign.stackexchange.com/questions/2489/how-to-design-sleek-magnifying-glass-effects-like-these
	- Add GIFs to explain steps
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
