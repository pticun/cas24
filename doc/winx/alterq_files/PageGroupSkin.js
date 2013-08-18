define.oldSkin({
    name: 'wysiwyg.viewer.skins.PageGroupSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'tdr','type':Constants.SkinParamTypes.URL, 'defaultTheme':'BASE_THEME_DIRECTORY'},
            {'id':'pos','type':Constants.SkinParamTypes.OTHER, 'defaultValue':'position:absolute; top:0; bottom:0; left:0; right:0;'},
            {'id': '$overlayColor', 'type':'cssBgColor', 'defaultValue': '#000000AA', 'name': ''}
        ],
        _html: '<div skinPart="inlineContent"></div>' +
               '<div skinPart="overlay"></div>'+
               '<div skinPart="grid">' +
                   '<div skinPart="gridHeader" class="horz"></div>' +
                   '<div skinPart="gridBodyRight" class="vert"></div>' +
                   '<div skinPart="gridBodyLeft" class="vert"></div>' +
                   '<div skinPart="gridFooter" class="horz"></div>' +
               '</div>',
        _css: [
                '{height:100px; width:100px}',
                '%overlay% {[pos] [$overlayColor]}',
                '%inlineContent% {[pos]}',

                '%grid% {visibility:hidden}',
                '[state="showGridLines"] %grid% {visibility:visible}',

                '[state="showGridLines"] %.horz% {position:absolute; height:1px; width:100%; background:url([tdr]editor_helpers/grid_h.png) repeat-x left;}',
                '[state="showGridLines"] %.vert% {position:absolute; height:100%; width:1px; background:url([tdr]editor_helpers/grid_v.png) repeat-y left;}',

                '[state="showGridLines"] %gridHeader% {top:0; left:0;}',
                '[state="showGridLines"] %gridFooter% {bottom:0; left:0;}',

                '[state="showGridLines"] %gridBodyRight% {top:0; right:0;}',
                '[state="showGridLines"] %gridBodyLeft% {top:0; left:0;}'

        ]
    }
});
