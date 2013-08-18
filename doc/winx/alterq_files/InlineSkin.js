/**
 * Created by IntelliJ IDEA.
 * User: idoro
 * Date: 11/1/11
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
define.oldSkin({
    name: 'skins.core.InlineSkin',
    imports: [],
    Class: {
        Extends: 'mobile.core.skins.BaseSkin',
        _params: [],
        _html: '',
        _css: [],
        _buildSkin: function() {
            return {'view':this._viewNode, 'inlineContent':this._viewNode};
        }
    }
});