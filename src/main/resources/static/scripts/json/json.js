if (typeof JSON !== 'object') {
    JSON = {};
}

(function () {
    'use strict';
    
    var rx_one = /^[\],:{}\s]*$/,
        rx_two = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,
        rx_three = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
        rx_four = /(?:^|:|,)(?:\s*\[)+/g,
        rx_escapable = /[\\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        rx_dangerous = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 
            ? '0' + n 
            : n;
    }
    
    function this_value() {
        return this.valueOf();
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function () {

            return isFinite(this.valueOf())
                ? this.getUTCFullYear() + '-' +
                        f(this.getUTCMonth() + 1) + '-' +
                        f(this.getUTCDate()) + 'T' +
                        f(this.getUTCHours()) + ':' +
                        f(this.getUTCMinutes()) + ':' +
                        f(this.getUTCSeconds()) + 'Z'
                : null;
        };

        Boolean.prototype.toJSON = this_value;
        Number.prototype.toJSON = this_value;
        String.prototype.toJSON = this_value;
    }

    var gap,
        indent,
        meta,
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        rx_escapable.lastIndex = 0;
        return rx_escapable.test(string) 
            ? '"' + string.replace(rx_escapable, function (a) {
                var c = meta[a];
                return typeof c === 'string'
                    ? c
                    : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
            }) + '"' 
            : '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

            return isFinite(value) 
                ? String(value) 
                : 'null';

        case 'boolean':
        case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

            return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

        case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

            if (!value) {
                return 'null';
            }

// Make an array to hold the partial results of stringifying this object value.

            gap += indent;
            partial = [];

// Is the value an array?

            if (Object.prototype.toString.apply(value) === '[object Array]') {

// The value is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                v = partial.length === 0
                    ? '[]'
                    : gap
                        ? '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']'
                        : '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

// If the replacer is an array, use it to select the members to be stringified.

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (
                                gap 
                                    ? ': ' 
                                    : ':'
                            ) + v);
                        }
                    }
                }
            } else {

// Otherwise, iterate through all of the keys in the object.

                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (
                                gap 
                                    ? ': ' 
                                    : ':'
                            ) + v);
                        }
                    }
                }
            }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

            v = partial.length === 0
                ? '{}'
                : gap
                    ? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}'
                    : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"': '\\"',
            '\\': '\\\\'
        };
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'': value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            text = String(text);
            rx_dangerous.lastIndex = 0;
            if (rx_dangerous.test(text)) {
                text = text.replace(rx_dangerous, function (a) {
                    return '\\u' +
                            ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (
                rx_one.test(
                    text
                        .replace(rx_two, '@')
                        .replace(rx_three, ']')
                        .replace(rx_four, '')
                )
            ) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function'
                    ? walk({'': j}, '')
                    : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }
}());

var ERROR_CODE={
		'timeout':{
			'code':-1,
			'message':'Request timeout'
		},
		'error':{
			'code':-2,
			'message':'Problem with request'
		}
};

var CONSTS={
	'HDR_ENC':'IST-Encrypted',
	'HDR_TEMP_KEY':'IST-Temp-Key',
	'DOC_CRYPTO_KEY':'CRYPTO_KEY',
	'DOC_USING_ENCRYPTION':'USING_ENCRYPTION'
};

var getJSPath=function(jsname){
	return '';
};

var showAlert=function(txt,onOK){
	var _ctx=ctx;
	if(null!=document['context']){
		_ctx=document['context']+'/';
	}

	var _body=document.body;
	var _div=document.createElement('div');
	_div['style']['position']='fixed';
	_div['style']['top']='0px';
	_div['style']['left']='0px';
	_div['style']['width']='100%';
	_div['style']['height']='100%';
    _div['style']['z-index']='9999';
	_div['style']['backgroundImage']='url('+_ctx+'scripts/transbg.png)';
	
	var _frame=document.createElement('div');
	_frame.style['position']='absolute';
	_frame.style['height']='200px';
	_frame.style['width']='400px';
	_frame.style['backgroundColor']='#FFFFFF';
	_frame.innerHTML=txt;
	_div.appendChild(_frame);

	_body.appendChild(_div);
	var _btn=document.createElement('input');
	_btn['type']='button';
	_frame.appendChild(_btn);
	_btn.focus();
	_btn.style['position']='absolute';
	_btn.style['left']='50%';
	_btn.style['top']='50%';
	_btn.onclick=function(){
		try {
			_body.removeChild(_div);
			if(document[onOK]){
				document[onOK]();
			}
		} catch (e) {
			alert('Error: ' + e + ' on: ' + _div);
		}
	};
	return _div;
};

var showProgress=function(){
	var _ctx=ctx;
	if(null!=document['context']){
		_ctx=document['context']+'/';
	}

	var _body=document.body;
	var _div=document.createElement('div');
	_div['style']['position']='fixed';
	_div['style']['top']='0px';
	_div['style']['left']='0px';
	_div['style']['width']='100%';
	_div['style']['height']='100%';
	_div['style']['z-index']='9999';
	_div['style']['backgroundImage']='url('+_ctx+'scripts/transbg.png)';
	
	var _img=document.createElement('img');
	_img['alt']='';
	_img['src']=_ctx+'scripts/loading.gif';
	_img.style['position']='fixed';
	_img.style['height']='100px';
	_img.style['z-index']='9999';
    _img.style['top']='50%';
    _img.style['left']='50%';
	_div.appendChild(_img);

	_body.appendChild(_div);
	var _btn=document.createElement('input');
	_btn['type']='button';
	_div.appendChild(_btn);
	_btn.focus();
	_btn.style['position']='absolute';
	_btn.style['left']='-200px';
	_btn.style['top']='-200px';
	
	_img.style['left']=((_div.offsetWidth-_img.offsetWidth)/2)+'px';
	_img.style['top']=((_div.offsetHeight-_img.offsetHeight)/2)+'px';
	return _div;
};

var hideProgress=function(divobj){
	var _body=document.body;
	_body.removeChild(divobj);
};

JSON['log']=function(obj){
	if(console){
		try{
			console.log(obj);
		}catch(e){
			alert(e);
		}
	}
};

JSON['post']=function(url,obj,success,fail,timeout){
	if(!document[CONSTS.DOC_CRYPTO_KEY]||document[CONSTS.DOC_CRYPTO_KEY].length!=16){
		document[CONSTS.DOC_CRYPTO_KEY]='0000000000000000';
	}

	var _div=showProgress();
	var _key=document[CONSTS.DOC_CRYPTO_KEY];
	var _data=JSON.stringify(obj);
	if(document[CONSTS.DOC_USING_ENCRYPTION]===true){
		_data=Crypto.encrypt(_data,_key);
	}
	
	$.ajax({
	    type:'POST',
	    url:url,
	    data:_data,
	    contentType:'text/plain',
	    dataType:'text',
	    timeout:timeout
	})
	 .done(function(data,status,xhr) {
		 try{
			 var _usingEnc=xhr.getResponseHeader(CONSTS.HDR_ENC);
			 document[CONSTS.DOC_USING_ENCRYPTION]=(_usingEnc==='true');
			 if(document[CONSTS.DOC_USING_ENCRYPTION]!==true){
				var _respObj=JSON.parse(data);
				if(_respObj.code===-1004){
					window.location.replace(ctx + "/pages/login");
				}
				if(_respObj.code===-1099){
					window.location.replace(ctx);
				}else if(_respObj.code===-5000){
                    window.location.replace(ctx);
                }else{
					if(null!=success) {
						success(_respObj,xhr);
					}
				}
				return;
			 }
			 
			 var _defKey = xhr.getResponseHeader(CONSTS.HDR_TEMP_KEY);
			 if(null==_defKey){
				var _resp=Crypto.decrypt(data,_key);
				JSON.log("RCVD_DATA="+_resp);
				var _respObj=JSON.parse(_resp);
				if(_respObj.code===-1004){
					window.location.replace(ctx + "/pages/login");
				}
				if(_respObj.code===-1099){
					window.location.replace(ctx);
				}else if(_respObj.code===-5000){
                    window.location.replace(ctx);
                }else{
					if(null!=success) {
						success(_respObj);
					}
				}
				
				return;
			 }
			 
			var _resp=Crypto.decrypt(data,_defKey);
			var _respObj=JSON.parse(_resp);
			if(_respObj.code===-1004){
				window.location.replace(ctx + "/pages/login");
			}
			if(_respObj.code===-1099){
				window.location.replace(ctx);
			} else if(_respObj.code===-5000){
                window.location.replace(ctx);
            } else{
				if(null!=success) {
					success(_respObj);
				}
			}
		 }catch (e) {
//		 	alert(e);
			 JSON.log(e);
		 }
	 })
	 .fail(function(err) {
		 try{
			 var _ret={};
			 _ret['code']=ERROR_CODE[err['statusText']]['code'];
			 _ret['message']=ERROR_CODE[err['statusText']]['message'];
			 
			 if(null!=fail){
				 fail(_ret);
			 }
		 }catch (e) {
			 JSON.log(e);
		 }
	 })
	 .always(function(resp) {
		JSON.log("SENT_DATA="+JSON.stringify(obj));
		 hideProgress(_div);
	 });
};

var encKeySuccess=function(obj){
	if(obj.code===0){
		document[CONSTS.DOC_CRYPTO_KEY]=obj.key;
	}
};

var encKeyError=function(obj){
//	alert(obj);
};

// JSON.post(document.context+'/json/key',{},encKeySuccess,encKeyError,10000);
