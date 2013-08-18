define.oldSkin({
    name: 'wysiwyg.viewer.skins.displayers.LinkBarItemNoBGSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        compParts:{
            image: {skin:'mobile.core.skins.ImageSkin' }
        },
        _params: [],
       _html:
            '<a skinPart="link">'+
                '<div skinPart="image"></div>' +
            '</a>',
        _css: [
            "%link% { cursor: pointer; }",
            "%image% {}"
        ]
    }
});