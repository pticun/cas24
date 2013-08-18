define.oldSkin({
    name: 'wysiwyg.viewer.skins.button.ShinyButtonISkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'brw',  'type':Constants.SkinParamTypes.SIZE, 'defaultValue': '0'},
            {'id':'brd',  'type':Constants.SkinParamTypes.COLOR_ALPHA, 'defaultTheme':'color_15'},
            {'id':'brdh', 'type':Constants.SkinParamTypes.COLOR_ALPHA, 'defaultTheme':'color_15'},
            {'id':'bg',   'type':Constants.SkinParamTypes.COLOR_ALPHA, 'defaultTheme':'color_17'},
            {'id':'bgh',  'type':Constants.SkinParamTypes.COLOR_ALPHA, 'defaultTheme':'color_18'},
            {'id':'txth', 'type':Constants.SkinParamTypes.COLOR, 'defaultTheme':'color_15'},
            {'id':'txt',  'type':Constants.SkinParamTypes.COLOR, 'defaultTheme':'color_15'},

            {'id':'rd',  'type':Constants.SkinParamTypes.BORDER_RADIUS, 'defaultValue':'5px'},
            {'id':'fnt', 'type':Constants.SkinParamTypes.FONT,'defaultTheme':'font_5'},

            {'id':'shd','type':Constants.SkinParamTypes.BOX_SHADOW, 'defaultValue':'0 1px 4px rgba(0, 0, 0, 0.6);'},
            {'id':'trans1','type':Constants.SkinParamTypes.TRANSITION,'defaultValue': 'border-color 0.4s ease 0s ,background-color 0.4s ease 0s'},
            {'id':'trans2','type':Constants.SkinParamTypes.TRANSITION,'defaultValue': 'color 0.4s ease 0s'},
            {'id':'tdr', 'type':Constants.SkinParamTypes.URL, 'defaultTheme':'BASE_THEME_DIRECTORY'},
            {'id':'pos', 'type':Constants.SkinParamTypes.OTHER, 'defaultValue':'position: absolute; top:0; bottom:0; left:0; right:0;'}
        ],
        _html:
             '<a skinPart="link"><span skinPart="label"></span></a>',
        _css: [

            '%link%  {[pos][rd][trans1] [shd] border:solid [brd] [brw]; cursor:pointer !important; background:[bg] url([tdr]shiny1button_bg.png) 50% 50% repeat-x;  }',
            '%label% { [fnt][trans2] color:[txt]; white-space:nowrap; margin-top:-[brw]; display:inline-block; position:relative;}',
            //hover
            ':hover %link% { border-color:[brdh]; background-color:[bgh]; [trans1] }',
            ':hover %label% { color: [txth]; [trans2] }'
        ]
    }
});