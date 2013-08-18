var MobileUtils = {
    MOBILE_MAX_WIDTH:600,

    isMobile: function(){
//        var addUserAgentsExperiment = rendererModel.runningExperiments.AddUserAgents || rendererModel.runningExperiments.adduseragents;
//        var addUserAgentsExperimentRunning = addUserAgentsExperiment && (addUserAgentsExperiment === "New" || addUserAgentsExperiment === "new");

        if (typeof this._isMobile === 'boolean'){
            return this._isMobile;
        }
        var screenWidth = this.getScreenWidth();
        var fixViewPortsExperiment = rendererModel.runningExperiments.FixViewport || rendererModel.runningExperiments.fixviewport;
        var fixViewPortsExperimentRunning = fixViewPortsExperiment && (fixViewPortsExperiment.toLowerCase() === "new");
        if(!fixViewPortsExperiment){
            if(!this.isAppleMobileDevice()){
                screenWidth = screenWidth/window.devicePixelRatio;
            }
        }
        var isMobileScreenSize = screenWidth < this.MOBILE_MAX_WIDTH;

        var isMobileUserAgent = false;
//        if(addUserAgentsExperimentRunning){
//             isMobileUserAgent = this._isMobileUserAgent();
//        }

//        this._isMobile = (isMobileScreenSize || isMobileUserAgent) && this.isTouchScreen();
        this._isMobile = isMobileScreenSize && this.isTouchScreen();
        return this._isMobile;
    },

    _isMobileUserAgent:function(){
        return (/iphone|ipod|android.*mobile|windows.*phone|blackberry.*mobile/i.test(window.navigator.userAgent.toLowerCase()));
    },

    getScreenWidth: function(){
        var smallerSide = Math.min(screen.width, screen.height);
        var fixViewPortsExperiment = rendererModel.runningExperiments.FixViewport || rendererModel.runningExperiments.fixviewport;
        var fixViewPortsExperimentRunning = fixViewPortsExperiment && (fixViewPortsExperiment.toLowerCase() === "new");
        if(fixViewPortsExperiment){
            if(this.isAndroidMobileDevice() && !this.isNewChromeOnAndroid()){
                smallerSide = smallerSide/window.devicePixelRatio;
            }
        }
        return smallerSide;
    },

    isAppleMobileDevice: function(){
        return (/iphone|ipod|ipad|Macintosh/i.test(navigator.userAgent.toLowerCase()));
    },

    isAndroidMobileDevice:function(){
        return (/android/i.test(navigator.userAgent.toLowerCase()));
    },

    isNewChromeOnAndroid:function(){
        if(this.isAndroidMobileDevice()){
            var userAgent = navigator.userAgent.toLowerCase();
            if((/chrome/i.test(userAgent))){
                var parts = userAgent.split('chrome/');

                var fullVersionString = parts[1].split(" ")[0];
                var versionString = fullVersionString.split('.')[0];
                var version = parseInt(versionString);

                if(version >= 27){
                    return true;
                }
            }
        }
        return false;
    },

    isTouchScreen: function(){
        return (('ontouchstart' in window) || window.DocumentTouch && document instanceof DocumentTouch);
    },

    isViewportOpen: function() {
        return !!document.getElementById('wixMobileViewport');
    },

    getInitZoom: function(){
        if(!this._initZoom){
            var screenWidth = Math.min(screen.height, screen.width);
            if(this.isAndroidMobileDevice() && !this.isNewChromeOnAndroid()){
                screenWidth = screenWidth/window.devicePixelRatio;
            }
            this._initZoom = screenWidth /document.body.offsetWidth;
        }
        return this._initZoom;
    },

    getZoom: function(){
        var screenWidth = (Math.abs(window.orientation) === 90) ? Math.max(screen.height, screen.width) : Math.min(screen.height, screen.width);
        if(this.isAndroidMobileDevice() && !this.isNewChromeOnAndroid()){
            screenWidth = screenWidth/window.devicePixelRatio;
        }
        var FixViewPortsExperiment = rendererModel.runningExperiments.FixViewport || rendererModel.runningExperiments.fixviewport;
        var FixViewPortsExperimentRunning = FixViewPortsExperiment && (FixViewPortsExperiment === "New" || FixViewPortsExperiment === "new");
        if(FixViewPortsExperimentRunning){
            return screenWidth / window.innerWidth;
        }else{
            return screenWidth / document.body.offsetWidth;
        }
    }
};

if (!Function.prototype.bind) {
  Function.prototype.bindContextForMobile = function (oThis) {
    var aArgs = Array.prototype.slice.call(arguments, 1),
        fToBind = this,
        fNOP = function () {},
        fBound = function () {
          return fToBind.apply(this instanceof fNOP && oThis
                                 ? this
                                 : oThis,
                               aArgs.concat(Array.prototype.slice.call(arguments)));
        };

    fNOP.prototype = this.prototype;
    fBound.prototype = new fNOP();

    return fBound;
  };
}else{
    Function.prototype.bindContextForMobile = Function.prototype.bind;
}

if (!document.getElementsByClassName) {
  document.getElementsByClassNameForMobile = function(search) {
    var d = document, elements, pattern, i, results = [];
    if (d.querySelectorAll) { // IE8
      return d.querySelectorAll("." + search);
    }
    if (d.evaluate) { // IE6, IE7
      pattern = ".//*[contains(concat(' ', @class, ' '), ' " + search + " ')]";
      elements = d.evaluate(pattern, d, null, 0, null);
      while ((i = elements.iterateNext())) {
        results.push(i);
      }
    } else {
      elements = d.getElementsByTagName("*");
      pattern = new RegExp("(^|\\s)" + search + "(\\s|$)");
      for (i = 0; i < elements.length; i++) {
        if ( pattern.test(elements[i].className) ) {
          results.push(elements[i]);
        }
      }
    }
    return results;
  }
}else{
    document.getElementsByClassNameForMobile = document.getElementsByClassName;
}
