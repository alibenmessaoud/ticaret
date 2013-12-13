!function(){"use strict";var e,t=function(e,t){var o=e.style[t];if(e.currentStyle?o=e.currentStyle[t]:window.getComputedStyle&&(o=document.defaultView.getComputedStyle(e,null).getPropertyValue(t)),"auto"==o&&"cursor"==t)for(var r=["a"],i=0;i<r.length;i++)if(e.tagName.toLowerCase()==r[i])return"pointer";return o},o=function(e){if(c.prototype._singleton){e||(e=window.event);var t;this!==window?t=this:e.target?t=e.target:e.srcElement&&(t=e.srcElement),c.prototype._singleton.setCurrent(t)}},r=function(e,t,o){e.addEventListener?e.addEventListener(t,o,!1):e.attachEvent&&e.attachEvent("on"+t,o)},i=function(e,t,o){e.removeEventListener?e.removeEventListener(t,o,!1):e.detachEvent&&e.detachEvent("on"+t,o)},s=function(e,t){if(e.addClass)return e.addClass(t),e;if(t&&"string"==typeof t){var o=(t||"").split(/\s+/);if(1===e.nodeType)if(e.className){for(var r=" "+e.className+" ",i=e.className,s=0,a=o.length;a>s;s++)r.indexOf(" "+o[s]+" ")<0&&(i+=" "+o[s]);e.className=i.replace(/^\s+|\s+$/g,"")}else e.className=t}return e},a=function(e,t){if(e.removeClass)return e.removeClass(t),e;if(t&&"string"==typeof t||void 0===t){var o=(t||"").split(/\s+/);if(1===e.nodeType&&e.className)if(t){for(var r=(" "+e.className+" ").replace(/[\n\t]/g," "),i=0,s=o.length;s>i;i++)r=r.replace(" "+o[i]+" "," ");e.className=r.replace(/^\s+|\s+$/g,"")}else e.className=""}return e},n=function(e){var o={left:0,top:0,width:e.width||e.offsetWidth||0,height:e.height||e.offsetHeight||0,zIndex:9999},r=t(e,"zIndex");for(r&&"auto"!=r&&(o.zIndex=parseInt(r,10));e;){var i=parseInt(t(e,"borderLeftWidth"),10),s=parseInt(t(e,"borderTopWidth"),10);o.left+=isNaN(e.offsetLeft)?0:e.offsetLeft,o.left+=isNaN(i)?0:i,o.top+=isNaN(e.offsetTop)?0:e.offsetTop,o.top+=isNaN(s)?0:s,e=e.offsetParent}return o},l=function(e){return(e.indexOf("?")>=0?"&":"?")+"nocache="+(new Date).getTime()},p=function(e){var t=[];return e.trustedDomains&&("string"==typeof e.trustedDomains?t.push("trustedDomain="+e.trustedDomains):t.push("trustedDomain="+e.trustedDomains.join(","))),t.join("&")},d=function(e,t){if(t.indexOf)return t.indexOf(e);for(var o=0,r=t.length;r>o;o++)if(t[o]===e)return o;return-1},h=function(e){if("string"==typeof e)throw new TypeError("ZeroClipboard doesn't accept query strings.");return e.length?e:[e]},c=function(e,t){if(e&&(c.prototype._singleton||this).glue(e),c.prototype._singleton)return c.prototype._singleton;c.prototype._singleton=this,this.options={};for(var o in u)this.options[o]=u[o];for(var r in t)this.options[r]=t[r];this.handlers={},c.detectFlashSupport()&&m()},f=[];c.prototype.setCurrent=function(o){e=o,this.reposition(),o.getAttribute("title")&&this.setTitle(o.getAttribute("title")),this.setHandCursor("pointer"==t(o,"cursor"))},c.prototype.setText=function(e){e&&""!==e&&(this.options.text=e,this.ready()&&this.flashBridge.setText(e))},c.prototype.setTitle=function(e){e&&""!==e&&this.htmlBridge.setAttribute("title",e)},c.prototype.setSize=function(e,t){this.ready()&&this.flashBridge.setSize(e,t)},c.prototype.setHandCursor=function(e){this.ready()&&this.flashBridge.setHandCursor(e)},c.version="1.1.7";var u={moviePath:"ZeroClipboard.swf",trustedDomains:null,text:null,hoverClass:"zeroclipboard-is-hover",activeClass:"zeroclipboard-is-active",allowScriptAccess:"sameDomain"};c.setDefaults=function(e){for(var t in e)u[t]=e[t]},c.destroy=function(){c.prototype._singleton.unglue(f);var e=c.prototype._singleton.htmlBridge;e.parentNode.removeChild(e),delete c.prototype._singleton},c.detectFlashSupport=function(){var e=!1;try{new ActiveXObject("ShockwaveFlash.ShockwaveFlash")&&(e=!0)}catch(t){navigator.mimeTypes["application/x-shockwave-flash"]&&(e=!0)}return e};var m=function(){var e=c.prototype._singleton,t=document.getElementById("global-zeroclipboard-html-bridge");if(!t){var o='      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="global-zeroclipboard-flash-bridge" width="100%" height="100%">         <param name="movie" value="'+e.options.moviePath+l(e.options.moviePath)+'"/>         <param name="allowScriptAccess" value="'+e.options.allowScriptAccess+'"/>         <param name="scale" value="exactfit"/>         <param name="loop" value="false"/>         <param name="menu" value="false"/>         <param name="quality" value="best" />         <param name="bgcolor" value="#ffffff"/>         <param name="wmode" value="transparent"/>         <param name="flashvars" value="'+p(e.options)+'"/>         <embed src="'+e.options.moviePath+l(e.options.moviePath)+'"           loop="false" menu="false"           quality="best" bgcolor="#ffffff"           width="100%" height="100%"           name="global-zeroclipboard-flash-bridge"           allowScriptAccess="always"           allowFullScreen="false"           type="application/x-shockwave-flash"           wmode="transparent"           pluginspage="http://www.macromedia.com/go/getflashplayer"           flashvars="'+p(e.options)+'"           scale="exactfit">         </embed>       </object>';t=document.createElement("div"),t.id="global-zeroclipboard-html-bridge",t.setAttribute("class","global-zeroclipboard-container"),t.setAttribute("data-clipboard-ready",!1),t.style.position="absolute",t.style.left="-9999px",t.style.top="-9999px",t.style.width="15px",t.style.height="15px",t.style.zIndex="9999",t.innerHTML=o,document.body.appendChild(t)}e.htmlBridge=t,e.flashBridge=document["global-zeroclipboard-flash-bridge"]||t.children[0].lastElementChild};c.prototype.resetBridge=function(){this.htmlBridge.style.left="-9999px",this.htmlBridge.style.top="-9999px",this.htmlBridge.removeAttribute("title"),this.htmlBridge.removeAttribute("data-clipboard-text"),a(e,this.options.activeClass),e=null,this.options.text=null},c.prototype.ready=function(){var e=this.htmlBridge.getAttribute("data-clipboard-ready");return"true"===e||e===!0},c.prototype.reposition=function(){if(!e)return!1;var t=n(e);this.htmlBridge.style.top=t.top+"px",this.htmlBridge.style.left=t.left+"px",this.htmlBridge.style.width=t.width+"px",this.htmlBridge.style.height=t.height+"px",this.htmlBridge.style.zIndex=t.zIndex+1,this.setSize(t.width,t.height)},c.dispatch=function(e,t){c.prototype._singleton.receiveEvent(e,t)},c.prototype.on=function(e,t){for(var o=e.toString().split(/\s/g),r=0;r<o.length;r++)e=o[r].toLowerCase().replace(/^on/,""),this.handlers[e]||(this.handlers[e]=t);this.handlers.noflash&&!c.detectFlashSupport()&&this.receiveEvent("onNoFlash",null)},c.prototype.addEventListener=c.prototype.on,c.prototype.off=function(e,t){for(var o=e.toString().split(/\s/g),r=0;r<o.length;r++){e=o[r].toLowerCase().replace(/^on/,"");for(var i in this.handlers)i===e&&this.handlers[i]===t&&delete this.handlers[i]}},c.prototype.removeEventListener=c.prototype.off,c.prototype.receiveEvent=function(t,o){t=t.toString().toLowerCase().replace(/^on/,"");var r=e;switch(t){case"load":if(o&&parseFloat(o.flashVersion.replace(",",".").replace(/[^0-9\.]/gi,""))<10)return this.receiveEvent("onWrongFlash",{flashVersion:o.flashVersion}),void 0;this.htmlBridge.setAttribute("data-clipboard-ready",!0);break;case"mouseover":s(r,this.options.hoverClass);break;case"mouseout":a(r,this.options.hoverClass),this.resetBridge();break;case"mousedown":s(r,this.options.activeClass);break;case"mouseup":a(r,this.options.activeClass);break;case"datarequested":var i=r.getAttribute("data-clipboard-target"),n=i?document.getElementById(i):null;if(n){var l=n.value||n.textContent||n.innerText;l&&this.setText(l)}else{var p=r.getAttribute("data-clipboard-text");p&&this.setText(p)}break;case"complete":this.options.text=null}if(this.handlers[t]){var d=this.handlers[t];"function"==typeof d?d.call(r,this,o):"string"==typeof d&&window[d].call(r,this,o)}},c.prototype.glue=function(e){e=h(e);for(var t=0;t<e.length;t++)-1==d(e[t],f)&&(f.push(e[t]),r(e[t],"mouseover",o))},c.prototype.unglue=function(e){e=h(e);for(var t=0;t<e.length;t++){i(e[t],"mouseover",o);var r=d(e[t],f);-1!=r&&f.splice(r,1)}},"undefined"!=typeof module?module.exports=c:"function"==typeof define&&define.amd?define(function(){return c}):window.ZeroClipboard=c}();