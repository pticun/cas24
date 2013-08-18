define.oldSkin({
    name: 'wysiwyg.viewer.skins.WRichTextSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _tags: [],
        _params: [
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
        ],
        _html: '<div skinPart="richTextContainer" class="richTextContainer"></div>',
        _css: [

            '%richTextContainer% {' +
                'position:relative; ' +
                'width:100%; height:100%; ' +
                'word-wrap: break-word;' +
            '}'
        ]
    }
});
