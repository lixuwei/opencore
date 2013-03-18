
	function checkFromAndAgent(){
			var webFrom = getParameter("webFrom");
			var webAgent = getParameter("webAgent");
			//d30表示给cookie指定30天的失效时间
			if(webFrom != null && webFrom.trim() != "") {
				var webFrom_coo=getCookie("webFrom");
				if(webFrom_coo == null || webFrom_coo.trim() == "") {
					SetCookieOutTime("webFrom", webFrom,"d30");
				}
			}
			if(webAgent != null && webAgent.trim() != "") {
				var webAgent_coo=getCookie("webAgent");
				if(webAgent_coo == null || webAgent_coo.trim() == "") {
					SetCookieOutTime("webAgent", webAgent,"d30");
				}	
			}
	}

	/**获取Cookies方法**/
	function getCookie(cookieName) {
		var cookieString = document.cookie;
		var start = cookieString.indexOf(cookieName + '=');
		// 加上等号的原因是避免在某些 Cookie 的值里有
		// 与 cookieName 一样的字符串。
		if (start == -1) // 找不到
			return null;
		start += cookieName.length + 1;
		var end = cookieString.indexOf(';', start);
		if (end == -1) {
			return unescape(cookieString.substring(start));
		} else {
			return unescape(cookieString.substring(start, end));
		}
	}

	function getCookieFromServer(cookieName) {

		var cookieString = document.cookie;
		var start = cookieString.indexOf(cookieName + '=');
		// 加上等号的原因是避免在某些 Cookie 的值里有
		// 与 cookieName 一样的字符串。
		if (start == -1) // 找不到
			return null;
		start += cookieName.length + 1;
		var end = cookieString.indexOf(';', start);
		if (end == -1) {
			return Url.decode(cookieString.substring(start));
		} else {
			return Url.decode(cookieString.substring(start, end));
		}
	}
	/**删除Cookies**/
	function DeleteCookie(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = getCookie(name);
		document.cookie = name + "=" + escape(cval) + ";expires="
				+ exp.toGMTString() + ";path=/";
	}
	/**创建Cookies**/
	function SetCookie(name, value) {
		var Days = 2;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString() + ";path=/";
	}
	//自定义cookies失效时间 s指秒 h指天数 d指天数 如s40代表40秒
	function SetCookieOutTime(name, value, outTime) {
		var strsec = getsec(outTime);
		var exp = new Date();
		exp.setTime(exp.getTime() + strsec * 1);
		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString()+";path=/";
	}
	// 转换cookies时间
	function getsec(str) {
		var str1 = str.substring(1, str.length) * 1;
		var str2 = str.substring(0, 1);
		if (str2 == "s") {
			return str1 * 1000;
		} else if (str2 == "h") {
			return str1 * 60 * 60 * 1000;
		} else if (str2 == "d") {
			return str1 * 24 * 60 * 60 * 1000;
		}
	}

	function getParameter(val) {
		var uri = window.location.search;
		var re = new RegExp("" + val + "=([^&?]*)", "ig");
		return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
	}

	var Url = {
		encode : function(string) {
			return escape(this._utf8_encode(string));
		},
		decode : function(string) {
			return this._utf8_decode(unescape(string));
		},
		_utf8_encode : function(string) {
			string = string.replace(/\r\n/g, "\n");
			var utftext = "";

			for (var n = 0; n < string.length; n++) {

				var c = string.charCodeAt(n);

				if (c < 128) {
					utftext += String.fromCharCode(c);
				} else if ((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				} else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}

			}

			return utftext;
		},

		// private method for UTF-8 decoding
		_utf8_decode : function(utftext) {
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;

			while (i < utftext.length) {

				c = utftext.charCodeAt(i);

				if (c < 128) {
					string += String.fromCharCode(c);
					i++;
				} else if ((c > 191) && (c < 224)) {
					c2 = utftext.charCodeAt(i + 1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				} else {
					c2 = utftext.charCodeAt(i + 1);
					c3 = utftext.charCodeAt(i + 2);
					string += String.fromCharCode(((c & 15) << 12)
							| ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}

			}
			return string;
		}
	}

	function quickLogKeyUp(event, index) {
		var keyCode = event.keyCode ? event.keyCode : event.which
				? event.which
				: event.charCode;
		if (keyCode == 13) {
			login(index);
		}
	}

	function regEmailChg(event) {
		var e = event || window.event;
		var keyCode = e.keyCode || e.which;
		if (keyCode >= 65 && keyCode <= 90) {
			return false;
		}
	}


	function iptLowerCase(event) {
		var e = event || window.event;
		var keyCode = e.keyCode || e.which;
		if (keyCode >= 65 && keyCode <= 90) {
			e.keyCode = keyCode + 32;
		}
	    if (keyCode==32){
	    	e.keyCode=0;
			e.returnvalue=false;
	    }
	}

	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	String.prototype.ltrim = function() {
		return this.replace(/(^\s*)/g, "");
	}
	String.prototype.rtrim = function() {
		return this.replace(/(\s*$)/g, "");
	}
 
	//删除指定域名下的共享cookie.二级域名可用
 	function DeleteCookieDomain(name,domain) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = getCookie(name);
		document.cookie = name + "=" + escape(cval) + ";expires="
				+ exp.toGMTString() + ";path=/"+";domain="+domain;
 	}
 	
 	/**创建Cookies 可设置域名**/
 	function SetCookieDomain(name, value,domain) {
 		var Days = 2;
 		var exp = new Date();
 		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
 		document.cookie = name + "=" + escape(value) + ";expires="
 				+ exp.toGMTString() + ";path=/"+";domain="+domain;
 	}
 