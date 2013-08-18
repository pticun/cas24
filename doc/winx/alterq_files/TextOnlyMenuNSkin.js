define.oldSkin({
    name: 'wysiwyg.viewer.skins.dropmenu.TextOnlyMenuNSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        compParts: {
            'repeaterButton':{ skin:'wysiwyg.viewer.skins.dropmenubutton.TextOnlyMenuButtonNSkin', styleGroup:'inherit' },
            'moreButton':{ skin:'wysiwyg.viewer.skins.dropmenubutton.TextOnlyMenuButtonNSkin', styleGroup:'inherit' }
        },
        _params: [
            {'id':'rd',  'type':Constants.SkinParamTypes.BORDER_RADIUS, 'defaultValue':'0px'},
            {'id':'bgDrop', 'type':Constants.SkinParamTypes.BG_COLOR, 'defaultTheme':'color_11'},

            {'id':'gapFromMenu', 'type':Constants.SkinParamTypes.OTHER, 'defaultValue':'7px'},
            {'id':'shd','type':Constants.SkinParamTypes.BOX_SHADOW, 'defaultValue':'0 1px 4px rgba(0, 0, 0, 0.6);'}
        ],
        _html:
            '<div skinPart="itemsContainer"></div><div skinPart="moreButton"></div><div skinPart="dropWrapper"><div skinPart="moreContainer"></div></div>',
        _css: [
            '%itemsContainer% { position:relative; overflow:hidden;}',

            '%dropWrapper% { visibility:hidden; position:absolute; margin-top:[gapFromMenu]; }',
            '%dropWrapper%[dropMode=dropUp] { margin-top:0; margin-bottom:[gapFromMenu]; }',
            '%moreContainer%{ [bgDrop] [rd] [shd] }'
        ]
    }
});