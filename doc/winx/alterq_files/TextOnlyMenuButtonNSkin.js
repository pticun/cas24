define.oldSkin({
    name: 'wysiwyg.viewer.skins.dropmenubutton.TextOnlyMenuButtonNSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'fnt','type':Constants.SkinParamTypes.FONT,'defaultTheme':'font_1'},
            {'id':'txt',  'type':Constants.SkinParamTypes.COLOR,'defaultTheme':'color_15'},
            {'id':'txth',  'type':Constants.SkinParamTypes.COLOR,'defaultTheme':'color_14'},
            {'id':'txts',  'type':Constants.SkinParamTypes.COLOR,'defaultTheme':'color_14'},
            {'id':'pad', 'type':Constants.SkinParamTypes.SIZE, 'defaultValue': '5px'},

            {'id':'trans','type':Constants.SkinParamTypes.TRANSITION,'defaultValue': 'color 0.4s ease 0s'}
        ],
        _html: '<div class="gapper"><div skinPart="bg"><p skinPart="label"></p></div></div>',
        _css: [
            '{ display:inline-block; cursor:pointer; color:[txt]; [fnt] [trans] }',
            '%.gapper% { padding: 0 [pad]; }',
            '%label% { display:inline-block; padding:0 10px; }',

            // ===> the buttons on the drop down menu  <====
            '[container=drop] { width:100%; display:block; }',
            '[container=drop] %label% { padding:0 0.5em; }',

            // hover
            '[state=over]  { color:[txth]; [trans] }',
            // selected
            '[state=selected] { color:[txts]; [trans] }'
        ]
    }
});