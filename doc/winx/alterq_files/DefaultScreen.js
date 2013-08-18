define.oldSkin({
    name: 'wysiwyg.viewer.skins.screenwidthcontainer.DefaultScreen',
     Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'bg','type':Constants.SkinParamTypes.BG_COLOR, 'defaultTheme':'color_11'},
            {'id':'bgctr','type':Constants.SkinParamTypes.BG_COLOR, 'defaultTheme':'color_11'},
            {'id':'brd',  'type':Constants.SkinParamTypes.COLOR_ALPHA, 'defaultTheme':'color_15'},

            {'id':'brwt', 'type':Constants.SkinParamTypes.SIZE, 'defaultValue': '0px'},
            {'id':'brwb', 'type':Constants.SkinParamTypes.SIZE, 'defaultValue': '0px'},

            {'id':'rd', 'type':Constants.SkinParamTypes.BORDER_RADIUS, 'defaultValue': '0'},
            {'id':'shd','type':Constants.SkinParamTypes.BOX_SHADOW, 'defaultValue':'0 0 5px rgba(0, 0, 0, 0.7)'} ,
            {'id':'pos', 'type':Constants.SkinParamTypes.OTHER, 'defaultValue': 'position:absolute; left:0px; right:0px; top:0px; bottom:0px;'}
        ],
        _html:
                '<div skinPart="screenWidthBackground">' +
                    '<div class="bg"></div>' +
                '</div>'+
                '<div skinPart="centeredContent">' +
                    '<div skinPart="bg"></div>' +
                    '<div skinPart="inlineContent"></div>' +
                '</div>',
        _css: [
             '%screenWidthBackground% {[pos] }',
             '%.bg% {[pos][bg][shd] border-top:[brwt] solid [brd]; border-bottom:[brwb] solid [brd]; }',
             '%bg%             { position:absolute; left:0px; right:0px; top:[brwt]; bottom:[brwb];[bgctr][rd]}',
             '%inlineContent%  { [pos]}',
             '%centeredContent%{ [pos] }'
        ]
    }
});