var Infosys={};

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