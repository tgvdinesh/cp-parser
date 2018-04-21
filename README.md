# cp-parser [![Build Status](https://travis-ci.org/tgvdinesh/cp-parser.svg?branch=new-template-adaptation)](https://travis-ci.org/tgvdinesh/cp-parser)
DOM Parser for CP


# Setup
1. Download [IntelliJ Plugin](https://github.com/tgvdinesh/cp-parser/releases/tag/1.0)
2. Download [Chrome Plugin](https://github.com/jmerle/chelper-companion/tree/feature/universal)
3. [Manually upload Chrome plugin](https://developer.chrome.com/extensions/getstarted#manifest)
4. [Manually upload Intellij Plugin](https://www.jetbrains.com/help/idea/installing-a-plugin-from-disk.html)


# Road Map
- [x] Bind Json to POJO
- [x] Implement parser for [Code Chef](https://www.codechef.com/)
- [x] ```browserPayLoad.setSender();``` Should be deprecated. During binding of string to json we must run this logic
- [ ] [Continuous integration](https://github.com/marketplace/category/continuous-integration)
- [ ] Add property file from where CSS Selectors should be read from
- [ ] Add a task tracker for parser project like Jira
- [ ] [Publish IntelliJ plugin](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/publishing_plugin.html)
- [ ] Implement parser for multiple sample input/output [Sample 1](https://www.hackerrank.com/challenges/30-conditional-statements/problem)
- [ ] Implement parser for all Competitive Programming websites
- [ ] Show [Progress Indicator](https://github.com/JetBrains/intellij-community/search?utf8=%E2%9C%93&q=ProgressIndicator&type=)
- [ ] Implement quality assurance
- [ ] If possible display build status with [IntelliJ](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
- [ ] Show notification message upon each update in IntelliJ
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
