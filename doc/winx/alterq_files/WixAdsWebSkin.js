define.oldSkin({
    name: 'wysiwyg.viewer.skins.wixadsskins.WixAdsWebSkin',
    Class: { Extends:'mobile.core.skins.BaseSkin',
        _params: [
            {'id':'bg','type':Constants.SkinParamTypes.BG_COLOR, 'defaultValue':'#404040'},
            {'id':'rd1',  'type':Constants.SkinParamTypes.BORDER_RADIUS, 'defaultValue':'0 0 6px 6px', 'noshow':true},
            {'id':'rd2',  'type':Constants.SkinParamTypes.BORDER_RADIUS, 'defaultValue':'6px 6px 0 0', 'noshow':true},
//            {'id':'nord',  'type':Constants.SkinParamTypes.BORDER_RADIUS, 'defaultValue':'0 0 0 0', 'noshow':true},
            //{'id':'txtshd','type':Constants.SkinParamTypes.OTHER,'defaultValue': 'text-shadow: 1px 1px 0px #000000; filter: dropshadow(color=#000000, offx=1, offy=1);'},
            //{'id':'font','type':Constants.SkinParamTypes.OTHER,'defaultValue': 'font: bold 13px/17px Helvetica, Arial, Verdana, sans-serif;'},
            {'id':'tdr', 'type':Constants.SkinParamTypes.URL, 'defaultTheme':'BASE_THEME_DIRECTORY'},
            {'id':'trans','type':Constants.SkinParamTypes.TRANSITION,'defaultValue': 'all 0.3s ease-in-out'},
            {'id':'height','type':Constants.SkinParamTypes.OTHER,'defaultValue': '40px'}
        ],
        _html:
            '<div skinPart="topRect">' +
                '<div skinPart="topRectLabel"></div>'+
                '<div skinPart="topRectContent"></div>'+
            '</div>' +
            '<div skinPart="footerLabel"></div>',
            _css: [
                '{cursor:pointer; height:[height]}',

                //css rules for the top label
                '%topRect% { [bg] [rd1] height:26px; position:fixed; right:50px; top:0px; overflow:hidden; [trans] }',
                '%topRect%:hover { height:97px; background-color:rgba(50,50,50,.8);}',
                '%topRectLabel% { padding: 6px; font-size: 13px; line-height: 1.3em; color: #fff; }',
                '%topRectLabel% .face { font-weight:bold; }',
                '%topRect%:hover %topRectLabel% { background-color:#222 }',

                '%topRect% %topRectLabel% .smallMusa { display:inline-block; text-indent:-9999px; width:16px; height:16px; margin-right: 5px; ' +
                    'background-image: url([tdr]wixads/wf_label_static.png); background-repeat: no-repeat; background-position: 0% 0%; }',
                '%topRect% %topRectLabel% .face .smallMusa { margin-right:8px; }',
                '%topRect% %topRectLabel% .smallLogo { display:inline-block; text-indent:-9999px; width:29px; height:16px; ' +
                    'background-image: url([tdr]wixads/wf_label_static.png); background-repeat: no-repeat; background-position: -16px top; }',

                '%topRectContent% { font-size: 13px; line-height: 1.3em; color: #ffffff; font-weight:bold; line-height:18px; text-align:justify; padding:5px 10px; width: 100%; }',
                '%topRectContent% .spacer{line-height:26px;}',
                '%topRectContent% .emphasis{color:#ffcc00;}',
                '%topRectContent% .cap {font-size:16px; line-height: 1.3em; text-transform:uppercase; }',
                '%topRectContent% .face { display:block; line-height:18px; text-align:justify; padding:0 20px; width:120px; }',

                //css rules for the bottom label
                '%footerLabel% {position:fixed; bottom:0; width:100% }',
                '%footerLabel% .adFootBox {height:[height]; width:100%; text-align:center}',
                '%footerLabel% .siteBanner { [bg] [rd2] width:100%; height:100%; text-align:center }',
                '%footerLabel%:hover .faceBanner, %footerLabel%:hover .siteBanner { background-color:#222 }',
                '%footerLabel% .siteBanner .wrapper { padding: 5px 0;}',
                '%footerLabel% .siteBanner .wrapper div { display:inline-block; height:30px;}',
                '%footerLabel% .bigMusa { text-indent:-9999px; width:36px; background:url([tdr]wixads/wf_label_static.png)left bottom no-repeat; position:relative; top:-19px; }',
                '%footerLabel% .logoDot { text-indent:-9999px; width:39px; height:15px; position:relative; top:-4px; margin: 0 5px; ' +
                 'background:url([tdr]wixads/wf_label_static.png) right -17px no-repeat; }',
                '%footerLabel% .emphasis {color:#ffcc00; font-size:16px; text-transform:uppercase; }',
                '%footerLabel% .txt { color: #fff; font-weight:bold; font-size: 15px; }',
                '%footerLabel% .siteBanner .txt { line-height: 47px; }',
                
                // Facebook -- css rules for the bottom label
                '%footerLabel% .faceBanner { [bg] width:500px; height:100%; margin: 0 auto; [rd2] padding: 5px 0 0 0; }',
                '%footerLabel% .faceBanner div { display:inline-block; height:30px;}',
                '%footerLabel% .faceBanner .txt { color: #fff; font-weight:bold; font-size: 15px; text-align:justify; margin: -2px 10px 0 19px; }',
                //'%footerLabel% .shd { [txtshd] }',
                '%footerLabel% .faceBanner .logoDot { position:static; margin: 0 3px; }',
                '%footerLabel% .faceBanner .emphasis { font-weight:bold; position:relative; top:-6px; }'
            ]
    }
});