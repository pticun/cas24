define.oldSkin({
    name: 'wysiwyg.viewer.skins.page.BasicPageSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'bg','type':Constants.SkinParamTypes.BG_COLOR, 'defaultTheme':'color_11'},

            {'id':'pos','type':Constants.SkinParamTypes.OTHER, 'defaultValue':'position: absolute; top: 0; bottom: 0; left: 0; right: 0;', 'noshow':true}
        ],
        _html: '<div skinPart="bg"></div><div skinPart="inlineContent"></div>',
        _css: [

            '%bg% { overflow: hidden; [pos][bg] }',
            '%inlineContent% {[pos] bottom:20px;}'
        ]
    }
});

