define.oldSkin({
    name: 'wysiwyg.viewer.skins.LinkBarNoBGSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        compParts:{
            imageItem: {skin:'wysiwyg.viewer.skins.displayers.LinkBarItemNoBGSkin', styleGroup:'displayer' }
        },
        _params: [

        ],
        _html:  '<div skinPart="itemsContainer"></div>',
        _css: [
                "%itemsContainer% { position:absolute; width:100%; height:100%}"
              ]
    }
});