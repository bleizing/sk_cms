/**
 * 
 */

var Infosys={};
Infosys['key']='0000000000000000';

Infosys['SCRIPTS']=[
                    
    /* FORGES */
    '/scripts/forge/forge.js',
    '/scripts/forge/crypto.js',
    '/scripts/forge/tls.js',
    '/scripts/forge/cipher.js',
    '/scripts/forge/cipherModes.js',
    '/scripts/forge/aes.js',
    '/scripts/forge/aesCipherSuites.js',
    '/scripts/forge/util.js',
    
    /* JSON */
    '/scripts/json/json.js'
    
];

$(document).ready(function(){
	var _body=$(document.body);
	for(var i=0;i<Infosys.SCRIPTS.length;i++){
		var _src=Infosys.SCRIPTS[i];
		_body.append(
			$('<script>')
				.attr('type','text/javascript')
				.attr('src',_ctx+_src)
		);
	}
});

function test() {
	alert('do test');
}

Infosys['log']=function(obj){
	try {
		console.log(obj);
	} catch (e) {
	}
};

Infosys['showProgress']=function(){
	var _body=document.body;
	var _div=document.createElement('div');
	_div['style']['position']='absolute';
	_div['style']['top']='0px';
	_div['style']['left']='0px';
	_div['style']['width']='100%';
	_div['style']['height']='100%';
	_div['style']['backgroundImage']='url(/image/static/progress/transbg.png)';
	
	var _img=document.createElement('img');
	_img['alt']='';
	_img['src']='/image/static/progress/loading.gif';
	_img.style['position']='absolute';
	_img.style['height']='100px';
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

	var _size=(0.06*document.body.offsetWidth)+'px';
	_img.style['width']=_size;
	_img.style['height']=_size;
	
	return _div;
};

Infosys['hideProgress']=function(prg){
	var _body=document.body;
	_body.removeChild(prg);
};

Infosys['publicApiJson']=function(url, json, timeout, onsuccess, onfail) {
	var _prg=Infosys.showProgress();
	var _param={
	    type:'POST',
	    url:url,
	    contentType:'text/plain',
	    dataType:'text',
	    timeout:timeout
	};
	
	if(json){
		_param['data']=JSON.stringify(json);
	}
	
	$.ajax(_param)
		.done(function(data,status,xhr) {			
			try {
				var _isEncrypted=xhr.getResponseHeader('IST-Using-Encryption');
				var _clear='{}';
				if('true'==_isEncrypted){
					var _defaultKey=xhr.getResponseHeader('IST-Default-Key');
					if(_defaultKey){
						_clear=Crypto.decrypt(data,_defaultKey);
					}else{
						_clear=Crypto.decrypt(data,Infosys.key);
					}
				}

				var _ret=JSON.parse(_clear);
				onsuccess(_ret);
			}catch(e){
				try{
					onfail(''+e);
				}catch(ex){}
			}
		})
		.fail(function(err) {
			try{
				onfail(''+err);
			}catch(e){}
		})
		.always(function() {
			Infosys.hideProgress(_prg);
		});
};
