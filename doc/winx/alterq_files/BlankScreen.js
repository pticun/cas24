define.oldSkin({
    name: 'wysiwyg.viewer.skins.screenwidthcontainer.BlankScreen',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [

            {'id': 'pos', 'type':Constants.SkinParamTypes.OTHER, 'defaultValue': 'position:absolute; left:0px; right:0px; top:0px; bottom:0px;'}
        ],
        _html: '<div skinPart="screenWidthBackground"></div>'+
               '<div skinPart="centeredContent">' +
                   '<div skinPart="bg"></div>' +
                   '<div skinPart="inlineContent"></div>'+
               '</div>',
        _css: [
             '%screenWidthBackground% {[pos]}',
             '%bg%                    {[pos]}',
             '%inlineContent%         {[pos]}',
             '%centeredContent%       {[pos]}'
        ]
    }
});