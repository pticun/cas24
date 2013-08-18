define.oldSkin({
    name: 'skins.core.ImageNewSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
        ],
        _html: '<img skinPart="image"/>',
        _css: [
            '%image% {position: static; box-shadow: #000 0 0 0}; image-rendering: optimizequality'
        ]
    }
});