//define.oldSkin({
//    name: 'wysiwyg.viewer.skins.area.RectangleArea',
//    Class: {
//        Extends:'mobile.core.skins.BaseSkin',
//        _params: [
//
//            {'id':'bg','type':Constants.SkinParamTypes.BG_COLOR, 'defaultTheme':'color_13'},
//
//            //{'id':'color','type':Constants.SkinParamTypes.COLOR, 'mutators':['invert',[],'saturation',[50],'luminous',[10], 'alpha',[50]], 'defaultParam':'color1'},
//
//            {'id':'cm0','type':Constants.SkinParamTypes.COLOR, 'mutators':['saturation',[15]], 'defaultParam':'bg'},
//            {'id':'cm1','type':Constants.SkinParamTypes.COLOR, 'mutators':['saturation',[30]], 'defaultParam':'bg'},
//            {'id':'cm2','type':Constants.SkinParamTypes.COLOR, 'mutators':['saturation',[45]], 'defaultParam':'bg'},
//            {'id':'cm3','type':Constants.SkinParamTypes.COLOR, 'mutators':['saturation',[60]], 'defaultParam':'bg'},
//            {'id':'cm4','type':Constants.SkinParamTypes.COLOR, 'mutators':['saturation',[75]], 'defaultParam':'bg'},
//            {'id':'cm5','type':Constants.SkinParamTypes.COLOR, 'mutators':['saturation',[90]], 'defaultParam':'bg'},
//
//            {'id':'trns', 'type':Constants.SkinParamTypes.TRANSITION,  'defaultValue': 'all 0.3s ease 0s'},
//            {'id':'pos','type':Constants.SkinParamTypes.OTHER, 'defaultValue':'position: absolute; top: 0; bottom: 0; left: 0; right: 0;', 'noshow':true}
//        ],
//        _html:
//
//
//                //'<div skinPart="inlineContent"></div>'+
//
//                '<div skinPart="bg" class="cube">'+
//                    '<div class="face f0"><div skinPart="inlineContent"></div></div>'+
//                    '<div class="face f1"><div skinPart="inlineContent"></div></div>'+
//                    '<div class="face f2"><div skinPart="inlineContent"></div></div>'+
//                    '<div class="face f3"><div skinPart="inlineContent"></div></div>'+
//                    '<div class="face f4"><div skinPart="inlineContent"></div></div>'+
//                    '<div class="face f5"><div skinPart="inlineContent"></div></div>'+
//                '</div>',
//        _css: [
//
//            '%.cube%       { [trns] width:300px; height:300px;  ' +
//                '-webkit-transform-style:preserve-3d; -webkit-transform: rotate3d(-1, -0.5, 0.5, 2.5rad);'+
//                   '-moz-transform-style:preserve-3d;    -moz-transform: rotate3d(-1, -0.5, 0.5, 2.5rad); }',
//
//            '%.cube%:hover { -webkit-transform: rotate3d(-15, -2, 1, 5rad); -moz-transform: rotate3d(-5, -6.5, 4.5, 2.5rad);}',
//
//
//            '%.face% { width:100%; height:100%; position:absolute; outline:1px solid #666; -webkit-backface-visibility:hidden; -moz-backface-visibility:hidden;}',
//
//            '%.f0% { background:[cm0]; -webkit-transform:rotateY(-90deg) translateZ(150px); -moz-transform:rotateY(-90deg) translateZ(150px); }',
//            '%.f1% { background:[cm1]; -webkit-transform:                translateZ(150px); -moz-transform:                translateZ(150px); }',
//            '%.f2% { background:[cm2]; -webkit-transform:rotateY(90deg)  translateZ(150px); -moz-transform:rotateY(90deg)  translateZ(150px); }',
//            '%.f3% { background:[cm3]; -webkit-transform:rotateY(180deg) translateZ(150px); -moz-transform:rotateY(180deg) translateZ(150px); }',
//            '%.f4% { background:[cm4]; -webkit-transform:rotateX(90deg)  translateZ(150px); -moz-transform:rotateX(90deg)  translateZ(150px); }',
//            '%.f5% { background:[cm5]; -webkit-transform:rotateX(-90deg) translateZ(150px); -moz-transform:rotateX(-90deg) translateZ(150px); }',
//
//            ''
//
//        ]
//    }
//});


define.oldSkin({
    name: 'wysiwyg.viewer.skins.area.RectangleArea',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [

            {'id':'bg','type':Constants.SkinParamTypes.BG_COLOR, 'defaultTheme':'color_11'},
            {'id':'pos','type':Constants.SkinParamTypes.OTHER, 'defaultValue':'position: absolute; top: 0; bottom: 0; left: 0; right: 0;', 'noshow':true}
        ],
        _html: '<div skinPart="bg"></div><div skinPart="inlineContent"></div>',
        _css: [

            '%bg% { overflow: hidden; [pos][bg] }',
            '%inlineContent% {[pos]}'
        ]
    }
});

