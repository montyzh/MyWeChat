var SERVER_PATH = '172.20.10.4:8080/mywechat/';

var HTTP_URL = 'http://' + SERVER_PATH;
var WEBSOCKET_URL = 'ws://' + SERVER_PATH + 'websocket';

function toServerUrl(url) {
	return HTTP_URL + url;
}

function ajax(action, data, options) {
	var defaultOptions = {
		data: JSON.stringify(data || {}),
		dataType: 'json', //服务器返回json格式数据
		type: 'post', //HTTP请求类型
		timeout: 10000, //超时时间设置为10秒
		headers: {
			'Content-Type': 'application/json'
		},
		error : function() {
			mui.toast('Network error');
		},
		success : function() {}
	};
	
	jQuery.extend(defaultOptions, options);
	
	$.ajax(HTTP_URL + 'wl/' + action, defaultOptions);
}

function ajaxT(action, data, options) {
	var defaultOptions = {
		data: JSON.stringify(data || {}),
		dataType: 'json', //服务器返回json格式数据
		type: 'post', //HTTP请求类型
		timeout: 10000, //超时时间设置为10秒
		async:false,
		headers: {
			'Content-Type': 'application/json'
		},
		error : function() {
			mui.toast('Network error');
		},
		success : function() {}
	};
	
	jQuery.extend(defaultOptions, options);
	
	$.ajax(HTTP_URL + 'wl/' + action, defaultOptions);
}

function setCurUser(data) {
	data = data || {};
	localStorage.setItem('$user', JSON.stringify(data));
}

function getCurUser() {
	var stateText = localStorage.getItem('$user') || "{}";
	return JSON.parse(stateText); 
}

function setAutoLogin(isAutoLogin) {
	localStorage.setItem('$autoLogin', JSON.stringify({isAutoLogin:isAutoLogin}));
}
 
function getAutoLogin() {
	var stateText =  localStorage.getItem('$autoLogin') || '{"isAutoLogin":false}';
	return JSON.parse(stateText).isAutoLogin;
}


//保存朋友
function setCurFiriends(data) {
	data = data || {};
	localStorage.setItem('$MyFriends', JSON.stringify(data));
	
}
//得到朋友
function getCurFiriends() {
	var stateText = localStorage.getItem('$MyFriends') || "{}";
	
	return JSON.parse(stateText);
}





