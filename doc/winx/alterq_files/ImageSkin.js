/**
 * Created by .
 * User: shex
 * Date: 20/02/11
 * Time: 19:07
 */

define.oldSkin({
    name: 'skins.core.ImageSkin',
    Class: {
        Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'themeDir', 'type':'themeUrl', 'defaultTheme':'THEME_DIRECTORY', 'name': ''}
//            {'id':'img','type':Constants.SkinParamTypes.OTHER, 'defaultValue':'background-size: cover !important; backgound-position: 50% 50% !important;'}

            //{'id':'pos', 'type':Constants.SkinParamTypes.OTHER, 'defaultValue': 'position:absolute; top:0; right:0; bottom:0; left:0; '},


        ],
        _html: '<div skinPart="loadingAnimation"></div><img skinPart="image"/>',
        _css: [
            //'{border:0.1em solid transparent; padding:0;}',
            '{}',

            '[state="loading"] > img {visibility:hidden}',
            '[state="loaded"] > img {visibility:visible}',
            '[state="loading"] > [skinPart="loadingAnimation"]{width:100%; height:100%; display:block; background:transparent url([themeDir]gif_preloader.gif) center no-repeat}',
            '[state="loaded"] > [skinPart="loadingAnimation"]{display:none;}'
        ]
    }
});
