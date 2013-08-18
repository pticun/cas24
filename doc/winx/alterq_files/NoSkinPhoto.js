//If this skin is updated, DefaultPhoto.js need to be updated as well
//Gush 12/6/12
define.oldSkin({
    name: 'wysiwyg.viewer.skins.photo.NoSkinPhoto',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [

            {'id':'contentPaddingLeft','type':Constants.SkinParamTypes.SIZE, 'defaultValue':'0px', 'noshow': true, 'usedInLogic': true},
            {'id':'contentPaddingRight','type':Constants.SkinParamTypes.SIZE, 'defaultValue':'0px', 'noshow': true, 'usedInLogic': true},
            {'id':'contentPaddingBottom','type':Constants.SkinParamTypes.SIZE, 'defaultValue':'0px', 'noshow': true, 'usedInLogic': true},
            {'id':'contentPaddingTop','type':Constants.SkinParamTypes.SIZE, 'defaultValue':'0px', 'noshow': true, 'usedInLogic': true}
        ],
        _html:
            '<a skinPart="link">' +
                '<div skinPart="img" skin="mobile.core.skins.ImageNewSkin"></div>'+
                '</a>',
        _css: [

            '%link% { display:block; overflow:hidden;}'
        ]
    }
});
