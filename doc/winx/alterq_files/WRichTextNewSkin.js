define.skin('wysiwyg.viewer.skins.WRichTextNewSkin', function(skinDefinition){
    /**@type core.managers.skin.SkinDefinition */
    var def = skinDefinition;

    def.inherits('mobile.core.skins.BaseSkin');

    //there are here for the layout, so that when one of the styles change the component will update it's height.
    //can be solved nicely..
    def.skinParams([
        {'id': 'f0' , 'type':'cssFont', 'defaultTheme': 'font_0' , 'name': '', 'usedInLogic': true},
        {'id': 'f1' , 'type':'cssFont', 'defaultTheme': 'font_1' , 'name': '', 'usedInLogic': true},
        {'id': 'f2' , 'type':'cssFont', 'defaultTheme': 'font_2' , 'name': '', 'usedInLogic': true},
        {'id': 'f3' , 'type':'cssFont', 'defaultTheme': 'font_3' , 'name': '', 'usedInLogic': true},
        {'id': 'f4' , 'type':'cssFont', 'defaultTheme': 'font_4' , 'name': '', 'usedInLogic': true},
        {'id': 'f5' , 'type':'cssFont', 'defaultTheme': 'font_5' , 'name': '', 'usedInLogic': true},
        {'id': 'f6' , 'type':'cssFont', 'defaultTheme': 'font_6' , 'name': '', 'usedInLogic': true},
        {'id': 'f7' , 'type':'cssFont', 'defaultTheme': 'font_7' , 'name': '', 'usedInLogic': true},
        {'id': 'f8' , 'type':'cssFont', 'defaultTheme': 'font_8' , 'name': '', 'usedInLogic': true},
        {'id': 'f9' , 'type':'cssFont', 'defaultTheme': 'font_9' , 'name': '', 'usedInLogic': true},
        {'id': 'f10', 'type':'cssFont', 'defaultTheme': 'font_10', 'name': '', 'usedInLogic': true}
    ]);

    def.html('');

    def.css([' {word-wrap: break-word;}',
        //it could be nice to have a different css for IE..
//        ' ul { list-style-type: disc; -webkit-padding-start: 1.3em; -moz-padding-start: 1.3em; -webkit-margin-start: 1em; -moz-margin-start: 1em; line-height: normal; letter-spacing: normal;}',
//        ' ol {  list-style-type: decimal; -webkit-padding-start: 1.3em; -moz-padding-start: 1.3em; -webkit-margin-start: 1em; -moz-margin-start: 1em; line-height: normal; letter-spacing: normal;}',
        ' ul { list-style-type: disc; padding-left: 1.3em; margin-left: 0.5em; line-height: normal; letter-spacing: normal;}',
        ' ol {  list-style-type: decimal;padding-left: 1.3em; margin-left: 0.5em; line-height: normal; letter-spacing: normal;}',
        ' ul[dir="rtl"] {padding-right: 1.3em; margin-right: 0.5em;}',
        ' ol[dir="rtl"] {padding-right: 1.3em; margin-right: 0.5em;}',
        ' ul ul {list-style-type: circle;}',
        ' ol ul {list-style-type: circle;}',
        ' ol ol ul {list-style-type: square;}',
        ' ol ul ul {list-style-type: square;}',
        ' ul ol ul {list-style-type: square;}',
        ' ul ul ul {list-style-type: square;}',
        ' li{ color: inherit; font-size: inherit; font-family: inherit; font-style: inherit; font-weight: inherit; line-height: normal; letter-spacing: normal;}',
//        ' p {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
//        ' h1 {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
//        ' h2 {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
//        ' h3 {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
//        ' h4 {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
//        ' h5 {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
//        ' h6 {font-weight: normal; margin: 0; text-decoration: none; line-height: normal; letter-spacing: normal;}',
        ' p {margin: 0; line-height: normal; letter-spacing: normal;}',
        ' h1 {margin: 0;  line-height: normal; letter-spacing: normal;}',
        ' h2 {margin: 0; line-height: normal; letter-spacing: normal;}',
        ' h3 {margin: 0;  line-height: normal; letter-spacing: normal;}',
        ' h4 {margin: 0; line-height: normal; letter-spacing: normal;}',
        ' h5 {margin: 0;  line-height: normal; letter-spacing: normal;}',
        ' h6 {margin: 0;  line-height: normal; letter-spacing: normal;}',
        ' a { color: inherit}'
    ]);
});