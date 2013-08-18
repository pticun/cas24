define.oldSkin({
    name: 'wysiwyg.viewer.skins.button.TextOnlyButtonSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [

            {'id':'txth', 'type':Constants.SkinParamTypes.COLOR, 'defaultTheme':'color_15'},
            {'id':'txt',  'type':Constants.SkinParamTypes.COLOR, 'defaultTheme':'color_15'},

            {'id':'fnt', 'type':Constants.SkinParamTypes.FONT,'defaultTheme':'font_5'},

            {'id':'trans','type':Constants.SkinParamTypes.TRANSITION,'defaultValue': 'color 0.4s ease 0s'},
            {'id':'pos', 'type':Constants.SkinParamTypes.OTHER, 'defaultValue':'position: absolute; top: 0px; bottom: 0px; left: 0px; right: 0px;'}
        ],
        _html:
            '<a skinPart="link"><span skinPart="label"></span></a>',
        _css: [
            '%link%    { [pos] }',
            '%label%   { cursor:pointer; [fnt] color:[txt]; white-space:nowrap; [trans] }' ,
            ':hover %label%   { color:[txth]; [trans] }'
        ]
    }
});